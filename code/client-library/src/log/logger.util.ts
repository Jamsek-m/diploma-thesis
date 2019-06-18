/**
 * Allowed logger levels enum
 */
export type LoggerLevel = "debug" | "info" | "warning" | "error";

export class Logger {

    private static logLevels = {
        debug: 0,
        info: 10,
        warning: 20,
        error: 30,
    };

    private static logLevel: LoggerLevel;

    /**
     * Method to be called at library bootstrap to initialize logger object.
     * @param logLevel user set log level
     */
    public static initialize(logLevel: LoggerLevel): void {
        Logger.logLevel = logLevel;
    }

    public static debug(message?: any, ...optionalParams: any[]) {
        if (Logger.isAllowedLevel("debug")) {
            console.log("[MetricsMonitor] " + message, ...optionalParams);
        }
    }

    public static info(message?: any, ...optionalParams: any[]) {
        if (Logger.isAllowedLevel("info")) {
            console.log("[MetricsMonitor] " + message, ...optionalParams);
        }
    }

    public static error(message?: any, ...optionalParams: any[]) {
        if (Logger.isAllowedLevel("error")) {
            console.error("[MetricsMonitor] " + message, ...optionalParams);
        }
    }

    public static warn(message?: any, ...optionalParams: any[]) {
        if (Logger.isAllowedLevel("warning")) {
            console.warn("[MetricsMonitor] " + message, ...optionalParams);
        }
    }

    private static isAllowedLevel(level: LoggerLevel): boolean {
        const testLevel = Logger.logLevels[level];
        const setLevel = Logger.logLevels[Logger.logLevel];
        return setLevel <= testLevel;
    }
}
