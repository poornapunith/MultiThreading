import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayDemo {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0));
        Thread producer1 = new Thread(new Producer(list));
        Thread producer2 = new Thread(new Producer(list));
        Thread consumer = new Thread(new Consumer(list));
        producer1.start();
        producer2.start();
        consumer.start();

    }
}

class Producer implements Runnable {
    List<Integer> list;
    Random random = new Random();
    Producer(List<Integer> list)
    {
        this.list=list;
    }
    public void run() {
        while(true)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.set(random.nextInt(list.size()), random.nextInt(10));
        }

    }
}

class Consumer implements Runnable {
    List<Integer> list;
    Consumer(List<Integer> list)
    {
        this.list=list;
    }

    public void run() {
        while(true)
        {
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(list);
        }
    }
}