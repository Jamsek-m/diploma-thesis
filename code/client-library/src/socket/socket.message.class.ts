export enum SocketMessageType {
    REGISTRATION = "REGISTRATION",
}

export class SocketMessage {
    public type: SocketMessageType;
}

export class SocketRegistrationMessage extends SocketMessage {

}
