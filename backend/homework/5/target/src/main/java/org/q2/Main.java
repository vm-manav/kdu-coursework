package org.q2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

            ExecutorService executorService= Executors.newFixedThreadPool(6);
            MessageQueue messageQueue=new MessageQueue();

            MessageSender message1=new MessageSender("Message 1",messageQueue);
            executorService.submit(message1);

            MessageSender message2=new MessageSender("Message 2",messageQueue);
            executorService.submit(message2);

            MessageSender message3=new MessageSender("Message 3",messageQueue);
            executorService.submit(message3);

            MessageReceiver receiveMessage1=new MessageReceiver(messageQueue);
            executorService.submit(receiveMessage1);

            MessageReceiver receiveMessage2=new MessageReceiver(messageQueue);
            executorService.submit(receiveMessage2);

            MessageReceiver receiveMessage3=new MessageReceiver(messageQueue);
            executorService.submit(receiveMessage3);
            executorService.shutdown();
        }
    }
