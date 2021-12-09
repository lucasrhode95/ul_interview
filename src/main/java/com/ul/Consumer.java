/*
 * (c) 2014 UL TS BV
 */
package com.ul;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class Consumer {
    private final int BUFFER_SIZE = 10;

    private BlockingQueue<Message> queue;
    private Thread consumerThread = null;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");

    private ArrayList<Message> buffer = new ArrayList<>();

    public Consumer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    private void formatAndLog(Message message) {
        String date = this.simpleDateFormat.format(new Date(message.getTimestamp()));
        String output = String.format("%s [%s]: %s", date, message.getPriority(), message.getText());
        System.out.println(output);
    }

    public void startConsuming() {
        consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Message message = queue.take();
                        Consumer.this.addToBuffer(message);
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

    private void flush() {
        Collections.sort(buffer);
        for (Message message : buffer) {
            formatAndLog(message);
        }
        buffer.clear();
    }

    private void addToBuffer(Message message) {
        buffer.add(message);
        if (buffer.size() >= BUFFER_SIZE) {
            flush();
        }
    }
}
