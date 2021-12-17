package az.developia;

public class UnhandledException {
    public static void main(String[] args) {
//        Thread.currentThread().setUncaughtExceptionHandler(new MyHandler());
        throw new RuntimeException("throw exception for testing");
    }

    public static class MyHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName() + " thread is handled by MyHandler");
            e.printStackTrace();
        }
    }
}
