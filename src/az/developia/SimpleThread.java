package az.developia;

public class SimpleThread implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId() + " | Running SimpleThread...");
    }
}
