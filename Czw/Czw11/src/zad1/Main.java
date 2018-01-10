package zad1;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        Buffer buffer = new Buffer(10);

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(() -> {
            while (true) {
                int val = buffer.get();
                System.out.println("Consumer get " + val);
                try { Thread.sleep(((int) (Math.random() * 2)) * 1000); }
                catch (InterruptedException ignored) { }
            }
        });

        es.submit(() -> {
            while (true) {
                int wrt = (int) (Math.random() * 100);
                buffer.put(wrt);
                System.out.println("Producer put " + wrt);
                try { Thread.sleep(((int) (Math.random() * 2)) * 1000); }
                catch (InterruptedException ignored) { }
            }
        });

        try { Thread.sleep(15000); }
        catch (InterruptedException ignored) {}

        System.exit(0);
    }
}
