package az.developia;

public class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("I'm running on thread " + Thread.currentThread().getName());
    }
}
