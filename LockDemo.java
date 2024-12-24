import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    private static final int MAX_SIZE = 5;
    Lock lock = new ReentrantLock();
    Condition QueueNotEmpty = lock.newCondition();
    Condition QueueNotFull = lock.newCondition();
    Queue<Integer> queue = new LinkedList<>();
    Random random = new Random();

    public static void main(String[] args) {
        LockDemo demo = new LockDemo();
        Thread producerOne = new Thread(() -> {
           for (int i = 0; i < 10; i++) {
               try {
                   Thread.sleep(1000);
                   demo.producer(i);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        });

        Thread consumerOne = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1500);
                    demo.consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        producerOne.start();
        consumerOne.start();
    }

    public void producer(int i) throws InterruptedException {
        lock.lock();
        try {
            if(queue.size()==MAX_SIZE)
            {
                QueueNotFull.await();
            }
            else
            {
                queue.offer(i);
                System.out.println("Produced : "+i);
                QueueNotEmpty.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void consumer() throws InterruptedException {
        lock.lock();
        try {
            if (queue.size() == 0) {
                QueueNotEmpty.await();
            }
            else
            {
                System.out.println("Consumed : "+queue.poll());
            }
        } finally {
            lock.unlock();
        }

    }
}
