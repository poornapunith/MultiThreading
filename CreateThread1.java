public class CreateThread1{
    public static void main(String[] args) {
        Thread t1 = new Thread1();
        t1.start();
    }
}

class Thread1 extends Thread{
    public void run(){
        System.out.println("thread1 is running");
    }
}