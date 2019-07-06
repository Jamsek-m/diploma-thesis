import {MouseRecord} from "../../classes/mouse.record.interface";
import {ConfigService, MonitorState} from "../../configuration";
import {SocketMessage, SocketMessageType} from "./socket.message.class";

export enum SocketSessionType {
    MOUSE_TRACK = "MOUSE_TRACK",
    PING = "PING",
}

export class SessionSocketMessage extends SocketMessage {

    protected sessionId: string;
    protected application: string;
    protected sessionType: SocketSessionType;

    constructor() {
        super();
        this.type = SocketMessageType.SESSION;
        this.sessionId = MonitorState.getMonitorState().socketSessionId;
        this.application = ConfigService.getConfig().applicationName;
    }
}

export class MouseTrackMessage extends SessionSocketMessage {

    public coordinates: MouseRecord[];

    constructor() {
        super();
        this.sessionType = SocketSessionType.MOUSE_TRACK;
    }
}

export class SessionPingMessage extends SessionSocketMessage {
    constructor() {
        super();
        this.sessionType = SocketSessionType.PING;
    }
}
