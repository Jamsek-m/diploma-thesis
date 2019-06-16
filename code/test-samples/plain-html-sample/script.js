MetricsMonitor.MetricsMonitor.init({
    mode: "capture",
    serverUrl: "http://localhost:8080",
    log: "debug"
}).then(() => {
    console.log("Metrics Monitor is initialized!");
}).catch((err) => {
    console.error(err);
});
