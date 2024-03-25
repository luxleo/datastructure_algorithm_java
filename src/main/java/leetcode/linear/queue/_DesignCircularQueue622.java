package leetcode.linear.queue;

public class _DesignCircularQueue622 {
    private int length;
    // front , rear index of circular queue
    private int front=0,rear =-1;
    private int[] circularQueue;

    public boolean enQueue(int value) {
        if(this.isFull())
            return false;
        rear = (rear + 1) % circularQueue.length;
        circularQueue[rear] = value;
        this.length++;
        return true;
    }

    public boolean deQueue() {
        if(this.isEmpty())
            return false;
        front = (front + 1) % circularQueue.length;
        this.length--;
        return true;
    }

    private boolean isEmpty() {
        return this.length == 0;
    }

    private boolean isFull() {
        return this.length == this.circularQueue.length;
    }
}
