/*
 * (c) 2014 UL TS BV
 */
package com.ul;

import java.util.concurrent.*;

public class Consumer {

    private BlockingQueue<Message> queue;
    private Thread consumerThread = null;

    public Consumer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    public void startConsuming() {
        consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Message message = queue.take();
                        System.out.println(message);
                    } catch (InterruptedException e) {
                        // executing thread has been interrupted, exit loop
                        break;
                    }
                }
            }
        });
        consumerThread.start();
    }

    public void stopConsuming() {
        consumerThread.interrupt();
    }
}
