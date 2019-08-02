import {MonitorState} from "../configuration";
import {SocketService} from "../socket";
import {execSessionFunc} from "../socket/exec.session.func";
import {AppStartupMessage} from "../socket/messages/session.socket.message.class";
import {ResourceTracker} from "./resource.tracker";

export class AppStartupTracker {

    public static trackApplicationStartup() {
        const applicationLoaded = Date.now();

        if (!AppStartupTracker.isFeatureEnabled()) {
            return;
        }

        if (MonitorState.getMonitorState().startingApplication) {

            let navigationStart: number;
            if (MonitorState.getMonitorState().browserFeatures["timeOrigin"]) {
                navigationStart = performance.timeOrigin;
            } else if (MonitorState.getMonitorState().browserFeatures["navigationStart"]) {
                navigationStart = performance.timing.navigationStart;
            }

            MonitorState.getMonitorState().setStartedApplication();

            execSessionFunc(() => {
                const message = new AppStartupMessage();
                message.applicationLoaded = applicationLoaded;
                message.navigationStart = navigationStart;
                SocketService.sendMessage(message);
            });

            ResourceTracker.trackResources();
        }
    }

    private static isFeatureEnabled(): boolean {
        return MonitorState.getMonitorState().browserFeatures["timeOrigin"] ||
            MonitorState.getMonitorState().browserFeatures["navigationStart"];
    }

}
