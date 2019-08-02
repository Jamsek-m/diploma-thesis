import {MonitorState} from "../configuration";
import {SocketService} from "../socket";
import {execSessionFunc} from "../socket/exec.session.func";
import {ResourceLoadMessage} from "../socket/messages/session.socket.message.class";

export class ResourceTracker {

    public static trackResources(): void {
        if (MonitorState.getMonitorState().browserFeatures["entriesByType"]) {
            const entries: PerformanceEntryList = performance.getEntriesByType("resource");
            entries.forEach((entry: PerformanceEntry) => {
                const resourceEntry = entry as PerformanceResourceTiming;
                execSessionFunc(() => {
                    const message = ResourceTracker.collectEntry(resourceEntry);
                    SocketService.sendMessage(message);
                });
            });
        }
    }

    private static collectEntry(entry: PerformanceResourceTiming): ResourceLoadMessage {
        const message = new ResourceLoadMessage();

        message.name = entry.name;
        message.resourceType = entry.initiatorType;
        message.decodedBodySize = entry.decodedBodySize;
        message.encodedBodySize = entry.encodedBodySize;
        message.transferSize = entry.transferSize;
        message.redirectTime = entry.redirectEnd - entry.redirectStart;
        message.DNSTime = entry.domainLookupEnd - entry.domainLookupStart;
        message.TCPHandleTime = entry.connectEnd - entry.connectStart;

        if (entry.secureConnectionStart > 0) {
            message.secureConnectionTime = entry.connectEnd - entry.secureConnectionStart;
        } else {
            message.secureConnectionTime = 0;
        }

        message.responseTime = entry.responseEnd - entry.responseStart;
        message.requestStartTime = entry.requestStart;
        message.responseEndTime = entry.responseEnd;

        return message;
    }

}
