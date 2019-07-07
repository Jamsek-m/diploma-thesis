import {ConfigService, Configuration} from "../configuration";
import {Logger} from "../log";
import {registerTrackers} from "../metrics";
import {HeatmapService} from "../reports";
import {ServerHealthUtil} from "../server";
import {SocketService} from "../socket";

export class MetricsMonitor {

    /**
     * Bootstraps library and connects to server
     * @param configuration Configuration for library
     */
    public static init(configuration: Configuration): Promise<string> {

        ConfigService.setConfiguration(configuration);

        Logger.initialize(ConfigService.getConfig().log);
        Logger.info("Initializing Metrics Monitor...");

        return new Promise<string>((resolve, reject) => {

            ServerHealthUtil
                .checkServerUrl(ConfigService.getConfig().serverUrl)
                .then(() => {

                    // Healthcheck returned ok, we can connect
                    Logger.debug("Server healtcheck succeeded.");

                    MetricsMonitor.resolveMode(resolve, reject);
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

    private static resolveMode(resolve: any, reject: any) {

        switch (ConfigService.getConfig().mode) {
            case "heatmap":
                HeatmapService.drawHeatmap();
                resolve();
                break;
            default:
            case "capture":
                SocketService
                    .connectSocket(ConfigService.getConfig().serverUrl)
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

}
