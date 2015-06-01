package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by bousesause on 18/05/15.
 */
public class Server
{

    private int port = 9876;
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream is;


    public Server()
    {
        
        try
        {
            serverSocket = new ServerSocket(port);
            listener();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    public void listener()
    {
     while (true)
     {
         try
         {
             Socket clientSocket = serverSocket.accept();
             Runnable connectionHandler = new ConnectionHandler(clientSocket);
             new Thread(connectionHandler).start();

         } catch (Exception e)
         {
             e.printStackTrace();
         }

     }


    }

    public void postMessage(int id, String s)
    {
        while (!s.equals("quit"))
        {
            try

            {

                //-----SENDS ID-----
                OutputStream os;
                socket = new Socket("127.0.0.1", 8765);
                os = socket.getOutputStream();
                os.write(id);
                socket.close();
                //-----SENDS MESSAGE-----
                os = socket.getOutputStream();
                socket = new Socket("127.0.0.1", 8765);
                byte[] b = s.getBytes(Charset.forName("UTF-8"));
                os.write(b);
                System.out.println("Message Sent: " + s);
                socket.close();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
