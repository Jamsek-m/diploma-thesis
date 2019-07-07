import {MonitorState} from "../configuration";

export function execSessionFunc(func: () => void) {
    if (!MonitorState.getMonitorState().socketSessionId) {
        setTimeout(() => {
            execSessionFunc(func);
        }, 1000);
    } else {
        func();
    }
}
