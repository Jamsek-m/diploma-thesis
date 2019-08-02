
export interface BrowserFeatures {
    [featureName: string]: boolean;
}

export class FeatureDetector {

    public static detectFeatures(): BrowserFeatures {
        const features: BrowserFeatures = {};

        if (performance && typeof performance !== "undefined") {

            features["timeOrigin"] = !!performance.timeOrigin;

            features["timing"] = !!performance.timing;

            if (performance.timing) {
                features["navigationStart"] = !!performance.timing.navigationStart;
            }

            features["entriesByType"] = !!performance.getEntriesByType("resource");
        }
        return features;
    }

}
