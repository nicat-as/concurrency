package az.developia.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private Lock lock = new ReentrantLock();
    private int number;

    public int add() {
        try {
            lock.lock();
            return ++this.number;
        } finally {
            lock.unlock();
        }
    }
}
