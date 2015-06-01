package sample;


import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 */
public class Client
{
    Socket socket;
    OutputStream oos;


    public void sendToPort(String chatInput)
    {

        try
        {

            socket = new Socket("127.0.0.1", 9876);
            oos = socket.getOutputStream();
            byte[] b = chatInput.getBytes(Charset.forName("UTF-8"));
            oos.write(b);
            System.out.println("Message Sent: " + chatInput);
        }
        catch(Exception e) {e.printStackTrace();}

    }

    public void readFromPort()
    {

            ObjectInputStream ois;
            try
            {
                ois = new ObjectInputStream(socket.getInputStream());
                String message = ((String) ois.readObject());
                System.out.println("Message Recived: " + message);
            } catch (Exception e)
            {
                e.printStackTrace();
            }

    }




}


