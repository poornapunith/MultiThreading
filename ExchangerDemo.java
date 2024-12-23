import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        Thread t1 = new Thread(new ThreadOne(exchanger));
        Thread t2 = new Thread(new ThreadTwo(exchanger));
        t1.start();
        t2.start();

    }
}

class ThreadOne implements Runnable {
    Exchanger<Integer> exchanger = new Exchanger<>();

    public ThreadOne(Exchanger<Integer> ex) {
        this.exchanger = ex;
    }
    public void run() {
        int data =10;
        System.out.println("Sending from 1st Thread : " + data);
        try {
            data = exchanger.exchange(data);
            System.out.println("Received to 1st Thread : " + data);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class ThreadTwo implements Runnable {
    Exchanger<Integer> exchanger = new Exchanger<>();

    public ThreadTwo(Exchanger<Integer> ex) {
        this.exchanger = ex;
    }
    public void run() {
        int data =20;
        System.out.println("Sending from 2nd Thread : " + data);
        try {
            Thread.sleep(2000);
            data = exchanger.exchange(data);
            System.out.println("Received to 2nd Thread : " + data);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

