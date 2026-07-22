package grail.concurrency.classes;

import grail.concurrency.interfaces.BroadcastingClearanceManager;
import util.annotations.ComponentWidth;
import util.annotations.Row;

public class ABroadcastingClearanceManager extends AClearanceManager
        implements BroadcastingClearanceManager {
    private static final int CONTROL_WIDTH = 150;

    @Override
    @Row(1)
    @ComponentWidth(CONTROL_WIDTH)
    public synchronized void proceedAll() {
        this.notifyAll();
    }
}
