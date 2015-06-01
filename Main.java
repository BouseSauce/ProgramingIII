package sample;

import javax.swing.*;


public class Main extends JFrame
{

    public static void main(String[] args) throws Exception
    {

        System.out.println("------------------");
        System.out.println("Starting chat box");
        System.out.println("------------------");

        Client c = new Client();
        Client a = new Client();



        System.out.println("------------------");
        System.out.println("Starting client ");
        System.out.println("------------------");

        c.sendMessage("Client 1");
        a.sendMessage("Client 2");

    }


}




