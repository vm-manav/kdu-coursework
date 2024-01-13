package org.q2;

import org.Logger;

import java.util.ArrayList;

public class MessageQueue {
    private int index=0;
    private final ArrayList<Message> messages=new ArrayList<>();

    private int getIndex(){
        return index;
    }
    private void incrementIndex(){
        this.index=this.index+1;
    }
    public synchronized void addMessage(Message message){
        messages.add(message);
        if(getIndex()< messages.size()){
            notifyAll();
        }
    }
    private Message getElement(int index){
        return messages.get(index);
    }
    public synchronized void removeMessage()  {
        while (getIndex()>=messages.size()){
            try {
                wait();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        Message message=getElement(getIndex());
        incrementIndex();
        Logger.infoMessage(message.getMessage());
    }
}
