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

        System.out.println("------------------");
        System.out.println("Starting client ");
        System.out.println("------------------");
        c.sendToPort("Hi Jess TEST");


    }


}




