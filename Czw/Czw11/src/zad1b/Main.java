package zad1b;

import java.util.concurrent.*;

public class Main {

	public static void main(String[] args) {

		ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

		ExecutorService es = Executors.newFixedThreadPool(2);
		es.submit(() -> {
			while (true) {
				try {
					Thread.sleep(((int) (Math.random() * 2)) * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int liczba = 0;
				try {
					liczba = blockingQueue.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Consumer get " + liczba);
			}
		});

		es.submit(() -> {
			while (true) {
				try {
					Thread.sleep(((int)(Math.random() * 2))*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int liczba = (int) (Math.random() * 100);

				try {
					blockingQueue.put(liczba);
					System.out.println("Producer put " + liczba);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		try { Thread.sleep(15000); }
		catch (InterruptedException ignored) {}

		System.exit(0);
		
	}

}
