import {SocketMessage} from "../messages";
import {SocketHandlerFactory} from "./socket.handler.interface";

export class SocketHandlerService {

    public static handleSocketMessage(message: SocketMessage): void {
        const handler = SocketHandlerFactory.getHandler(message.type);
        if (handler) {
            handler.handle(message);
        }
    }

}
