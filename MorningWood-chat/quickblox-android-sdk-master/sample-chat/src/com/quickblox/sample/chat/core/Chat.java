package com.quickblox.sample.chat.core;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Packet;

import android.os.Message;

public interface Chat {
    void sendMessage(String message) throws XMPPException;

    void release() throws XMPPException;

	void sendMessage(int i, Packet message);

	void sendMessage(int i, org.jivesoftware.smack.packet.Message message);

	void sendMessage(int i, Message message);
}
