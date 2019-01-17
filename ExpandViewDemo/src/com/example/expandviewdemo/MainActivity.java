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
	private String[] mColleages = { "山东财经大学","山东大学","山东师范大学","青岛大学","济南大学","山东英才学院","济南职业学院","山东技师学院"};
	private String[] mDepartments = { "计科", "金融","管理","统计","物理"};
	private String[] mProfessions = { "电子商务", "数字媒体", "网络工程" , "计算机" , "通信" , "机械化" };
	private String[] mClasses = { "1班", "2班", "3班", "4班" };
	private ExpandView expandView;
	private RelativeLayout sure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        expandView = (ExpandView) findViewById(R.id.expandview);
        mExpandleItemViews = new LinkedHashMap<String, ExpandItemView>();
		mExpandleItemViews.put("大学", new ExpandItemView("大学", this, Arrays.asList(mColleages)));
		mExpandleItemViews.put("院系", new ExpandItemView("院系", this, Arrays.asList(mDepartments)));
		mExpandleItemViews.put("专业", new ExpandItemView("专业", this, Arrays.asList(mProfessions)));
		mExpandleItemViews.put("班级", new ExpandItemView("班级", this, Arrays.asList(mClasses)));
		expandView.bindView(new ArrayList<ExpandItemView>(mExpandleItemViews.values()));
		sure = (RelativeLayout) findViewById(R.id.sure);
		sure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
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
