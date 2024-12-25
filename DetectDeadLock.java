import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DetectDeadLock {
    public static void main(String[] args) {
        Lock lockA = new ReentrantLock();
        Lock lockB = new ReentrantLock();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                lockB.lock();
                System.out.println("Locked B in T1");
                for (int i = 0; i < 10; i++) {

                }
                lockA.lock();
                System.out.println("Locked A in T1");
                lockA.unlock();
                lockB.unlock();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                lockA.lock();
                System.out.println("Locked A in T2");
                for (int i = 0; i < 10; i++) {

                }
                lockB.lock();
                System.out.println("Locked B in T2");
                lockB.unlock();
                lockA.unlock();
            }
        });

        t1.start();
        t2.start();

//        To list all running Process use "jps -l" command
//        Now for Thread dump run "kill -3 {processId}" for Linux or mac
//        "jstack {processId}" for windows cmd or power shell
//        At ending of logs we can see dead lock related logs

    }
}
