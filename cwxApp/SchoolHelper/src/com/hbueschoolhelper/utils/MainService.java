package com.hbueschoolhelper.utils;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.hbueschoolhelper.MessageActivity;
import com.hbueschoolhelper.R;

public class MainService extends Service implements MessageListener, Runnable {

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
		System.out.println("onStart");
	}

	/**
	 * 处理服务器发送的消息
	 */
	@Override
	public void processMessage(Chat arg0, Message arg1) {
		Notification notification = new Notification();
		notification.icon = R.drawable.ic_launcher;
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		notification.tickerText = "收到消息" + arg1.getBody();

		Intent intent = new Intent(this, MessageActivity.class);
		intent.putExtra("data", arg1.getBody());
		intent.putExtra("sender", arg1.getFrom());
		notification.contentIntent = PendingIntent.getActivity(this, 0, intent,
				0);
		notification.setLatestEventInfo(this, "消息", "收到消息" + arg1.getBody(),
				notification.contentIntent);
		NotificationManager manager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		manager.notify(0, notification);

		Log.v("info",
				"get message:" + arg1.getBody() + " from->" + arg1.getFrom());
	}

	private static boolean isNotInit = true;

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if (isNotInit) {
				try {
					if (xmppWatcher == null
							|| XmppManager.isConnected() == false) {
						xmppWatcher = new XmppManager(MainURLs.Jabber_host,
								MainURLs.Jabber_port, XmppUserUtils.Account,
								XmppUserUtils.Password, handler);
						XmppManager.getMessageList().clear();
						XmppManager.addMessageListener(this);
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

}
