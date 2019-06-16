import {Optional} from "../classes";
import {Configuration} from "./configuration.class";

export class ConfigurationUtil {

    private configuration: Configuration = null;
    private static instance: ConfigurationUtil = null;

    public static getInstance(): ConfigurationUtil {
        if (ConfigurationUtil.instance === null) {
            ConfigurationUtil.instance = new ConfigurationUtil();
            return ConfigurationUtil.instance;
        }
        return ConfigurationUtil.instance;
    }

    public _setConfiguration(configuration: Configuration): void {
        if (this.configuration === null) {
            this.configuration = configuration;
        }
    }

    public getConfig(key: string): Optional<string> {
        const option: string | undefined = (this.configuration as any)[key];
        if (option) {
            return Optional.withValue<string>(option);
        }
        return Optional.empty();
    }

}
