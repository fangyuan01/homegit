package com.hbueschoolhelper;

import org.jivesoftware.smack.XMPPException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hbueschoolhelper.utils.XmppManager;

public class MessageActivity extends Activity {

	private TextView msg;
	private EditText send;
	private Button sendMsg;

	private String sender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);

		msg = (TextView) findViewById(R.id.message_msg);
		send = (EditText) findViewById(R.id.message_edit);
		sendMsg = (Button) findViewById(R.id.message_sendbutton);

		String mmsg = getIntent().getStringExtra("data");
		sender = getIntent().getStringExtra("sender");

		msg.setText(mmsg);

		sendMsg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					XmppManager.SendMessage(send.getText().toString(), sender);
				} catch (XMPPException e) {
					e.printStackTrace();
				}
				finish();
			}
		});
	}
}
