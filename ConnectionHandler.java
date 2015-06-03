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

    public ConnectionHandler(Socket s)
    {
        socket = s;
    }

    public ConnectionHandler(ServerSocket s)
    {
        serverSocket = s;
    }

    @Override
    public void run()
    {

        try
        {
            int nRead;
            byte[] data = new byte[16384];
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int id;
            is = socket.getInputStream();
            id = is.read();

            while ((nRead = is.read(data, 0, data.length)) != -1)
            {
                buffer.write(data, 0, nRead);
            }

            String s = new String(buffer.toByteArray());
            if (!s.equals(""))
            {
                System.out.println(id + " : " + s);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }


}
