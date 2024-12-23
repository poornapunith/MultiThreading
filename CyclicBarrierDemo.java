import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static int stages = 3;
    public static int tourists = 5;
    public static CyclicBarrier barrier = new CyclicBarrier(tourists,() ->
    {
        System.out.println("Guide is speaking..!");
    });

    public static void main(String[] args) {
        for (int i = 0; i < tourists; i++) {
            Thread thread = new Thread(new Touring(i));
            thread.start();
        }
    }


    static class Touring implements Runnable {
        private int id;
        public Touring(int id) {
            this.id = id;
        }

        public void run() {
            for (int i = 1; i <= stages; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Tourist : "+id+" reached at Stage : "+i);
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
