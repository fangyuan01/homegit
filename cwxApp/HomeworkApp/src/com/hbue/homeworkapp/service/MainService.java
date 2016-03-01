package com.hbue.homeworkapp.service;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import com.hbue.homeworkapp.utils.XmppManager;
import com.hbue.homeworkapp.utils.XmppManager.OnXmppCompanyListener;

public class MainService extends Service implements MessageListener, Runnable,
		OnXmppCompanyListener {

	private static XmppManager xmppWatcher = null;
	private Handler handler = null;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		handler = new Handler();
		Thread thread = new Thread(this);
		thread.start();
		XmppManager.addOnXmppCompanyListener(this);
	}

	/**
	 * 处理服务器发送的消息
	 */
	@Override
	public void processMessage(Chat arg0, Message arg1) {
	}

	private static boolean isNotInit = true;

	@Override
	public void run() {
		while (true) {
			if (isNotInit) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					if (xmppWatcher == null
							|| XmppManager.isConnected() == false) {
						xmppWatcher = new XmppManager(MainURLs.Jabber_host,
								MainURLs.Jabber_port, XmppUserUtils.Account,
								XmppUserUtils.Password, handler);
					}
				} catch (Exception e) {
				}
			} else {
				if (XmppManager.isConnected() == false) {
					XmppManager.closeConnect();
					isNotInit = true;
				}
			}
		}
	}

	@Override
	public void onCompany() {
		isNotInit = false;
		XmppManager.addMessageListener(this);
	}
}
