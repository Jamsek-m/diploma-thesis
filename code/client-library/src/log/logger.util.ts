import set = Reflect.set;

export type LoggerLevel = "debug" | "info" | "error";

export class Logger {

    private static logLevels = {
        debug: 0,
        info: 10,
        error: 20,
    };

    private static logLevel: LoggerLevel;

    public static initialize(logLevel: LoggerLevel): void {
        Logger.logLevel = logLevel;
    }

    public static debug(message?: any, ...optionalParams: any[]) {
        if (Logger.isAllowedLevel("debug")) {
            console.log(message, ...optionalParams);
        }
    }

    public static info(message?: any, ...optionalParams: any[]) {
        if (Logger.isAllowedLevel("info")) {
            console.log(message, ...optionalParams);
        }
    }

    public static error(message?: any, ...optionalParams: any[]) {
        if (Logger.isAllowedLevel("error")) {
            console.error(message, ...optionalParams);
        }
    }

    private static isAllowedLevel(level: LoggerLevel): boolean {
        const testLevel = Logger.logLevels[level];
        const setLevel = Logger.logLevels[Logger.logLevel];
        return setLevel <= testLevel;
    }
}
