import {SocketMessage, SocketMessageType} from "../messages";
import {RegistrationRequestHandler, RegistrationResponseHandler} from "./registration.handler.class";
import {SessionHandler} from "./session.handler.class";

export interface SocketHandler {

    handle(message: SocketMessage): void;

}

export class SocketHandlerFactory {

    public static getHandler(messageType: SocketMessageType): SocketHandler {
        switch (messageType) {
            case SocketMessageType.REGISTRATION_REQUEST:
                return new RegistrationRequestHandler();
            case SocketMessageType.REGISTRATION_RESPONSE:
                return new RegistrationResponseHandler();
            case SocketMessageType.SESSION:
                return new SessionHandler();
            default:
                return null;
        }
    }

}
