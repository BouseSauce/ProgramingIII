package sample;



import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Random;

/**
 */
public class Client
{

    private ConnectionHandler connectionHandler;

    private Socket socket;
    private OutputStream os;
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

        System.out.println(this.toString());
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

    public void messageHandler(String a)
    {
        sender(a);
    }


}


