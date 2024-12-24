import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private static final ReentrantLock lock = new ReentrantLock();
    private static int sharedNumber =0;
    public static ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                reentrantLockDemo.methodA();
            }
        });
        t1.start();
    }

    public void methodA()
    {
        try {
            lock.lock();
            sharedNumber++;
            System.out.println("Method A : "+sharedNumber);
            methodB();
        } finally {
            lock.unlock();
        }
    }

    public void methodB()
    {
        try {
            lock.lock();
            sharedNumber--;
            System.out.println("Method B : "+sharedNumber);
//            System.out.println("Current Lock count : "+lock.getHoldCount());
        } finally {
            lock.unlock();
        }
    }
}
