import {ConfigService, MonitorState} from "../configuration";
import {SOCKET_ENDPOINT} from "../library.config";
import {Logger} from "../log";
import {SocketHandlerService} from "./handler";
import {SocketMessage, SocketRegistrationRequestMessage} from "./messages";
import {SessionPingMessage} from "./messages/session.socket.message.class";

export class SocketService {

    private static socket: WebSocket;

    private static intervalID: any = 0;

    public static connectSocket(serverUrl: string): Promise<void> {

        return new Promise<void>((resolve, reject) => {

            const socketUrl = (serverUrl + SOCKET_ENDPOINT).replace("http", "ws");
            SocketService.socket = new WebSocket(socketUrl);

            SocketService.socket.onerror = (e: Event) => {
                MonitorState.getMonitorState().socketConnected = false;
                reject(new Error("Connection to socket failed!"));
            };

            SocketService.socket.onopen = (e: Event) => {
                MonitorState.getMonitorState().socketConnected = true;
                SocketService.sendRegistrationEvent();
                SocketService.intervalID = setInterval(SocketService.pingServerSocket, 60000);
                resolve();
            };

            SocketService.socket.onclose = (e: CloseEvent) => {
                Logger.debug("Socket connection was closed with reason: " + e.reason);
                MonitorState.getMonitorState().socketConnected = false;
                clearInterval(SocketService.intervalID);
                reject(new Error("Socket connection was closed with reason: " + e.reason));
            };

            SocketService.socket.onmessage = SocketService.onSocketMessage.bind(this);
        });
    }

    private static pingServerSocket(): void {
        const pingMessage = new SessionPingMessage();
        Logger.debug("Pinging server");
        SocketService.sendMessage(pingMessage);
    }

    public static sendMessage(message: SocketMessage): void {
        if (SocketService.socket.readyState === SocketService.socket.OPEN) {
            SocketService.socket.send(JSON.stringify(message));
        }
    }

    private static onSocketMessage(e: MessageEvent) {
        try {
            const message: SocketMessage = Object.assign(new SocketMessage(), JSON.parse(e.data));
            SocketHandlerService.handleSocketMessage(message);
        } catch (e) {
            Logger.error("Error parsing socket message. Message must be valid JSON!");
        }
    }

    private static sendRegistrationEvent(): void {
        const registrationRequest = new SocketRegistrationRequestMessage(ConfigService.getConfig().applicationName);
        SocketService.sendMessage(registrationRequest);
    }

}
