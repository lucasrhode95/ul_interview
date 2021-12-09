/*
 * (c) 2014 UL TS BV
 */
package com.ul;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
    private BufferedWriter fileWriter;

    public Consumer(BlockingQueue<Message> queue) {
        this.queue = queue;
        setupLogger();
    }

    private void setupLogger() {
        try {
            fileWriter = new BufferedWriter(new FileWriter("logs.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFormattedMessage(Message message) {
        String date = this.simpleDateFormat.format(new Date(message.getTimestamp()));
        return String.format("%s [%s]: %s\n", date, message.getPriority(), message.getText());
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
                        flush();
                        close();
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

    private void writeRaw(String message) {
        System.out.print(message);
        try {
            fileWriter.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void flush() {
        Collections.sort(buffer);
        for (Message message : buffer) {
            writeRaw(getFormattedMessage(message));
        }
        buffer.clear();

        try {
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeRaw("------ FLUSH -----\n");
    }

    private void close() {
        try {
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void addToBuffer(Message message) {
        buffer.add(message);
        if (buffer.size() >= BUFFER_SIZE) {
            flush();
        }
    }
}
