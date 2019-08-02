import {BrowserFeatures} from "../bootstrap/feature.detector";

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
    public startingApplication: boolean;
    public socketSessionId: string;
    public browserFeatures: BrowserFeatures;

    constructor() {
        this.serverConnected = false;
        this.socketConnected = false;
        this.startingApplication = true;
        this.socketSessionId = null;
    }

    public setSocketSessionId(sessionId: string): void {
        this.socketSessionId = sessionId;
    }

    public setStartedApplication(): void {
        this.startingApplication = false;
    }

    public setBrowserFeatures(features: BrowserFeatures): void {
        this.browserFeatures = features;
    }
}
