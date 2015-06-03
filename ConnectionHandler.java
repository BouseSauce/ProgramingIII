package sample;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by bousesause on 1/06/15.
 */
public class ConnectionHandler implements Runnable
{

    InputStream is;
    Socket socket;
    ServerSocket serverSocket;
    private String message;
    private int id;

    public ConnectionHandler(Socket s) throws IOException
    {
        socket = s;
    }

    public ConnectionHandler(ServerSocket s)
    {
        serverSocket = s;

            listener();

    }


    @Override
    public void run()
    {

        try
        {

            int nRead;
            byte[] data = new byte[16384];
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            is = socket.getInputStream();
            id = is.read();

            while ((nRead = is.read(data, 0, data.length)) != -1)
            {
                buffer.write(data, 0, nRead);
            }

            if (!new String(buffer.toByteArray()).equals(""))
            {
                message = new String(buffer.toByteArray());
                System.out.println("Message recived");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void listener()
    {
        while (true)
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
    public String getMessage()
    {
        return message;
    }

    public int getId()
    {
        return id;
    }

}
