package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Created by bousesause on 19/05/15.
 */


public class ChatBox
{

    private JTextField chatInput;
    private JTextField chatDisplay;
    private String messages;
    public boolean getSet = false;


    public ChatBox()
    {
        createAndShowGUI();
    }


    //-------------------------------------------------------------

    private void addComponentsToPane(Container pane)
    {


        JButton button;

        chatDisplay = new JTextField("");
        chatDisplay.setPreferredSize(new Dimension(200, 200));
        pane.add(chatDisplay, BorderLayout.PAGE_START);

        JButton sendButton = new JButton("Send");

        ActionListener actionListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                getSet = true;
            }
        };
        sendButton.addActionListener(actionListener);




        sendButton.setPreferredSize(new Dimension(100, 100));
        pane.add(sendButton, BorderLayout.CENTER);


        chatInput = new JTextField("Enter Text here");
        chatInput.setPreferredSize(new Dimension(100, 100));
        pane.add(chatInput, BorderLayout.PAGE_END);
    }



    private void createAndShowGUI()
    {

        JFrame frame = new JFrame("Chat Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try
        {
            addComponentsToPane(frame.getContentPane());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        frame.pack();
        frame.setVisible(true);
    }

    //----------------------------------------------------

    public void setChatDisplay(String newMessage)
    {
        chatDisplay.setText(chatDisplay.getText() + "\n" + newMessage);
    }
    public String getChatInput()
    {
        return chatInput.getText();
    }

}