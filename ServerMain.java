package sample;

import javax.swing.*;


public class ServerMain extends JFrame
{

    public static void main(String[] args) throws Exception
    {

        System.out.println("Starting server");
        Server s = new Server(9876);
        System.out.println("Server terminated");


    }


}




