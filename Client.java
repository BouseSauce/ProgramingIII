package sample;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Random;

/**
 */
public class Client implements Runnable
{
    private Socket socket;
    private ServerSocket serverSocket;
    private OutputStream os;
    private InputStream is;
    private int id = generateID();


    public Client()
    {
        try
        {
            socket = new Socket("127.0.0.1", 9876);
            os = socket.getOutputStream();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void run() {
        readFromPort(8765);
    }


    private void sender(String chatInput)
    {
        if(socket.isClosed())
        {
            try
            {
                socket = new Socket("127.0.0.1", 9876);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        try
        {

            //-----SENDS ID-----

            os.write(id);

            //-----SENDS MESSAGE-----
            byte[] b = chatInput.getBytes(Charset.forName("UTF-8"));
            os.write(b);
            System.out.println("Message Sent: " + chatInput);

                socket.close();


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void readFromPort(int port)
    {

        while (true)
        {
            try
            {
                if (new ServerSocket(port).isBound())
                {
                    port++;
                }
                serverSocket = new ServerSocket(port);
                socket = serverSocket.accept();
                is = socket.getInputStream();
                int id = is.read();
                socket.close();
                //-----GETS ID-----
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
                    System.out.println(id + " : " + s);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
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


