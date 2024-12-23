public class CreateThread2 {
    public static void main(String[] args) {

        new Thread(new Thread2()).start();

    }
}

class Thread2 implements Runnable {
    public void run() {
        System.out.println("Thread2 running...");
    }
}
