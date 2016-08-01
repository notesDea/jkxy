// IMyService.aidl
package com.jkxy.serviceserviceservice;

// Declare any non-default types here with import statements
import com.jkxy.serviceserviceservice.TimerServiceCallback;

interface IMyService {
    void registCallback(TimerServiceCallback callback);
    void unregistCallback(TimerServiceCallback callback);
    void isRun(boolean canRun);
}
