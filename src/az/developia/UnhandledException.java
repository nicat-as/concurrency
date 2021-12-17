package az.developia;

public class UnhandledException {
    public static void main(String[] args) {
        var runnable = (Runnable) ()-> {
            throw new RuntimeException("throw exception for testing");
        };
        var task = new Thread(runnable);
        task.setUncaughtExceptionHandler(new MyHandler());
        task.start();
    }

    public static class MyHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getId() + " thread is handled by MyHandler");
            e.printStackTrace();
        }
    }
}
