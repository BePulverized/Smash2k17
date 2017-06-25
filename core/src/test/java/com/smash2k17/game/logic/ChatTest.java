package com.smash2k17.game.logic;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.*;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Created by Martien on 20-May-17.
 */
public class ChatTest {
    private Chat chat;

    @Before
    public void setUpTest() throws Exception {
        chat =  new Chat();
    }

    @Test
    public void getNameTest() throws Exception {
        chat.setName("test");
        assertEquals("test", chat.getName());
    }

    @Test
    public void setNameTest() throws Exception {
        getNameTest();
    }

    @Test
    public void getMessageListTest() throws Exception {
        chat.addMessage("test123 hallo", "Martien");

        try{
            if(chat.getMessageList().get(0) != null)
            if(chat.getMessageList().get(0).getSender().equals("Martien")){
                if(chat.getMessageList().get(0).getText().equals("test123 hallo")){
                    assertTrue(true);
                }
                else{
                    assertFalse("Wrong text", true);
                }
            }else{
                if(chat.getMessageList().get(0).getText().equals("test123 hallo")){
                    assertFalse("Wrong text and sender", true);
                }
                else{
                    assertFalse("Wrong sender", true);
                }
            }
        }catch(NullPointerException ex){
            System.out.println("Message is Null");
            System.out.println("Actual text: " + chat.getMessageList().get(0).getText());
            System.out.println("Actual sender: " + chat.getMessageList().get(0).getSender());
            ex.printStackTrace();
        }

    }

    @Test
    public void addMessageTest() throws Exception {
        getMessageListTest();
    }
}