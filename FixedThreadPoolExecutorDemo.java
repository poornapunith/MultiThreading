import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExecutorDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName()+ " is running.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        try(ExecutorService executor = Executors.newFixedThreadPool(3)) {
            for (int i = 0; i < 10; i++) {
                executor.execute(t1);
            }
        }
    }
}
