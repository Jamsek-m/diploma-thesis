export class MonitorState {

    private static stateInstance: MonitorState = null;

    public static getMonitorState(): MonitorState {
        if (MonitorState.stateInstance === null) {
            MonitorState.stateInstance = new MonitorState();
        }
        return MonitorState.stateInstance;
    }

    public serverConnected: boolean;
    public socketConnected: boolean;
    public socketSessionId: string;

    constructor() {
        this.serverConnected = false;
        this.socketConnected = false;
        this.socketSessionId = null;
    }

    public setSocketSessionId(sessionId: string): void {
        this.socketSessionId = sessionId;
    }
}
