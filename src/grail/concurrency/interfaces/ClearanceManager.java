package grail.concurrency.interfaces;

import util.models.ListenableVector;
import util.models.PropertyListenerRegisterer;

public interface ClearanceManager extends PropertyListenerRegisterer {
    void proceed();
    void waitForProceed();
    ListenableVector<String> getWaitingThreads();
}
