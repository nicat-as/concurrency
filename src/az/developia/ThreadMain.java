package az.developia;

public class ThreadMain {
    public static void main(String[] args) {
        SimpleThread simpleThread = new SimpleThread();
        Thread task = new Thread(simpleThread);
        task.start();

        task.stop();
    }
}
