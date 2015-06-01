package sample;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by bousesause on 18/05/15.
 */
public class Server extends Thread
{

    ObjectOutputStream oos;
    private int port = 9876;
    private ServerSocket serverSocket;
    private Socket socket;
    private InetAddress host;
    private ArrayList<String> messagesList;


    public Server()
    {

        try
        {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            host = InetAddress.getLoopbackAddress();
            messagesList = new ArrayList<>();
                printIncomingMessage();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    public void printIncomingMessage()
    {
        try
            {
                int nRead;
                byte[] data = new byte[16384];
                InputStream ois = socket.getInputStream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();


                while ((nRead = ois.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                messagesList.add(new String (buffer.toByteArray()));
                System.out.println("Message Recived: " + messagesList.get(messagesList.size() - 1));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        //printIncomingMessage();
    }

}
