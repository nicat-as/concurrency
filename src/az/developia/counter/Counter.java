package az.developia.counter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private AtomicInteger number = new AtomicInteger(0);
    ThreadLocal<String> threadLocalString;

    public Counter() {
        this.threadLocalString = new ThreadLocal<>();
        this.threadLocalString.set("Initialize");
    }

    public synchronized int add() {
        return number.addAndGet(1);
    }

    public void print(){
        System.out.println(this.threadLocalString.get());
    }
    public void set(){
        this.threadLocalString.set("Thread is" + Thread.currentThread().getId());
    }
}
