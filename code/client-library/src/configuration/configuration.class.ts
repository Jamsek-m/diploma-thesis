import {LoggerLevel} from "../log";

export class Configuration {
    public serverUrl: string = null;
    public mode?: "capture" | "read";
    public log?: LoggerLevel;
}
