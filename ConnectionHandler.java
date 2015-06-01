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

    public ConnectionHandler(Socket s)
    {
        socket = s;
    }

    @Override
    public void run()
    {

        try
        {
            is = socket.getInputStream();
            int id = 0;
            id = is.read();
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
                System.out.println(id + " : " + s);
                //postMessage(id, s);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
