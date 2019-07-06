import {ConfigService, Configuration} from "../configuration";
import {Logger} from "../log";
import {registerTrackers} from "../metrics";
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

}
