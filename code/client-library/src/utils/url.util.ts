import {ConfigService} from "../configuration";

export class UrlUtil {

    public static getCurrentPathname(): string {
        return UrlUtil
            .mapPathWithParameters(typeof window !== "undefined" ? window.location.pathname : "#");
    }

    public static mapPathWithParameters(pathname: string): string {
        pathname = UrlUtil.normalizePath(pathname);

        if (pathname === "/" || pathname === "#") {
            return pathname;
        }

        const mappers = ConfigService.getConfig().urlsWithParameters;
        if (!mappers || mappers.length === 0) {
            return pathname;
        }

        // check for every mapper
        for (const mapper of mappers) {

            if (mapper === pathname) {
                return pathname;
            }

            const mapperParts = mapper.split("/");
            const pathnameParts = pathname.split("/");

            // if they are not same length, they can't be the same
            if (mapperParts.length === pathnameParts.length) {

                let pathMapped = false;
                // tslint:disable-next-line:prefer-for-of
                for (let i = 0; i < mapperParts.length; i++) {

                    if (mapperParts[i] === pathnameParts[i]) {
                        pathMapped = true;
                    } else if (mapperParts[i].startsWith("{") && mapperParts[i].endsWith("}") && pathnameParts[i]) {
                        // if part of mapper is variable then it matches pathname
                        pathMapped = true;
                    } else {
                        // parts are not the same, nor is there variable - mapper is not applied.
                        pathMapped = false;
                        break;
                    }
                }

                if (pathMapped) {
                    return mapper;
                }
            }
        }
        // no mapper was used, return path as is.
        return pathname;
    }

    /**
     * Normalizes given pathname for uniform representation of urls.
     * @param path to be normalized
     */
    public static normalizePath(path: string): string {
        if (path === "/" || path === "#") {
            return path;
        }

        if (path.endsWith("/")) {
            path = path.substring(0, path.length - 1);
        }
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return path;
    }

}
