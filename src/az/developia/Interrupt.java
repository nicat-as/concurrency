package az.developia;

public class Interrupt {
    public static void main(String[] args) {
        Runnable runnable = ()-> {
            try {
                System.out.println("Hello");
                if (Thread.currentThread().isInterrupted()){
                    Thread.interrupted();
                    throw new RuntimeException("interrupted");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        };
        var task = new Thread(runnable);
        task.start();
        task.interrupt();
    }
}
