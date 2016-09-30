package com.jkxy.baiduwaimai.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jkxy.baiduwaimai.R;

import java.util.List;

/**
 * “我的”界面的适配器
 * Created by notes on 2016/8/22.
 */

public class MineAdapter extends BaseAdapter {

    private List<MineItem> items;
    private Context context;


    public MineAdapter(List<MineItem> items, Context context) {
        this.context = context;
        this.items = items;
    }
    @Override
    public int getCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_mine, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }

        MineItem list = items.get(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.mineIcon.setImageDrawable(context.getDrawable(list.getIconResId()));
        viewHolder.mineText.setText(list.getText());
        //设置背景条的可见度
        viewHolder.ivBackgroundBar.setVisibility(list.getSeparatorBarIsVisible());
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    public static class ViewHolder{
        private ImageView mineIcon, ivBackgroundBar;
        private TextView mineText;

        public ViewHolder(View item) {
            mineIcon = (ImageView) item.findViewById(R.id.iv_mine_icon);
            ivBackgroundBar = (ImageView) item.findViewById(R.id.iv_mine_backgroundbar);
            mineText = (TextView) item.findViewById(R.id.tv_mine_item);
        }
    }

    public boolean addItem(MineItem item) {
        return items.add(item);
    }

    public MineItem removeItem(int position) {
        return items.remove(position);
    }

    public boolean removeItem(MineItem item) {
        return items.remove(item);
    }
}
