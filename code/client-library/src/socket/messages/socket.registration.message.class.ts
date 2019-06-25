import {SocketMessage, SocketMessageType} from "./socket.message.class";

export class SocketRegistrationRequestMessage extends SocketMessage {

    private applicationName: string;

    constructor(applicationName: string) {
        super();
        this.type = SocketMessageType.REGISTRATION_REQUEST;
        this.applicationName = applicationName;
    }

}

export class SocketRegistrationResponseMessage extends SocketMessage {

    private sessionId: string;

    constructor() {
        super();
        this.type = SocketMessageType.REGISTRATION_RESPONSE;
    }

    public getSessionId(): string {
        return this.sessionId;
    }

}
