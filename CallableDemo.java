import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            Future<Integer> data = executor.submit(new CallableThread());
            System.out.println("Received from Callable Thread : "+data.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class CallableThread implements Callable<Integer>
{
    @Override
    public Integer call() throws Exception {
        return 5;
    }
}
