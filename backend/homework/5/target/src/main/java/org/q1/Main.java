package org.q1;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
            MessageQueue messageQueue=new MessageQueue();
            MessageSender message1=new MessageSender("Message 1",messageQueue);
            Thread t1=new Thread(message1);
            t1.start();
            MessageSender message2=new MessageSender("Message 2",messageQueue);
            Thread t2=new Thread(message2);
            t2.start();
            MessageSender message3=new MessageSender("Message 3",messageQueue);
            Thread t3=new Thread(message3);
            t3.start();

            MessageReceiver receiveMessage1=new MessageReceiver(messageQueue);
            Thread t4=new Thread(receiveMessage1);
            t4.start();

            MessageReceiver receiveMessage2=new MessageReceiver(messageQueue);
            Thread t5=new Thread(receiveMessage2);
            t5.start();

            MessageReceiver receiveMessage3=new MessageReceiver(messageQueue);
            Thread t6=new Thread(receiveMessage3);
            t6.start();


        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
    }
    }
