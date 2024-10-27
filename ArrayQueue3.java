package love.seeyoufxp.queue;

import java.util.Iterator;

public class ArrayQueue3<E> implements Queue<E>, Iterable<E> {

    /*
        求模运算：
        - 如果除数是2的n次方
        - 那么被除数的后n位即为余数（模）
        - 求被除数的后n位的方法：与2^n-1按位与
     */

    private final E[] array;
    private int head = 0;
    private int tail = 0;

    @SuppressWarnings("all")
    public ArrayQueue3(int capacity) {
//        1.抛异常
//        if ((capacity & capacity - 1) != 0) {
//            throw new IllegalArgumentException("capacity should be a power of 2");
//        }
//        2.改成2^n
        int n = (int) (Math.log10(capacity - 1) / Math.log10(2)) + 1;
        capacity = 1 << n;
        array = (E[]) new Object[capacity];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E value = array[p & (array.length - 1)];
                p++;
                return value;
            }
        };
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
//        array[(int) Integer.toUnsignedLong(tail) % array.length] = value;
        array[tail & (array.length - 1)] = value;
        tail++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head & (array.length - 1)];
        head++;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head & (array.length - 1)];
    }

    @Override
    public boolean isEmpty() {
        if (head == tail) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isFull() {
        return tail - head == array.length;
    }
}
