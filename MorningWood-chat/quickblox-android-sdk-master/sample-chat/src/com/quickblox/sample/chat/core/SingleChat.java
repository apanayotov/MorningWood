package com.quickblox.sample.chat.core;

import android.util.Log;

import com.quickblox.module.chat.QBChatService;
import com.quickblox.module.chat.listeners.ChatMessageListener;
import com.quickblox.module.chat.xmpp.QBPrivateChat;
import com.quickblox.sample.chat.model.ChatMessage;
import com.quickblox.sample.chat.ui.activities.ChatActivity;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;
import org.jivesoftware.smack.packet.Packet;

import java.util.Calendar;

public class SingleChat implements Chat, ChatMessageListener {

    public static final String EXTRA_USER_ID = "user_id";
	private static final String TAG = null;
    private ChatActivity chatActivity;
    private QBPrivateChat chat; 
    private int companionId;
    
    
    public SingleChat(ChatActivity chatActivity) {
        this.chatActivity = chatActivity;
        companionId = chatActivity.getIntent().getIntExtra(EXTRA_USER_ID, 0);
        chat = QBChatService.getInstance().createChat(); 
        chat.addChatMessageListener(new ChatMessageListener() {
            @Override
            public void processMessage(Message message) {
                Log.d(TAG, "Messags: " + message.getBody());
            }
         
            @Override
            public boolean accept(Message.Type messageType) {
                switch (messageType) {
                    case chat: 
                        return true; // process 1-1 chat messages
                    default:
                        return false;
                }
            }
        });
    }
    
    
    @Override
    public void sendMessage(String message) throws XMPPException {
        chat.sendMessage(companionId, message);
    }

    @Override
    public void release() {
        chat.removeChatMessageListener(this);
    }
    
    
    
   	@Override
   	public void processMessage(Message message) {
   		final String messageBody = message.getBody();
   		Log.d(TAG, "Messags: " + message.getBody());
    		chatActivity.showMessage(new ChatMessage(messageBody, Calendar.getInstance().getTime(), true));
    	}
   	
	@Override
	public boolean accept(Type arg0) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void sendMessage(int i, android.os.Message message) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void sendMessage(int i, Packet message) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void sendMessage(int i, Message message) {
		// TODO Auto-generated method stub
		
	}
}
    
    
