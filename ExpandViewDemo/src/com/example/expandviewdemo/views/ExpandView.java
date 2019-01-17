package com.example.expandviewdemo.views;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.example.expandviewdemo.R;
import com.example.expandviewdemo.utils.OnExpandListener;

@SuppressLint("NewApi") 
public class ExpandView  extends LinearLayout implements OnExpandListener {
	
	/** ��¼ѡ�е�ToggleButton */
	private ToggleButton mSelectToggleBtn;
	/** ɸѡ */
	private List<View> mToggleButtons = new ArrayList<View>();
	/** ɸѡ��� */
	private List<View> mPopupviews;

	/** popupwindowչʾ�Ŀ� */
	private int mDisplayWidth;
	/** popupwindowչʾ�ĸ� */
	private int mDisplayHeight;
	/** ɸѡ������PopupWindow������ */
	private PopupWindow mPopupWindow;
	private Context mContext;

	/** toggleButton������������ɫ */
	int mNormalTextColor = getResources().getColor(R.color.grey);
	/** toggleButton��ѡ�е�����������ɫ */
	int mSelectTextColor = Color.RED;
	
	
	public ExpandView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public ExpandView(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public ExpandView(Context context) {
		this(context, null);
	}
	
	private void init() {
		setOrientation(LinearLayout.HORIZONTAL);
		mDisplayWidth = getResources().getDisplayMetrics().widthPixels;
		mDisplayHeight = getResources().getDisplayMetrics().heightPixels;
		mContext = getContext();
		setBackgroundResource(R.drawable.choosearea_bg_right);
	}
	
	
	
	/**
	 * ����popupWindow����������ToggleButton������ɫ
	 */
	private void hidePopWindow() {
		if (mPopupWindow != null) {
			mPopupWindow.dismiss();
		}
		if (mSelectToggleBtn != null) {
			mSelectToggleBtn.setTextColor(mNormalTextColor);
			mSelectToggleBtn.setChecked(false);
		}
	}

	/**
	 * ��ʾpopupWindow
	 */
	private void showPopWindow() {
		if (null == mPopupWindow) {
			mPopupWindow = new PopupWindow(mPopupviews.get(((Integer) mSelectToggleBtn.getTag()).intValue()), mDisplayWidth,
					mDisplayHeight);
			/** ����popupWindow������,������������ɫ */
			mPopupWindow.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss() {
					if (mSelectToggleBtn != null) {
						mSelectToggleBtn.setTextColor(mNormalTextColor);
						mSelectToggleBtn.setChecked(false);
					}
				}
			});
			mPopupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
			mPopupWindow.setFocusable(true);
			mPopupWindow.setOutsideTouchable(true);
			mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		} else {
			mPopupWindow.setContentView(mPopupviews.get(((Integer) mSelectToggleBtn.getTag()).intValue()));
		}

		if (mPopupWindow.isShowing()) {
			hidePopWindow();
		} else {
			/** ��ʾ��ʱ����Ϊѡ����ɫ */
			mSelectToggleBtn.setTextColor(mSelectTextColor);
			mPopupWindow.showAsDropDown(mToggleButtons.get(0), 0, 0);
		}
	}

	
	@Override
	public void onItemClick(int position) {
		hidePopWindow();
		if (null != mSelectToggleBtn) {
			int selectBtnIndex = ((Integer) mSelectToggleBtn.getTag()).intValue();
			mSelectToggleBtn
					.setText(((ExpandItemView) (((RelativeLayout) mPopupviews.get(selectBtnIndex)).getChildAt(0)))
							.getmGridviewDatas().get(position));
		}
	}
	
	@Override
	public void onBottomClick() {
		hidePopWindow();
		if (null != mSelectToggleBtn) {
			int selectBtnIndex = ((Integer) mSelectToggleBtn.getTag()).intValue();
			mSelectToggleBtn
					.setText((((ExpandItemView) (((RelativeLayout) mPopupviews.get(selectBtnIndex)).getChildAt(0)))
							.getTitle()));
		}
	}
	
	
	public String getSelectedInfo(){
		String info =  "";
		for(int i=0;i<mToggleButtons.size();i++){
			info+=((ToggleButton)mToggleButtons.get(i)).getText().toString()+",";
		}
		return info;
	}
	
	public void bindView(List<ExpandItemView> views) {
		mPopupviews = new ArrayList<View>();
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		for (int i = 0; i < views.size(); i++) {
			ExpandItemView view = views.get(i);
			view.setOnExpandListener(this);
			final RelativeLayout r = new RelativeLayout(mContext);
			RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			r.addView(view, rl);
			mPopupviews.add(r);

			final ToggleButton toggleButton = (ToggleButton) inflater.inflate(R.layout.toggle_button, this, false);
			toggleButton.setText(view.getTitle());
			mToggleButtons.add(toggleButton);
			addView(toggleButton);
			toggleButton.setTag(i);
			toggleButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					/** ��¼ѡ�е�ToggleButton,�������ʲô���ð� */
					mSelectToggleBtn = toggleButton;
					showPopWindow();
				}
			});
			r.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					onPressBack();
				}

				private void onPressBack() {
					hidePopWindow();
				}
			});
		}
	}


}
