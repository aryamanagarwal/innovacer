import java.util.concurrent.LinkedBlockingQueue;

public class IMessageProcessor implements Runnable{
    private LinkedBlockingQueue<Message> messagesQ;
    public IMessageProcessor(LinkedBlockingQueue<Message> messagesQ){
        this.messagesQ = messagesQ;
    }
    private void processMessage(Message message){
        System.out.println("Processing Message : "+ message.getData());
    }
    @Override
    public void run() {
        while (true){
            Message message = null;
            try {
                message = messagesQ.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            processMessage(message);
        }
    }
}
