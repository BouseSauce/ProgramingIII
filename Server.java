package sample;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by bousesause on 18/05/15.
 */
public class Server
{

    ConnectionHandler connectionHandler;
    private int port;
    private ServerSocket serverSocket;
    private Socket socket;


    public Server(int aPort)
    {
        port = aPort;
        try
        {
            serverSocket = new ServerSocket(port);
            connectionHandler = new ConnectionHandler(serverSocket);
            connectionHandler.run();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }


        //postMessage();

    }

    public void postMessage()
    {
       while (true)
        {
            String s = connectionHandler.getMessage();
            int id = connectionHandler.getId();
            OutputStream os;
            try
            {
                //-----SENDS ID-----

                socket = new Socket("127.0.0.1", 9875);
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
