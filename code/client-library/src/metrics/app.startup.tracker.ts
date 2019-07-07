import {MonitorState} from "../configuration";
import {SocketService} from "../socket";
import {execSessionFunc} from "../socket/exec.session.func";
import {AppStartupMessage} from "../socket/messages/session.socket.message.class";

export class AppStartupTracker {

    public static trackApplicationStartup() {
        const applicationLoaded = Date.now();
        const navigationStart = typeof performance !== "undefined" ? performance.timeOrigin : null;

        execSessionFunc(() => {
            const message = new AppStartupMessage();
            message.applicationLoaded = applicationLoaded;
            message.navigationStart = navigationStart;
            SocketService.sendMessage(message);
        });
    }

}
