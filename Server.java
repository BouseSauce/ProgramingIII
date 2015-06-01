package sample;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    private InputStream is;


    public Server()
    {

        try
        {
            serverSocket = new ServerSocket(port);


            host = InetAddress.getLoopbackAddress();
            Thread thread = new Thread() {
                public void run(){
                    printIncomingMessage();
                }
            };
            thread.run();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    public void printIncomingMessage()
    {
     while (true)
     {
         try
         {
             socket = serverSocket.accept();
             is = socket.getInputStream();
             int nRead;
             byte[] data = new byte[16384];
             ByteArrayOutputStream buffer = new ByteArrayOutputStream();

             while ((nRead = is.read(data, 0, data.length)) != -1)
             {
                 buffer.write(data, 0, nRead);
             }
             String s = new String(buffer.toByteArray());
             socket.close();
             if (!s.equals(""))
             {
                 System.out.println("Message Recived: " + s);
             }
         } catch (Exception e)
         {
             e.printStackTrace();
         }

     }

    }

}
