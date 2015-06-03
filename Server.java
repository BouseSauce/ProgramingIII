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

    private int port;
    private ServerSocket serverSocket;
    private Socket socket;


    public Server(int aPort)
    {
        port = aPort;
        try
        {
            serverSocket = new ServerSocket(port);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
        listener();
        postMessage();

    }

    public void listener()
    {
     while(true)
     {
         try
         {
             socket = serverSocket.accept();
             Runnable connectionHandler = new ConnectionHandler(socket);
             new Thread(connectionHandler).start();


         } catch (Exception e)
         {
             e.printStackTrace();
         }

     }


    }

    public void postMessage()
    {
       while (true)
        {
            String s = "0hbhg";
            int id = 12345;
            OutputStream os;
            try
            {
                //-----SENDS ID-----

                socket = new Socket("127.0.0.1", 1076);
                os = socket.getOutputStream();
                os.write(id);

                //-----SENDS MESSAGE-----

                byte[] b = s.getBytes(Charset.forName("UTF-8"));
                os.write(b);
                System.out.println("Message was sent from server: ");
                socket.close();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
