package com.smash2k17.game.logic;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Martien on 22-May-17.
 */
public class MessageTest {
    Message message;

    @Before
    public void setUp(){
        message = new Message("test text", "Martien");
    }

    @Test
    public void getTimeStampTest() throws Exception {
        Date date = new Date(2017,5,22,12,14,35);

        message.setTimeStamp(date);
        assertEquals(date, message.getTimeStamp());
    }

    @Test
    public void setTimeStampTest() throws Exception {
        Date date = new Date(2017,5,22,12,14,35);

        message.setTimeStamp(date);
        assertEquals(date, message.getTimeStamp());
    }

    @Test
    public void getTextTest() throws Exception {
        assertEquals("test text", message.getText());
    }

    @Test
    public void getSenderTest() throws Exception {
        assertEquals("Martien", message.getSender());
    }

}