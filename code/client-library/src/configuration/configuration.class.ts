import {LoggerLevel} from "../log";

/**
 * Allowed modes enum
 */
export type MetricsMonitorMode = "capture" | "heatmap";

/**
 * Configuration interface for configuring Metrics Monitor library.
 */
export interface Configuration {
    /**
     * Base url of a Metrics Monitor server
     */
    serverUrl: string;

    /**
     * Library working mode. Currently only 'capture' is supported.
     * @default capture
     */
    mode?: MetricsMonitorMode;

    /**
     * Logging level.
     * @default info
     */
    log?: LoggerLevel;

    /**
     * If false, the bootstrap will reject promise if it will not be able to connect to server.
     * Library uses silent fail as default value to minimize impact on user experience.
     * @default true
     */
    silentFail?: boolean;

    /**
     * Application name - unique identifier if you have more apps connected to same server.
     * @example
     * 'webstore-angular'
     */
    applicationName: string;

}
