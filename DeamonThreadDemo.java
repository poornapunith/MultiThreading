public class DeamonThreadDemo {
    public static void main(String[] args) {
        System.out.println("Main Thread Started");
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("Deamon Thread started");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Deamon Thread stopped");
            }
        });
        t1.setDaemon(true);
        t1.start();
        System.out.println("Main Thread Stopped");
    }
}
