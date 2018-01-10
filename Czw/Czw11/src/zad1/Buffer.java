package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private List<Integer> buffer;
    private int size;

    private ReentrantLock locker;
    private Condition condition;

    Buffer(int size) {
        this.size = size;
        this.buffer = new ArrayList<>(size);

        this.locker = new ReentrantLock();
        this.condition = locker.newCondition();
    }

    public int get() {
        locker.lock();
        try {
            while (buffer.isEmpty()) condition.await();
            condition.signalAll();
            return buffer.remove(0);
        } catch (InterruptedException e) {
            return -1;
        } finally {
            locker.unlock();
        }
    }

    public void put(int n) {
        locker.lock();
        try {
            while (buffer.size() == size) condition.await();
            buffer.add(n);
            System.out.println("buffer size: " + buffer.size());
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }
}



