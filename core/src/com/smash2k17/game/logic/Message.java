package com.smash2k17.game.logic;

import java.util.Date;

/**
 * Created by BePul on 27-3-2017.
 */
public class Message {

    private String text;
    private Date timeStamp;
    private String sender;

    public Message(String text, String sender)
    {
        this.text = text;
        this.sender = sender;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getText() {
        return text;
    }


}
