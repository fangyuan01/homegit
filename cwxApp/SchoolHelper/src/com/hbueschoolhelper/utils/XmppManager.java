package com.hbueschoolhelper.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.provider.PrivacyProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.GroupChatInvitation;
import org.jivesoftware.smackx.PrivateDataManager;
import org.jivesoftware.smackx.ReportedData;
import org.jivesoftware.smackx.ReportedData.Row;
import org.jivesoftware.smackx.bytestreams.socks5.provider.BytestreamsProvider;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.jivesoftware.smackx.packet.ChatStateExtension;
import org.jivesoftware.smackx.packet.LastActivity;
import org.jivesoftware.smackx.packet.OfflineMessageInfo;
import org.jivesoftware.smackx.packet.OfflineMessageRequest;
import org.jivesoftware.smackx.packet.SharedGroupsInfo;
import org.jivesoftware.smackx.provider.AdHocCommandDataProvider;
import org.jivesoftware.smackx.provider.DataFormProvider;
import org.jivesoftware.smackx.provider.DelayInformationProvider;
import org.jivesoftware.smackx.provider.DiscoverInfoProvider;
import org.jivesoftware.smackx.provider.DiscoverItemsProvider;
import org.jivesoftware.smackx.provider.MUCAdminProvider;
import org.jivesoftware.smackx.provider.MUCOwnerProvider;
import org.jivesoftware.smackx.provider.MUCUserProvider;
import org.jivesoftware.smackx.provider.MessageEventProvider;
import org.jivesoftware.smackx.provider.MultipleAddressesProvider;
import org.jivesoftware.smackx.provider.RosterExchangeProvider;
import org.jivesoftware.smackx.provider.StreamInitiationProvider;
import org.jivesoftware.smackx.provider.VCardProvider;
import org.jivesoftware.smackx.provider.XHTMLExtensionProvider;
import org.jivesoftware.smackx.search.UserSearch;
import org.jivesoftware.smackx.search.UserSearchManager;

import android.os.Handler;
import android.util.Log;

public class XmppManager implements PacketFilter, PacketListener,
		ChatManagerListener, RosterListener, MessageListener,
		ConnectionListener {

	private static Connection xmppCon = null;

	private static String serviceName = null;

	private static String userName = null;

	private static String password = null;

	private static ChatManager chatManager = null;

	private static XmppManager _self = null;
	/**
	 * 文件传输监听
	 */
	private static FileTransferManager fileManager;

	private static String _host = null;

	private static int _port = -1;

	private static List<MessageListener> msgList = new ArrayList<MessageListener>();

	private Handler msgHandler;

	private static ConnectionConfiguration config;

	/**
	 * 默认构造函数
	 * 
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @throws XMPPException
	 */
	public XmppManager(String host, int port, String username, String password,
			Handler handler) throws XMPPException {
		XmppManager.configure(ProviderManager.getInstance());
		msgHandler = handler;
		initXmpp(host, port, username, password);
	}

	public boolean initXmpp(String host, int port, String username,
			String password) {
		if (xmppCon == null) {
			try {
				config = new ConnectionConfiguration(host, port);
				xmppCon = new XMPPConnection(config);
				xmppCon.connect();
				xmppCon.addPacketListener(this, this);
				xmppCon.login(username, password);
				xmppCon.addConnectionListener(this);
				serviceName = xmppCon.getServiceName();
				userName = xmppCon.getUser();
				XmppManager.password = password;
				chatManager = xmppCon.getChatManager();
				chatManager.addChatListener(this);
				xmppCon.getRoster().addRosterListener(this);
				_host = host;
				_port = port;
				_self = this;
				Log.v("info", "Jabber服务器连接成功！" + username);
				msgHandler.post(new Runnable() {

					@Override
					public void run() {
						for (OnXmppCompanyListener item : onXmppCompanyListeners) {
							item.onCompany();
						}
					}
				});
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				xmppCon = null;
				return false;
			}
		}
		return false;
	}

	/**
	 * 判断是否在连接状态
	 * 
	 * @return
	 */
	public static boolean isConnected() {
		if (xmppCon != null)
			return xmppCon.isConnected();
		return false;
	}

	/**
	 * 关闭连接
	 */
	public static void closeConnect() {
		try {
			if (xmppCon != null) {
				xmppCon.disconnect();
				xmppCon.removePacketListener(_self);
				xmppCon.getRoster().addRosterListener(_self);
			}
			if (chatManager != null) {
				chatManager.removeChatListener(_self);
			}
			xmppCon = null;
		} catch (Exception e) {
		}
	}

	/**
	 * 获取聊天管理者
	 * 
	 * @return
	 */
	public static ChatManager getCharManager() {
		return xmppCon.getChatManager();
	}

	public static synchronized void addMessageListener(MessageListener msgLis) {
		msgList.add(msgLis);
	}

	public static synchronized void removeMessageListener(MessageListener msgLis) {
		msgList.remove(msgLis);
	}

	public static synchronized void clearMessageListener() {
		msgList.clear();
	}

	public static List<MessageListener> getMessageList() {
		return msgList;
	}

	/**
	 * 向所有在线用户广播信息
	 * 
	 * @param msg
	 * @return
	 */
	public static boolean BroadCastMessage(String msg) {
		Message message = new Message();
		message.setTo("all@broadcast." + serviceName);
		message.setBody(msg);
		xmppCon.sendPacket(message);
		return true;
	}

	/**
	 * 给指定用户发送信息
	 * 
	 * @param msg
	 * @param userId
	 * @throws XMPPException
	 */
	public static void SendMessage(String msg, String userId)
			throws XMPPException {
		Log.v("info", "发送:" + msg + " to->" + userId);
		Chat newChat = chatManager.createChat(userId + "@" + serviceName, null);
		newChat.sendMessage(msg);
		newChat = null;
	}

	/**
	 * 发送信息给群组用户
	 * 
	 * @param msg
	 *            信息
	 * @param groupId
	 *            群组id
	 * @param noCare
	 *            true表示发送给所有用户不关心是否在线，false表示只发送给在线用户
	 * @throws XMPPException
	 */
	public static void SendMessageToGroup(String msg, String groupId,
			boolean noCare) throws XMPPException {
		Roster roster = getRoster();
		RosterGroup rosterGroup = roster.getGroup(groupId);
		if (rosterGroup == null)
			return;
		Iterator<RosterEntry> itor = rosterGroup.getEntries().iterator();
		while (itor.hasNext()) {
			RosterEntry entry = itor.next();
			// 判断是否在线可用
			if (!noCare) {
				Presence pre = roster.getPresence(entry.getName());
				if (!pre.isAvailable())
					continue;
			}
			SendMessage(msg, entry.getName());
		}
	}

	/**
	 * 获取连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		return xmppCon;
	}

	/**
	 * 获取服务名称
	 * 
	 * @return
	 */
	public static String getServiceName() {
		return serviceName;
	}

	/**
	 * 获取jabber服务器地址
	 * 
	 * @return
	 */
	public static String getHost() {
		return _host;
	}

	/**
	 * 获取jabber服务器端口
	 * 
	 * @return
	 */
	public static int getPort() {
		return _port;
	}

	/**
	 * 获取花名册
	 * 
	 * @return
	 */
	public static Roster getRoster() {
		return xmppCon.getRoster();
	}

	/**
	 * 创建用户
	 * 
	 * @param name
	 * @param password
	 * @return
	 * @throws XMPPException
	 */
	public static boolean createAccount(String name, String password,
			String host, int port) {
		try {
			if (xmppCon == null) {
				config = new ConnectionConfiguration(host, port);
				xmppCon = new XMPPConnection(config);
				xmppCon.connect();
				AccountManager accountManager = new AccountManager(xmppCon);
				accountManager.createAccount(name, password);
				Log.v("info", "xmpp账号注册成功！");
				config = null;
				xmppCon = null;
			} else {
				AccountManager accountManager = new AccountManager(xmppCon);
				accountManager.createAccount(name, password);
				Log.v("info", "xmpp账号注册成功！");
			}
			return true;
		} catch (Exception e) {
			config = null;
			xmppCon = null;
			Log.v("info", "xmpp账号注册失败！");
			return false;
		}
	}

	/**
	 * 创建分组
	 * 
	 * @return
	 */
	public static RosterGroup createGroup(String groupName) {
		Roster roster = getRoster();
		return roster.createGroup(groupName);
	}

	/**
	 * 根据名称获取分组
	 * 
	 * @param groupName
	 * @return
	 */
	public static RosterGroup getGroup(String groupName) {
		Roster roster = getRoster();
		return roster.getGroup(groupName);
	}

	/**
	 * 获取分组数量
	 * 
	 * @return
	 */
	public static int getGroupCount() {
		return getRoster().getGroupCount();
	}

	/**
	 * 添加用户到指定分组
	 * 
	 * @param groupName
	 * @param userName
	 * @return
	 * @throws XMPPException
	 */
	public static boolean addUserToGroup(String groupName, String userName)
			throws XMPPException {
		RosterGroup rg = XmppManager.getGroup(groupName);
		RosterEntry entry = getRoster().getEntry(userName);
		if (rg == null || entry == null)
			return false;
		rg.addEntry(entry);
		return true;
	}

	/**
	 * 创建名单实体
	 * 
	 * @param name
	 * @param group
	 * @return
	 */
	public static RosterEntry createEntry(String name) {
		try {
			String scName = name + "@" + serviceName;
			getRoster().createEntry(scName, name, null);
			return getRoster().getEntry(scName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 当收到信息回复的时候执行
	 */
	@Override
	public void chatCreated(Chat chat, boolean isLocal) {
		// TODO Auto-generated method stub
		if (!isLocal) {
			chat.addMessageListener(this);
		}
	}

	/**
	 * 释放资源
	 * 
	 * @throws Throwable
	 */
	public void destory() throws Throwable {
		if (xmppCon != null)
			xmppCon.disconnect();
		XmppManager.xmppCon = null;
		XmppManager.chatManager = null;
		XmppManager.serviceName = null;
		XmppManager.userName = null;
	}

	/**
	 * 处理数据包
	 */
	@Override
	public void processPacket(Packet arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * 数据包接收
	 */
	@Override
	public boolean accept(Packet arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 实体添加
	 */
	@Override
	public void entriesAdded(Collection<String> arg0) {
	}

	/**
	 * 好友删除
	 */
	@Override
	public void entriesDeleted(Collection<String> arg0) {
	}

	/**
	 * 好友更新
	 */
	@Override
	public void entriesUpdated(Collection<String> arg0) {
	}

	/**
	 * 处理用户上下线
	 */
	@Override
	public void presenceChanged(Presence pre) {
		System.out.println("from：" + pre.getFrom() + "          To："
				+ pre.getType());
	}

	@Override
	public void processMessage(Chat chat, Message msg) {
		android.os.Message mg = new android.os.Message();
		mg.what = 0;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("chat", chat);
		map.put("msg", msg);
		mg.obj = map;
		MessageRunnable runnable = new MessageRunnable();
		runnable.setMsg(mg);
		msgHandler.post(runnable);
	}

	public static void configure(ProviderManager pm) {

		// Private Data Storage
		pm.addIQProvider("query", "jabber:iq:private",
				new PrivateDataManager.PrivateDataIQProvider());

		// Time
		try {
			pm.addIQProvider("query", "jabber:iq:time",
					Class.forName("org.jivesoftware.smackx.packet.Time"));
		} catch (ClassNotFoundException e) {
			Log.w("TestClient",
					"Can't load class for org.jivesoftware.smackx.packet.Time");
		}

		// Roster Exchange
		pm.addExtensionProvider("x", "jabber:x:roster",
				new RosterExchangeProvider());

		// Message Events
		pm.addExtensionProvider("x", "jabber:x:event",
				new MessageEventProvider());

		// Chat State
		pm.addExtensionProvider("active",
				"http://jabber.org/protocol/chatstates",
				new ChatStateExtension.Provider());
		pm.addExtensionProvider("composing",
				"http://jabber.org/protocol/chatstates",
				new ChatStateExtension.Provider());
		pm.addExtensionProvider("paused",
				"http://jabber.org/protocol/chatstates",
				new ChatStateExtension.Provider());
		pm.addExtensionProvider("inactive",
				"http://jabber.org/protocol/chatstates",
				new ChatStateExtension.Provider());
		pm.addExtensionProvider("gone",
				"http://jabber.org/protocol/chatstates",
				new ChatStateExtension.Provider());

		// XHTML
		pm.addExtensionProvider("html", "http://jabber.org/protocol/xhtml-im",
				new XHTMLExtensionProvider());

		// Group Chat Invitations
		pm.addExtensionProvider("x", "jabber:x:conference",
				new GroupChatInvitation.Provider());

		// Service Discovery # Items
		pm.addIQProvider("query", "http://jabber.org/protocol/disco#items",
				new DiscoverItemsProvider());

		// Service Discovery # Info
		pm.addIQProvider("query", "http://jabber.org/protocol/disco#info",
				new DiscoverInfoProvider());

		// Data Forms
		pm.addExtensionProvider("x", "jabber:x:data", new DataFormProvider());

		// MUC User
		pm.addExtensionProvider("x", "http://jabber.org/protocol/muc#user",
				new MUCUserProvider());

		// MUC Admin
		pm.addIQProvider("query", "http://jabber.org/protocol/muc#admin",
				new MUCAdminProvider());

		// MUC Owner
		pm.addIQProvider("query", "http://jabber.org/protocol/muc#owner",
				new MUCOwnerProvider());

		// Delayed Delivery
		pm.addExtensionProvider("x", "jabber:x:delay",
				new DelayInformationProvider());

		// Version
		try {
			pm.addIQProvider("query", "jabber:iq:version",
					Class.forName("org.jivesoftware.smackx.packet.Version"));
		} catch (ClassNotFoundException e) {
			// Not sure what's happening here.
		}

		// VCard
		pm.addIQProvider("vCard", "vcard-temp", new VCardProvider());

		// Offline Message Requests
		pm.addIQProvider("offline", "http://jabber.org/protocol/offline",
				new OfflineMessageRequest.Provider());

		// Offline Message Indicator
		pm.addExtensionProvider("offline",
				"http://jabber.org/protocol/offline",
				new OfflineMessageInfo.Provider());

		// Last Activity
		pm.addIQProvider("query", "jabber:iq:last", new LastActivity.Provider());

		// User Search
		pm.addIQProvider("query", "jabber:iq:search", new UserSearch.Provider());

		// SharedGroupsInfo
		pm.addIQProvider("sharedgroup",
				"http://www.jivesoftware.org/protocol/sharedgroup",
				new SharedGroupsInfo.Provider());

		// JEP-33: Extended Stanza Addressing
		pm.addExtensionProvider("addresses",
				"http://jabber.org/protocol/address",
				new MultipleAddressesProvider());

		// FileTransfer
		pm.addIQProvider("si", "http://jabber.org/protocol/si",
				new StreamInitiationProvider());

		pm.addIQProvider("query", "http://jabber.org/protocol/bytestreams",
				new BytestreamsProvider());

		// Privacy
		pm.addIQProvider("query", "jabber:iq:privacy", new PrivacyProvider());
		pm.addIQProvider("command", "http://jabber.org/protocol/commands",
				new AdHocCommandDataProvider());
		pm.addExtensionProvider("malformed-action",
				"http://jabber.org/protocol/commands",
				new AdHocCommandDataProvider.MalformedActionError());
		pm.addExtensionProvider("bad-locale",
				"http://jabber.org/protocol/commands",
				new AdHocCommandDataProvider.BadLocaleError());
		pm.addExtensionProvider("bad-payload",
				"http://jabber.org/protocol/commands",
				new AdHocCommandDataProvider.BadPayloadError());
		pm.addExtensionProvider("bad-sessionid",
				"http://jabber.org/protocol/commands",
				new AdHocCommandDataProvider.BadSessionIDError());
		pm.addExtensionProvider("session-expired",
				"http://jabber.org/protocol/commands",
				new AdHocCommandDataProvider.SessionExpiredError());
	}

	/**
	 * 搜索用户
	 * 
	 * @param name
	 */
	public static List<String> searchUser(String name) {
		ArrayList<String> users = new ArrayList<String>();
		try {
			UserSearchManager search = new UserSearchManager(xmppCon);
			Form searchForm = search.getSearchForm("search."
					+ xmppCon.getServiceName());
			Form answerForm = searchForm.createAnswerForm();
			answerForm.setAnswer("Username", true);
			answerForm.setAnswer("search", name);
			ReportedData data = search.getSearchResults(answerForm, "search."
					+ xmppCon.getServiceName());
			Iterator<Row> it = data.getRows();
			Row row = null;
			String ansS = "";
			while (it.hasNext()) {
				row = it.next();
				users.add(row.getValues("Username").next().toString());
				ansS += row.getValues("Username").next().toString() + " ";
			}
			Log.v("info", "搜索到用户" + ansS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void connectionClosed() {
	}

	@Override
	public void connectionClosedOnError(Exception arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reconnectingIn(int arg0) {
		System.out.println("connecting time :" + arg0);
	}

	@Override
	public void reconnectionFailed(Exception arg0) {
		System.out.println("connecting failed :" + arg0.getLocalizedMessage());
	}

	@Override
	public void reconnectionSuccessful() {
		// TODO Auto-generated method stub

	}

	public static void addOnXmppCompanyListener(OnXmppCompanyListener lis) {
		onXmppCompanyListeners.add(lis);
	}

	public static void removeOnXmppCompanyListener() {
		onXmppCompanyListeners.clear();
	}

	public static List<OnXmppCompanyListener> onXmppCompanyListeners = new LinkedList<XmppManager.OnXmppCompanyListener>();

	public interface OnXmppCompanyListener {
		public void onCompany();
	}

}

class MessageRunnable implements Runnable {

	private android.os.Message msg;

	public void setMsg(android.os.Message msg) {
		this.msg = msg;
	}

	@Override
	public void run() {
		List<MessageListener> list = XmppManager.getMessageList();
		HashMap<String, Object> map = (HashMap<String, Object>) msg.obj;
		for (int i = 0; i < list.size(); i++) {
			list.get(i).processMessage((Chat) map.get("chat"),
					(Message) map.get("msg"));
		}
	}

}
