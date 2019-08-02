import {MouseRecord} from "../../classes/mouse.record.interface";
import {ConfigService, MonitorState} from "../../configuration";
import {SocketMessage, SocketMessageType} from "./socket.message.class";

export enum SocketSessionType {
    MOUSE_TRACK = "MOUSE_TRACK",
    PING = "PING",
    APP_STARTUP = "APP_STARTUP",
    PAGE_LOAD = "PAGE_LOAD",
    RESOURCE_LOAD = "RESOURCE_LOAD",
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

export class AppStartupMessage extends SessionSocketMessage {

    public navigationStart: number;
    public applicationLoaded: number;

    constructor() {
        super();
        this.sessionType = SocketSessionType.APP_STARTUP;
    }
}

export class PageLoadMessage extends SessionSocketMessage {
    public loadStart: number;
    public loadEnd: number;
    public pathname: string;
    public firstPage: boolean;

    constructor() {
        super();
        this.sessionType = SocketSessionType.PAGE_LOAD;
    }
}

export class ResourceLoadMessage extends SessionSocketMessage {
    public resourceType: string;
    public decodedBodySize: number;
    public encodedBodySize: number;
    public transferSize: number;
    public redirectTime: number;
    public DNSTime: number;
    public TCPHandleTime: number;
    public secureConnectionTime: number;
    public responseTime: number;
    public requestStartTime: number;
    public responseEndTime: number;
    public name: string;

    constructor() {
        super();
        this.sessionType = SocketSessionType.RESOURCE_LOAD;
    }
}
