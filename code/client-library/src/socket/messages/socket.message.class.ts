export enum SocketMessageType {
    REGISTRATION_REQUEST = "REGISTRATION_REQUEST",
    REGISTRATION_RESPONSE = "REGISTRATION_RESPONSE",
    SESSION = "SESSION",
}

export class SocketMessage {
    public type: SocketMessageType;
}
