package az.developia;

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        var myRunnable = new MyRunnable();
        System.out.println("Main method " + Thread.currentThread().getName());
        var task = new Thread(myRunnable);
        task.start();
        task.join();
        var task2 = new Thread(myRunnable);
        task2.start();
        task2.join();
        System.out.println("main ended");
    }
}
