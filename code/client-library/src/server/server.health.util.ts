import {AjaxResponse, HealthStatus} from "../classes";
import {AjaxCaller} from "../classes/ajax";
import {MonitorState} from "../configuration";
import {HEALTH_ENDPOINT} from "../library.config";

export class ServerHealthUtil {

    public static checkServerUrl(serverUrl: string): Promise<void> {
        return new Promise<void>((resolve, reject) => {
            new AjaxCaller<void, HealthStatus>({
                url: serverUrl + HEALTH_ENDPOINT,
            }).invoke().then((res: AjaxResponse<HealthStatus>) => {
                if (res.status === 200) {
                    MonitorState.getMonitorState().serverConnected = true;
                    resolve();
                } else {
                    MonitorState.getMonitorState().serverConnected = false;
                    reject(new Error("Error connecting to server! Server healthcheck fails."));
                }
            }).catch((err) => {
                MonitorState.getMonitorState().serverConnected = false;
                reject(new Error("Error connecting to server! Server unavailable"));
            });
        });
    }

}
