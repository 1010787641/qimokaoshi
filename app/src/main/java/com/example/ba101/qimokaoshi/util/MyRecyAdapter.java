package com.example.ba101.qimokaoshi.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ba101.qimokaoshi.R;
import com.example.ba101.qimokaoshi.bean.ListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by Ba101 on 2019/1/15.
 */

public class MyRecyAdapter extends XRecyclerView.Adapter {
    private List<ListBean.RecentBean> mList;
    private Context mContext;
    private static final int ONE = 1;
    private static final int TWO = 2;
    public MyRecyAdapter(List<ListBean.RecentBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ONE){
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item, null, false);
            MyViewHolder holder = new MyViewHolder(inflate);
            return holder;
        }else{
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item2, null, false);
            MyViewHolder2 holder = new MyViewHolder2(inflate);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            MyViewHolder holder1 = (MyViewHolder) holder;
            Glide.with(mContext).load(mList.get(position).getThumbnail()).into(holder1.mIv);
            holder1.mTv.setText(mList.get(position).getTitle());
        }else {
            MyViewHolder2 holder1 = (MyViewHolder2) holder;
            Glide.with(mContext).load(mList.get(position).getThumbnail()).into(holder1.mIv);
            holder1.mTv.setText(mList.get(position).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 ==0){
            return ONE;
        }else{
            return TWO;
        }
    }
    public void setData(List<ListBean.RecentBean> list ){
        mList = list;
        notifyDataSetChanged();
    }
    class  MyViewHolder extends XRecyclerView.ViewHolder {

        private ImageView mIv;
        private TextView mTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.item_iv);
            mTv = itemView.findViewById(R.id.item_tv);
        }
    }
    class  MyViewHolder2 extends XRecyclerView.ViewHolder {

        private ImageView mIv;
        private TextView mTv;

        public MyViewHolder2(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.item2_iv);
            mTv = itemView.findViewById(R.id.item2_tv);
        }
    }
}
