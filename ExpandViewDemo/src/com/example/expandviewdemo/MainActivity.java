package com.example.expandviewdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.expandviewdemo.views.ExpandItemView;
import com.example.expandviewdemo.views.ExpandView;


public class MainActivity extends ActionBarActivity {

	private Map<String, ExpandItemView> mExpandleItemViews;
	private String[] mColleages = { "ɽ���ƾ���ѧ","ɽ����ѧ","ɽ��ʦ����ѧ","�ൺ��ѧ","���ϴ�ѧ","ɽ��Ӣ��ѧԺ","����ְҵѧԺ","ɽ����ʦѧԺ"};
	private String[] mDepartments = { "�ƿ�", "����","����","ͳ��","����"};
	private String[] mProfessions = { "��������", "����ý��", "���繤��" , "�����" , "ͨ��" , "��е��" };
	private String[] mClasses = { "1��", "2��", "3��", "4��" };
	private ExpandView expandView;
	private RelativeLayout sure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        expandView = (ExpandView) findViewById(R.id.expandview);
        mExpandleItemViews = new LinkedHashMap<String, ExpandItemView>();
		mExpandleItemViews.put("��ѧ", new ExpandItemView("��ѧ", this, Arrays.asList(mColleages)));
		mExpandleItemViews.put("Ժϵ", new ExpandItemView("Ժϵ", this, Arrays.asList(mDepartments)));
		mExpandleItemViews.put("רҵ", new ExpandItemView("רҵ", this, Arrays.asList(mProfessions)));
		mExpandleItemViews.put("�༶", new ExpandItemView("�༶", this, Arrays.asList(mClasses)));
		expandView.bindView(new ArrayList<ExpandItemView>(mExpandleItemViews.values()));
		sure = (RelativeLayout) findViewById(R.id.sure);
		sure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
				Toast.makeText(MainActivity.this, expandView.getSelectedInfo(), Toast.LENGTH_LONG).show();
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
