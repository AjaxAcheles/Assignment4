package grail.concurrency.interfaces;

public interface BroadcastingClearanceManager extends ClearanceManager {
    void proceedAll();
}
