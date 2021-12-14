/*
 * (c) 2014 UL TS BV
 */
package com.ul;

public class Message implements Comparable<Message> {

    public enum Priority {

        HIGH, MEDIUM, LOW // NOTE: review compareTo method when adding new priority levels
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

    @Override
    public int compareTo(Message message) {
        // NOTE: this compareTo method is efficient but only works with exactly 3 priority levels. If more priorities
        // are needed, this should be reviewed.
        if (this.getPriority() == message.getPriority()) {
            return 0;
        } else if (this.getPriority() == Priority.HIGH || message.getPriority() == Priority.LOW) {
            return -1;
        } else if (this.getPriority() == Priority.LOW || message.getPriority() == Priority.HIGH) {
            return 1;
        } else {
            throw new RuntimeException("Missing condition.");
        }
    }
}
