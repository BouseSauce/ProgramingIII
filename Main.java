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
        new Thread(c).start();


        System.out.println("------------------");
        System.out.println("Starting client ");
        System.out.println("------------------");
        c.sendMessage("Test 1");
        c.sendMessage("Test 2");
        c.sendMessage("Test 3");
        c.sendMessage("Test 4");
        c.sendMessage("Test 1");
        c.sendMessage("Test 2");
        c.sendMessage("Test 3");
        c.sendMessage("Test 4");



    }


}




