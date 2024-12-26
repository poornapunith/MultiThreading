import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolExecutorDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName()+" Running...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        try (ExecutorService executor = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 10; i++) {
                executor.execute(t1);
            }
        }
    }
}
