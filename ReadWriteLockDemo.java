import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static int counter =0;

    private static ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            Thread readers = new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 3; j++) {
                        readWriteLockDemo.reading();
                    }
                }
            });
            readers.setName("Reader"+i);
            readers.start();
        }

        Thread writer = new Thread(new Runnable() {
            public void run() {
                for (int j = 0; j < 3; j++) {
                    readWriteLockDemo.writing();
                }
            }
        });
        writer.setName("Writer");
        writer.start();


    }

    public void reading()
    {
        try {
            lock.readLock().lock();
            System.out.println("Reading : "+counter+" by "+Thread.currentThread().getName());
        } finally {
            lock.readLock().unlock();
        }
    }

    public void writing()
    {
        try{
            lock.writeLock().lock();
            counter++;
            System.out.println("Writing : "+counter+" by "+Thread.currentThread().getName());
        }
        finally {
            lock.writeLock().unlock();
        }
    }

}

