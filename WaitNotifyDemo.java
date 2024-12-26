public class WaitNotifyDemo {
    private static final Object lock = new Object();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    if(i==5)
                    {
                        System.out.println("Reached 5 in T1, waiting");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else
                    {
                        System.out.println("Reached "+i+" in T1");
                    }
                }
            }

        });
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    if(i==5)
                    {
                        System.out.println("Reached 5 in T2, Notifying");
                        lock.notify();
                    }
                    else
                    {
                        System.out.println("Reached "+i+" in T2");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
