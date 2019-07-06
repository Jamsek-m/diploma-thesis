import {MonitorState} from "../../configuration";
import {Logger} from "../../log";
import {SocketMessage, SocketRegistrationResponseMessage} from "../messages";
import {SocketHandler} from "./socket.handler.interface";

export class RegistrationRequestHandler implements SocketHandler {

    public handle(message: SocketMessage): void {

    }

}

export class RegistrationResponseHandler implements SocketHandler {

    public handle(message: SocketMessage): void {
        const state = MonitorState.getMonitorState();
        const msg = Object.assign(new SocketRegistrationResponseMessage(), message);
        const sesId = msg.getSessionId();
        state.setSocketSessionId(sesId);
        Logger.info(`Session was registered with id '${MonitorState.getMonitorState().socketSessionId}'`);
    }

}
