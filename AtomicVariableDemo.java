import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariableDemo {
//    public static int counter =0;
    public static AtomicInteger counter = new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
//                    counter++;
                    counter.incrementAndGet();
                }

            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
//                    counter++;
                    counter.incrementAndGet();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter);
    }
}
