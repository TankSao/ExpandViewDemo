package com.example.expandviewdemo.views;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import com.example.expandviewdemo.R;
import com.example.expandviewdemo.adapters.MyAdapter;
import com.example.expandviewdemo.adapters.ViewHolder;
import com.example.expandviewdemo.utils.OnExpandListener;

@SuppressLint("NewApi") public class ExpandItemView  extends LinearLayout {
	
	/**��ʾ��toggleButton�ı�������*/
	public String mTitle;
	/** �ײ���ť */
	private Button mBottomBtn;
	/** չʾҪɸѡ������*/
	private GridView mGridView;
	/** ɸѡ����������*/
	private List<String> mGridviewDatas;

	public ExpandItemView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ExpandItemView(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public ExpandItemView(Context context) {
		this(context, null);
	}

	public ExpandItemView(String title, Context context,List<String> datas) {
		this(context);
		setTitle(title);
		mGridviewDatas = datas;
		init();
	}
	
	private void init() {
		setBackgroundColor(getResources().getColor(android.R.color.white));
		/**������inflate������ͼ��*/
		LayoutInflater.from(getContext()).inflate(R.layout.layout_expand_view, this, true);
		setOrientation(LinearLayout.VERTICAL);
		
		mGridView = (GridView) findViewById(R.id.gridview);
		mBottomBtn = (Button) findViewById(R.id.btn_all);
		/**�Լ�д��������������һ�仰��ʹ����*/
		mGridView.setAdapter(new MyAdapter<String>(mGridviewDatas, R.layout.gridview_item, getContext()) {

			@Override
			protected void convert(ViewHolder viewHolder, String t) {
				viewHolder.setBtnText(R.id.item_text, t);
			}
		});
		/**ÿһ������ص���������*/
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(mOnExpandListener != null)
				{
					mOnExpandListener.onItemClick(position);
				}
			}
		});
		/**�ײ���ť�Ļص�*/
		mBottomBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mOnExpandListener != null)
				{
					mOnExpandListener.onBottomClick();
				}
			}
		});
	}
	
	public String getTitle() {
		return mTitle == null ? new String() : mTitle;
	}

	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public List<String> getmGridviewDatas() {
		return mGridviewDatas == null ? new ArrayList<String>() : mGridviewDatas;
	}

	public void setmGridviewDatas(List<String> mGridviewDatas) {
		this.mGridviewDatas = mGridviewDatas;
	}

	/**
	 * �ۼ�����ĸ߶���Ϊ����ĸ߶�
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int cCount = getChildCount();

		int desireWidth = MeasureSpec.getSize(widthMeasureSpec);
		int desireHeight = 0;
		for (int i = 0; i < cCount; i++) {
			View child = getChildAt(i);
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			desireHeight += child.getMeasuredHeight();
		}
		setMeasuredDimension(desireWidth, desireHeight);
	}
	
	private OnExpandListener mOnExpandListener;

	public void setOnExpandListener(OnExpandListener mOnExpandListener) {
		this.mOnExpandListener = mOnExpandListener;
	}

}
