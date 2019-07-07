import {MonitorState} from "../configuration";
import {SocketService} from "../socket";
import {execSessionFunc} from "../socket/exec.session.func";
import {AppStartupMessage} from "../socket/messages/session.socket.message.class";

export class AppStartupTracker {

    public static trackApplicationStartup() {
        const applicationLoaded = Date.now();

        if (MonitorState.getMonitorState().startingApplication) {

            const navigationStart = typeof performance !== "undefined" ? performance.timeOrigin : null;
            MonitorState.getMonitorState().setStartedApplication();

            execSessionFunc(() => {
                const message = new AppStartupMessage();
                message.applicationLoaded = applicationLoaded;
                message.navigationStart = navigationStart;
                SocketService.sendMessage(message);
            });
        }
    }

}
