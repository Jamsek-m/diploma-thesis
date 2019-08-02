import {ConfigService, Configuration, MonitorState} from "../configuration";
import {Logger} from "../log";
import {registerTrackers} from "../metrics";
import {AppStartupTracker} from "../metrics/app.startup.tracker";
import {PageLoadTracker} from "../metrics/page.load.tracker";
import {HeatmapService} from "../reports";
import {ServerHealthUtil} from "../server";
import {SocketService} from "../socket";
import {FeatureDetector} from "./feature.detector";

export class MetricsMonitor {

    /**
     * Bootstraps library and connects to server
     * @param configuration Configuration for library
     */
    public static init(configuration: Configuration): Promise<string> {

        ConfigService.setConfiguration(configuration);

        Logger.initialize(ConfigService.getConfig().log);
        Logger.info("Initializing Metrics Monitor...");

        MonitorState.getMonitorState().setBrowserFeatures(FeatureDetector.detectFeatures());

        return new Promise<string>((resolve, reject) => {

            ServerHealthUtil.checkServerUrl(ConfigService.getConfig().serverUrl)
                .then(() => {

                    // Healthcheck returned ok, we can connect
                    Logger.debug("Server healtcheck succeeded.");

                    if (ConfigService.getConfig().mode === "capture") {

                        SocketService.connectSocket(ConfigService.getConfig().serverUrl)
                            .then(() => {

                                Logger.debug("Connection to socket is now established.");

                                registerTrackers();

                                resolve();
                            })
                            .catch((err: Error) => {

                                if (ConfigService.getConfig().silentFail) {
                                    Logger.warn("Error on socket connection!");
                                    resolve();
                                } else {
                                    reject(err);
                                }
                            });

                    } else {
                        resolve();
                    }

                    MetricsMonitor.resolveMode();
                })
                .catch((err: Error) => {

                    if (ConfigService.getConfig().silentFail) {
                        Logger.warn("Server healthcheck failed! Metrics Monitor will not be working");
                        resolve();
                    } else {
                        reject(err);
                    }
                });
        });
    }

    private static resolveMode() {

        switch (ConfigService.getConfig().mode) {
            case "heatmap":
                HeatmapService.drawHeatmap();
                break;
            default:
                break;
        }
    }

    /**
     * Redraws heatmap with fresh data from service.
     * Usually it is called when navigating to new page, to get new page data.
     * Works only in 'heatmap' mode.
     */
    public static redrawHeatmap(): void {
        if (ConfigService.getConfig().mode === "heatmap") {
            HeatmapService.drawHeatmap();
        }
    }

    /**
     * Logs start and end of application load.
     * Call this function where you know your application is loaded.
     * Works only in 'capture' mode.
     */
    public static logApplicationStartup(): void {
        if (ConfigService.getConfig().mode === "capture") {
            AppStartupTracker.trackApplicationStartup();
        }
    }

    /**
     * Logs start of page load.
     * Call this function when you know navigation to page started.
     * @param pageName Unique page name to identify page amongst others. Pathname is recommended.
     */
    public static logPageLoadStart(pageName: string) {
        if (ConfigService.getConfig().mode === "capture") {
            PageLoadTracker.trackPageLoadStart(pageName);
        }
    }

    /**
     * Logs end of page load.
     * Call this function when you know page has loaded.
     * @param pageName Unique page name to identify page amongst others. Pathname is recommended.
     */
    public static logPageLoadEnd(pageName: string) {
        if (ConfigService.getConfig().mode === "capture") {
            PageLoadTracker.trackPageLoadEnd(pageName);
        }
    }

}
