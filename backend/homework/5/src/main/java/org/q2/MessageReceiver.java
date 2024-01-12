package org.q2;

public class MessageReceiver implements Runnable{
    private final MessageQueue messageQueue;
    public MessageReceiver(MessageQueue messageQueue){
        this.messageQueue=messageQueue;
    }

    @Override
    public void run() {
        messageQueue.removeMessage();
    }
}
