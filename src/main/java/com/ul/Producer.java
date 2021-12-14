/*
 * (c) 2014 UL TS BV
 */
package com.ul;

import java.util.concurrent.BlockingQueue;

public class Producer {

    private BlockingQueue<Message> queue;
    private Thread producerThread = null;
    private int messageCounter = 0;

    public Producer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    public void startProducing() {
        producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (messageCounter != 100) {
                        Message message = MessageFactory.generateMessage(messageCounter);
                        queue.add(message);
                        messageCounter++;
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producerThread.start();
    }

    public void stopProducing() {
        producerThread.interrupt();
    }
}
