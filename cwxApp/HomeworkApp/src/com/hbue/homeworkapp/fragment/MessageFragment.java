package com.hbue.homeworkapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hbue.homeworkapp.R;

public class MessageFragment extends Fragment implements OnClickListener {

	private RelativeLayout btnLookMsg;
	private RelativeLayout btnSendMsg;
	private boolean lookMsgIsShowing = false;
	private boolean sendMsgIsShowing = false;

	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	private String fragmentTag = "MSG_FRAGMENT";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.page_message, container, false);
		btnLookMsg = (RelativeLayout) v.findViewById(R.id.message_layout_lookmsg);
		btnSendMsg = (RelativeLayout) v.findViewById(R.id.message_layout_sendmsg);
		btnLookMsg.setOnClickListener(this);
		btnSendMsg.setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.message_layout_lookmsg:
			removeFragmentFromView(R.id.message_layout_areasendmsg);
			sendMsgIsShowing = false;
			if (lookMsgIsShowing) {
				removeFragmentFromView(R.id.message_layout_arealookmsg);
				lookMsgIsShowing = false;
			} else {
				addFragmentIntoView(R.id.message_layout_arealookmsg, new LookMessageFragment());
				lookMsgIsShowing = true;
			}
			break;
		case R.id.message_layout_sendmsg:
			removeFragmentFromView(R.id.message_layout_arealookmsg);
			lookMsgIsShowing = false;
			if (sendMsgIsShowing) {
				removeFragmentFromView(R.id.message_layout_areasendmsg);
				sendMsgIsShowing = false;
			} else {
				addFragmentIntoView(R.id.message_layout_areasendmsg, new SendMessageFragment());
				sendMsgIsShowing = true;
			}
			break;
		default:
			return;
		}
	}

	/**
	 * 向某控件加入fragment
	 * 
	 * @param id
	 *            该控件id
	 * @param fragment
	 */
	public void addFragmentIntoView(int id, Fragment fragment) {
		if (fragmentManager == null) {
			fragmentManager = getChildFragmentManager();
		}
		fragmentTransaction = fragmentManager.beginTransaction();
		if (fragmentManager.findFragmentByTag(fragmentTag + id) != null) {
			fragmentTransaction.replace(id, fragment, fragmentTag + id);
		} else {
			fragmentTransaction.add(id, fragment, fragmentTag + id);
		}
		fragmentTransaction.commit();
	}

	/**
	 * 清除某控件上fragment 该frament必须是使用{@link addFragmentIntoView} 方法添加
	 * 
	 * @param id
	 *            该控件id
	 */
	public void removeFragmentFromView(int id) {
		if (fragmentManager == null) {
			fragmentManager = getChildFragmentManager();
		}
		fragmentTransaction = fragmentManager.beginTransaction();
		if (fragmentManager.findFragmentByTag(fragmentTag + id) != null) {
			fragmentTransaction.remove(fragmentManager.findFragmentByTag(fragmentTag + id));
		} else {
		}
		fragmentTransaction.commit();
	}
}
