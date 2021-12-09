/*
 * (c) 2014 UL TS BV
 */
package com.ul;

public class Message {

    public enum Priority {

        HIGH, MEDIUM, LOW
    }

    private long timestamp;
    private Priority priority;
    private String text;

    public Message(long timestamp, Priority priority, String text) {
        this.timestamp = timestamp;
        this.priority = priority;
        this.text = text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getText() {
        return text;
    }
}
