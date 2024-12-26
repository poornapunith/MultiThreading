import java.util.concurrent.CountDownLatch;

public class CountdownLatchDemo {
    private static final CountDownLatch countDownLatch = new CountDownLatch(3);
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Chef(1,countDownLatch)).start();
        new Thread(new Chef(2,countDownLatch)).start();
        new Thread(new Chef(3,countDownLatch)).start();
        countDownLatch.await();
        System.out.println("All Prepared, Ready to Serve..!");
    }
}

class Chef implements Runnable {
    int id;
    CountDownLatch countDownLatch;
    public Chef(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        System.out.println("Chef #" + id+" Started");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Chef #" + id+" Ended");
        countDownLatch.countDown();
    }
}
