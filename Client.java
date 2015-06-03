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
    private Socket socket;
    private ServerSocket serverSocket;
    private OutputStream os;
    private ChatBox chatBox;
    private int id = generateID();


    public Client(String ipAdd, int port)
    {
        try
        {
            socket = new Socket(ipAdd, port);
            os = socket.getOutputStream();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        chatBox = new ChatBox();
        while(true)
        {
            if (chatBox.getSet == true)
            {
                sendMessage(chatBox.getChatInput());
                chatBox.setChatDisplay(chatBox.getChatInput());
                chatBox.getSet = false;

            }
        }
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
            socket.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void readFromPort()
    {
            try
            {
                serverSocket = new ServerSocket(1076);
                socket = serverSocket.accept();
                Runnable connectionHandler = new ConnectionHandler(socket);
                new Thread(connectionHandler).start();
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

    public void sendMessage(String s)
    {
        sender(s);
    }

}


