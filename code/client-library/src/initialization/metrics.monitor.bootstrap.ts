import {HealthStatus} from "../classes";
import {AjaxCaller, AjaxResponse} from "../classes/ajax";
import {Configuration} from "../configuration";
import {HEALTH_ENDPOINT} from "../library.config";
import {Logger} from "../log";
import {SocketConnectionUtil, SocketMessageType} from "../socket";

export class MetricsMonitor {

    public static init(configuration: Configuration): Promise<string> {
        Logger.initialize(configuration.log || "info");
        Logger.info(`[MetricsMonitor] Initializing Metrics Monitor...`);
        return new Promise<string>((resolve, reject) => {
            MetricsMonitor
                .checkServerUrl(configuration.serverUrl)
                .then(() => {
                    Logger.debug(`[MetricsMonitor] Server healtcheck succeeded.`);
                    SocketConnectionUtil
                        .connectSocket(configuration.serverUrl)
                        .then(() => {
                            SocketConnectionUtil.sendMessageToServer({type: SocketMessageType.REGISTRATION});
                            resolve();
                        })
                        .catch((err: Error) => {
                            reject(err);
                        });
                })
                .catch((err: Error) => {
                    reject(err);
                });
        });
    }

    private static checkServerUrl(serverUrl: string): Promise<void> {
        return new Promise<void>((resolve, reject) => {
            new AjaxCaller<void, HealthStatus>({
                url: serverUrl + HEALTH_ENDPOINT,
                method: "GET",
            }).invoke().then((res: AjaxResponse<HealthStatus>) => {
                if (res.status === 200) {
                    resolve();
                } else {
                    reject(new Error("Error connecting to server! Server unavailable or server healthcheck fails."));
                }
            }).catch((err) => {
                reject(new Error("Error connecting to server! Server unavailable or server healthcheck fails."));
            });
        });
    }

}
