package com.hbue.homeworkapp.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hbue.homeworkapp.R;
import com.hbue.homeworkapp.dao.Msg;
import com.hbue.homeworkapp.service.XmppUserUtils;
import com.hbue.homeworkapp.utils.XmppManager;

public class SendMessageFragment extends Fragment {

	private EditText etMsgTitle;
	private TextView etMsgReceiver;
	private EditText etMsgInfo;

	private Button btnSend;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.page_message_sendmsg, container,
				false);
		etMsgTitle = (EditText) v.findViewById(R.id.sendmsg_edittext_titleedit);
		etMsgReceiver = (TextView) v
				.findViewById(R.id.sendmsg_edittext_titlereceiver);
		etMsgInfo = (EditText) v.findViewById(R.id.sendmsg_edittext_titleinfo);

		etMsgReceiver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Builder builder = new AlertDialog.Builder(getActivity());
				List<String> users = XmppManager.searchUser("hbue");
				ScrollView scrollView = new ScrollView(getActivity());
				LinearLayout layout = new LinearLayout(getActivity());
				layout.setOrientation(LinearLayout.VERTICAL);
				final ArrayList<CheckBox> boxs = new ArrayList<CheckBox>();
				for (String item : users) {
					CheckBox cb = new CheckBox(getActivity());
					cb.setText(item);
					boxs.add(cb);
					layout.addView(cb);
				}
				scrollView.addView(layout);
				builder.setView(scrollView);
				builder.setTitle("选择账户");
				builder.setNegativeButton("确定", new Dialog.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						for (CheckBox item : boxs) {
							if (item.isChecked()) {
								etMsgReceiver
										.setText(item.getText().toString());
							}
						}
					}
				});
				builder.create().show();
			}
		});

		btnSend = (Button) v.findViewById(R.id.sendmsg_button_sendbtn);
		btnSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					Gson gson = new Gson();
					String msgInfo = gson.toJson(new Msg(System
							.currentTimeMillis(), etMsgTitle.getText()
							.toString(), XmppUserUtils.Account, etMsgInfo
							.getText().toString(), null, etMsgInfo.getText()
							.toString()));
					XmppManager.SendMessage(msgInfo, etMsgReceiver.getText()
							.toString());
					Toast.makeText(
							getActivity(),
							"发送数据\"" + etMsgInfo.getText().toString() + "\"给\""
									+ etMsgReceiver.getText().toString()
									+ "\"成功", Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					Toast.makeText(getActivity(), "发送失败", Toast.LENGTH_SHORT)
							.show();
					e.printStackTrace();
				}
			}
		});

		return v;
	}
}
