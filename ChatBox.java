package sample;

import javax.swing.*;
import java.awt.*;



/**
 * Created by bousesause on 19/05/15.
 */


public class ChatBox
{

    private JTextField chatInput;
    private JTextField chatDisplay;

    ChatBox()
    {
        createAndShowGUI();
    }


    //-------------------------------------------------------------

    private void addComponentsToPane(Container pane)
    {


        JButton button;

        chatDisplay = new JTextField("User Messages will appear here:");
        chatDisplay.setPreferredSize(new Dimension(200, 200));
        pane.add(chatDisplay, BorderLayout.PAGE_START);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e ->
        {
            setChatDisplay(getChatInput());

        });

        sendButton.setPreferredSize(new Dimension(100, 100));
        pane.add(sendButton, BorderLayout.CENTER);


        button = new JButton("Button 3 (LINE_START)");

        pane.add(button, BorderLayout.LINE_START);

        chatInput = new JTextField("Enter Text here");
        chatInput.setPreferredSize(new Dimension(100, 100));
        pane.add(chatInput, BorderLayout.PAGE_END);
    }



    private void createAndShowGUI()
    {

        //Create and set up the window.
        JFrame frame = new JFrame("BorderLayoutDemo");
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