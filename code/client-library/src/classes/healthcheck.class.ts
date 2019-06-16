export interface Healthcheck {
    /**
     * Name of a healthcheck.
     */
    name: string;
    /**
     * Outcome of a healthcheck. Returns UP or DOWN.
     */
    state: string;
}

export interface HealthStatus {
    /**
     * Outcome of the healtcheck. Returns UP or DOWN.
     */
    outcome: string;
    /**
     * Outcomes of separate healthchecks.
     */
    checks: Healthcheck[];
}
