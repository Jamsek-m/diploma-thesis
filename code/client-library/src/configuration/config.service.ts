import {Configuration} from "./configuration.class";

export class ConfigService {

    private static configuration: Configuration = null;

    private static defaultConfig: Configuration = {
        applicationName: "",
        serverUrl: "",
        log: "info",
        silentFail: true,
        mode: "capture",
    };

    public static setConfiguration(configuration: Configuration): void {
        if (ConfigService.configuration === null) {
            ConfigService.configuration = ConfigService.mergeConfig(configuration);
        }
    }

    /**
     * Get configuration object passed by user.
     * @returns <tt>Configuration object</tt>
     */
    public static getConfig(): Configuration {
        return ConfigService.configuration;
    }

    private static mergeConfig(config: Configuration): Configuration {
        Object.keys(ConfigService.defaultConfig).forEach(key => {
            if ((!(config as any)[key] || (config as any)[key] === "") && (config as any)[key] !== false) {
                (config as any)[key] = (ConfigService.defaultConfig as any)[key];
            }
        });
        return config;
    }

}
