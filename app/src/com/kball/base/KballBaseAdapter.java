package com.kball.base;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @Description:适配器封装类
 * @param <T>
 */
public abstract class KballBaseAdapter<T> extends BaseAdapter {
	protected Context mContext;
	protected ImageLoader imageLoader;
	protected LayoutInflater mInflater;
	protected ArrayList<T> datas;
	protected String orMy;

	public KballBaseAdapter() {
	}

	public KballBaseAdapter(Context context) {
		mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.imageLoader = ImageLoader.getInstance();
		this.datas = null;
	}

	public KballBaseAdapter(Context context, ArrayList<T> datas) {
		mContext = context;
		this.imageLoader = ImageLoader.getInstance();
		this.mInflater = LayoutInflater.from(mContext);
		this.datas = datas;
	}
	public void setMyData(ArrayList<T> mData, String orMy) {
		this.datas = mData;
		this.orMy = orMy;
		notifyDataSetChanged();
	}

	public void setDatas(ArrayList<T> datas) {
		this.datas = datas;
		notifyDataSetChanged();
	}

	public void cleanDatas() {
		if (datas != null && !datas.isEmpty()) {
			datas.clear();
			notifyDataSetChanged();
		}
	}

	public ArrayList<T> getDatas() {
		return this.datas;
	}

	public void addDatas(ArrayList<T> datas) {
		this.datas.addAll(datas);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (null != datas) {
			return datas.size();
		}
		return 0;
	}

	@Override
	public T getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public abstract View getView(int position, View convertView, ViewGroup arg2);

}
