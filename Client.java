package sample;



import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Random;

/**
 */
public class Client
{

    private ChatBox chatBox;
    private ConnectionHandler connectionHandler;

    private Socket socket;
    private ServerSocket serverSocket;
    private OutputStream os;
    private int id = generateID();



    public Client(String ipAdd, int port)
    {
        try
        {
            socket = new Socket(ipAdd, port);
            os = socket.getOutputStream();
            connectionHandler = new ConnectionHandler(socket);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        chatBox = new ChatBox();
        Runnable myRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                while(true)
                    messageHandler();
            }
        };
        myRunnable.run();
        connectionHandler.run();

    }


    private void sender(String chatInput)
    {
        try
        {

            //-----SENDS ID-----

            os.write(id);

            //-----SENDS MESSAGE-----
            byte[] b = chatInput.getBytes(Charset.forName("UTF-8"));
            os.write(b);
            System.out.println("Message Sent: " + chatInput);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private int generateID()
    {
        Random randomGenerator = new Random();
        int ID = randomGenerator.nextInt(10000);
        return ID;
    }

    private void messageHandler()
    {
            if (chatBox.messageTrigger == true)
            {
                sender(chatBox.getChatInput());
                System.out.println("ca'''''''");
                chatBox.setChatDisplay(chatBox.getChatInput());
                chatBox.messageTrigger = false;

            }
        }



}


