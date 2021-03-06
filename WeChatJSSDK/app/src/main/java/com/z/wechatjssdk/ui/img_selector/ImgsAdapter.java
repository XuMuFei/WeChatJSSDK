package com.z.wechatjssdk.ui.img_selector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;


import com.z.wechatjssdk.R;
import com.z.wechatjssdk.utils.bitmapfun.ImageFetcher;

import java.util.ArrayList;
import java.util.List;

public class ImgsAdapter extends BaseAdapter {

	Context context;
	List<String> data;
    private ImageFetcher mImageFetcher;
	Util util;
	OnItemClickClass onItemClickClass;
	private int index=-1;
	
	List<View> holderlist;
	public ImgsAdapter(Context context,List<String> data,OnItemClickClass onItemClickClass,ImageFetcher imageFetcher) {
		this.context=context;
		this.data=data;
		this.onItemClickClass=onItemClickClass;
		util=new Util(context);
		holderlist=new ArrayList<View>();
        mImageFetcher=imageFetcher;
    }
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		Holder holder;
		if (arg0 != index && arg0 > index) {
			index=arg0;
			arg1= LayoutInflater.from(context).inflate(R.layout.img_gallery_item, null);
			holder=new Holder();
			holder.imageView=(ImageView) arg1.findViewById(R.id.imageView1);
			holder.checkBox=(CheckBox) arg1.findViewById(R.id.checkBox1);
			arg1.setTag(holder);
			holderlist.add(arg1);
		}else {
			holder= (Holder)holderlist.get(arg0).getTag();
			arg1=holderlist.get(arg0);
		}

        mImageFetcher.loadImage(data.get(arg0),holder.imageView,null);

		arg1.setOnClickListener(new OnPhotoClick(arg0, holder.checkBox));
		return arg1;
	}
	
	class Holder{
		ImageView imageView;
		CheckBox checkBox;
	}


	public interface OnItemClickClass{
		public void OnItemClick(View v, int Position, CheckBox checkBox);
	}
	
	class OnPhotoClick implements OnClickListener {
		int position;
		CheckBox checkBox;
		
		public OnPhotoClick(int position,CheckBox checkBox) {
			this.position=position;
			this.checkBox=checkBox;
		}
		@Override
		public void onClick(View v) {
			if (data!=null && onItemClickClass!=null ) {
				onItemClickClass.OnItemClick(v, position, checkBox);
			}
		}
	}
	
}
