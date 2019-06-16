import {rejects} from "assert";
import {SOCKET_ENDPOINT} from "../library.config";
import {Logger} from "../log";
import {SocketMessage} from "./socket.message.class";

export class SocketConnectionUtil {

    private static socket: WebSocket;

    public static connectSocket(serverUrl: string): Promise<void> {
        return new Promise<void>((resolve, reject) => {
            const socketUrl = (serverUrl + SOCKET_ENDPOINT).replace(/^https?/, "ws");
            SocketConnectionUtil.socket = new WebSocket(socketUrl);
            SocketConnectionUtil.socket.onerror = (e: Event) => {
                Logger.error(`[MetricsMonitor] Error connecting to server socket!`);
                reject();
            };
            SocketConnectionUtil.socket.onopen = (e: Event) => {
                Logger.debug(`[MetricsMonitor] Connected to server socket.`);
                resolve();
            };
            SocketConnectionUtil.socket.onmessage = SocketConnectionUtil.onSocketMessage.bind(this);
        });
    }

    public static sendMessageToServer(message: SocketMessage): void {
        if (SocketConnectionUtil.socket.readyState === SocketConnectionUtil.socket.OPEN) {
            SocketConnectionUtil.socket.send(JSON.stringify(message));
        }
    }

    private static onSocketMessage(e: MessageEvent) {
        console.log(e);
    }

}
