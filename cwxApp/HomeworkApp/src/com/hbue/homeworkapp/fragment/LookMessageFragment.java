package com.hbue.homeworkapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hbue.homeworkapp.MainApplication;
import com.hbue.homeworkapp.R;
import com.hbue.homeworkapp.adpeter.MessageAdapter;
import com.hbue.homeworkapp.dao.Msg;

public class LookMessageFragment extends Fragment {

	private ListView messageList;
	private MessageAdapter classAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.page_message_lookmsg, container,
				false);
		messageList = (ListView) v.findViewById(R.id.lookmsg_listview_msglist);
		classAdapter = new MessageAdapter(getActivity());
		classAdapter.setData(MainApplication.getDaoSession(getActivity())
				.getMsgDao().loadAll());
		classAdapter.addData(new Msg(System.currentTimeMillis(), "通知", "学委",
				"下次作业请11号之前交", "", "5月7号"));
		classAdapter.addData(new Msg(System.currentTimeMillis(), "作业说明", "王老师",
				"这次作业第七题不用做", "", "5月8号"));
		classAdapter.addData(new Msg(System.currentTimeMillis(), "问题", "李同学",
				"昨天作业第六题怎么做", "", "5月9号"));
		messageList.setAdapter(classAdapter);
		return v;
	}
}
