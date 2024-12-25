import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static Semaphore semaphore = new Semaphore(3);
    public static void main(String[] args) {
        SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    semaphoreDemo.check(finalI);
                }
            });
            thread.start();

        }
    }

    public void check(int i)
    {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println("Checking "+i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
