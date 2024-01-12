package org.q2;

public class MessageSender implements Runnable{

    private final String message;
    private final MessageQueue messageQueue;

    public MessageSender(String message, MessageQueue messageQueue){
        this.message=message;
        this.messageQueue=messageQueue;
    }
    @Override
    public void run() {
        Message messageObject=new Message();
        messageObject.setMessage(message);
        messageQueue.addMessage(messageObject);
    }
}
