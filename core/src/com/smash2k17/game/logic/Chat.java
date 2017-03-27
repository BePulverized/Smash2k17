package com.smash2k17.game.logic;

import java.util.ArrayList;

/**
 * Created by BePul on 27-3-2017.
 */
public class Chat {

    private String name;
    private ArrayList<Message> messageList;

    public Chat()
    {
        messageList = new ArrayList<Message>();
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Message> getMessageList()
    {
        return messageList;
    }

    public void AddMessage(String text, String sender)
    {
        messageList.add(new Message(text, sender));
    }
}
