import {MonitorState} from "../configuration";

/**
 * Holds execution of given function until session id is received from server.
 * @param func to be executed when session is set
 */
export function execSessionFunc(func: () => void) {
    if (!MonitorState.getMonitorState().socketSessionId) {
        setTimeout(() => {
            execSessionFunc(func);
        }, 1000);
    } else {
        func();
    }
}
