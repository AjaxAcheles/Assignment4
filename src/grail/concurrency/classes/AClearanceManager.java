package grail.concurrency.classes;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import grail.concurrency.interfaces.ClearanceManager;
import util.annotations.ComponentWidth;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.models.AListenableVector;
import util.models.ListenableVector;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"WaitingThreads"})
@EditablePropertyNames({})
public class AClearanceManager implements ClearanceManager {
    private static final int CONTROL_WIDTH = 150;

    private final ListenableVector<String> waitingThreads;
    private final List<PropertyChangeListener> propertyChangeListeners;

    public AClearanceManager() {
        this.waitingThreads = new AListenableVector<String>();
        this.propertyChangeListeners = new ArrayList<PropertyChangeListener>();
    }

    @Override
    @Row(0)
    @ComponentWidth(CONTROL_WIDTH)
    public synchronized void proceed() {
        this.notify();
    }

    @Override
    public synchronized void waitForProceed() {
        String threadId = Thread.currentThread().toString();
        this.waitingThreads.addElement(threadId);
        try {
            this.wait();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        } finally {
            this.waitingThreads.removeElement(threadId);
        }
    }

    @Override
    @Row(2)
    public ListenableVector<String> getWaitingThreads() {
        return this.waitingThreads;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeListeners.add(listener);
    }
}
