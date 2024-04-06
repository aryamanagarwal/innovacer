import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class InnovacerMessageProcessorFramework {
    private LinkedBlockingQueue<Message> messagesQ;
    private ExecutorService executorService;
    public InnovacerMessageProcessorFramework(int numOfThreads) {
        executorService = Executors.newFixedThreadPool(numOfThreads);
        messagesQ = new LinkedBlockingQueue<Message>();
        for (int i=0;i<numOfThreads;i++){
            executorService.execute(new IMessageProcessor(messagesQ));
        }
    }
    public void addMessage(Message message){
        try {
            messagesQ.put(message);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void exit(){
        executorService.shutdown();
    }
    public static void main(String[] args) {
        InnovacerMessageProcessorFramework innovacerMessageProcessorFramework = new InnovacerMessageProcessorFramework(5);
        int numOfMessages = 10;

        for(int i=0;i<numOfMessages;i++){
            Message message = new Message("Message " + i);
            innovacerMessageProcessorFramework.addMessage(message);
        }
        innovacerMessageProcessorFramework.exit();
    }
}