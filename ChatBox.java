package sample;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by bousesause on 19/05/15.
 */


public class ChatBox
{

    public boolean messageTrigger = false;
    private JTextField chatInput;
    private JTextArea chatDisplay;
    private String messages;
    private Client client;

    public ChatBox()
    {
        client = new Client("127.0.0.1", 9876);
        createAndShowGUI();
    }
    //-------------------------------------------------------------
    private void addComponentsToPane(Container pane)
    {

        chatDisplay = new JTextArea("Chat:");
        chatDisplay.setPreferredSize(new Dimension(200, 200));
        pane.add(chatDisplay, BorderLayout.PAGE_START);

        JButton sendButton = new JButton("Send");
        ActionListener actionListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                client.messageHandler(getChatInput());
                setChatDisplay(getChatInput());
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