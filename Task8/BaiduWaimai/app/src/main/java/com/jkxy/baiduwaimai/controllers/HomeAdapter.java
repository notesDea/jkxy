package com.jkxy.baiduwaimai.controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jkxy.baiduwaimai.R;

import java.util.List;

/**
 * Created by notes on 2016/8/25.
 */

public class HomeAdapter extends BaseAdapter {

    private List<HomeItem> mItems;
    private Context mContext;

    public HomeAdapter(List<HomeItem> mItems, Context context) {
        this.mItems = mItems;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAvgTime, tvDistributionFee, tvStartingFee, tvDistance, tvSoldNumbers,
         tvShopName, tvCoupon, tvInvoice, tvPay, tvPayment, tvMian, tvJian, tvQuan;
        private RatingBar ratingBarShop;
        private ImageView ivShopIcon;
        private LinearLayout layoutMian, layoutJian, layoutQuan;

        public ViewHolder(View itemView) {
            super(itemView);
            tvShopName = (TextView) itemView.findViewById(R.id.tv_home_shopname);
            ivShopIcon = (ImageView) itemView.findViewById(R.id.iv_home_shopicon);
            tvAvgTime = (TextView) itemView.findViewById(R.id.tvAvgTime);
            tvDistributionFee = (TextView) itemView.findViewById(R.id.tv_home_distributionfee);
            tvStartingFee = (TextView) itemView.findViewById(R.id.tv_home_startingfee);
            tvDistance = (TextView) itemView.findViewById(R.id.tv_home_distance);
            tvSoldNumbers = (TextView) itemView.findViewById(R.id.tv_home_soldnumbers);
            ratingBarShop = (RatingBar) itemView.findViewById(R.id.ratingbar_home_star);
            tvCoupon = (TextView) itemView.findViewById(R.id.tv_home_coupon);
            tvInvoice = (TextView) itemView.findViewById(R.id.tv_home_invoice);
            tvPay = (TextView) itemView.findViewById(R.id.tv_home_pay);
            tvPayment = (TextView) itemView.findViewById(R.id.tv_home_payment);
            tvJian = (TextView) itemView.findViewById(R.id.tv_home_jian);
            tvMian = (TextView) itemView.findViewById(R.id.tv_home_mian);
            tvQuan = (TextView) itemView.findViewById(R.id.tv_home_quan);
            layoutJian = (LinearLayout) itemView.findViewById(R.id.linearlayout_home_jian);
            layoutMian = (LinearLayout) itemView.findViewById(R.id.linearlayout_home_mian);
            layoutQuan = (LinearLayout) itemView.findViewById(R.id.linearlayout_home_quan);
        }
    }

    @Override
    public int getCount() {
        if (mItems == null) {
            return 0;
        }
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_home, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }

        HomeItem item = mItems.get(position);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        initHolderView(holder, item);

        //设置卷|票|付|赔的可见状态
        setViewVisible(holder.tvCoupon, item.isCoupon());
        setViewVisible(holder.tvInvoice, item.isInvoice());
        setViewVisible(holder.tvPay, item.isPay());
        setViewVisible(holder.tvPayment, item.isPayment());


        String mianDetails = item.getMianDetails();
        String jianDetails = item.getJianDetails();
        String quanDetails = item.getQuanDetails();
        if (setViewVisible(holder.layoutJian, !jianDetails.equals(""))) {
            holder.tvJian.setText(item.getJianDetails());
        }
        if (setViewVisible(holder.layoutMian, !mianDetails.equals(""))) {
            holder.tvMian.setText(item.getMianDetails());
        }
        if (setViewVisible(holder.layoutQuan, !quanDetails.equals(""))) {
            holder.tvQuan.setText(item.getQuanDetails());
        }

        return convertView;
    }

    private void initHolderView(ViewHolder holder, HomeItem item) {
        holder.tvShopName.setText(item.getShopName());
        holder.ivShopIcon.setImageDrawable(mContext.getDrawable(item.getShopIconSrc()));
        holder.tvAvgTime.setText(mContext.getString(R.string.home_avgtime, item.getAvgTime()));
        holder.tvDistributionFee.setText(mContext.getString(R.string.home_distributionfee,
                item.getDistributionFee()));
        holder.tvStartingFee.setText(mContext.getString(R.string.home_startingfee,
                item.getStartingFee()));
        holder.tvDistance.setText(item.getDistance());
        holder.tvSoldNumbers.setText(mContext.getString(R.string.home_soldnumbers,
                item.getSoldNumbers()));
        holder.ratingBarShop.setRating(item.getNumberOfStar());
    }

    /**
     *
     * @param view 更改显示状态的 View
     * @param state 如果是 true Visibility的状态为VISIBLE，否则为 GONE
     * @return @param state
     */
    public boolean setViewVisible(View view, boolean state) {
        if (state) {
            viewVisibleState(view, View.VISIBLE);
        } else {
            viewVisibleState(view, View.GONE);
        }
        return state;
    }

    /**
     *
     * @param view 更改显示状态的 View
     * @param visibleState 可选为参数为 View.VISIBLE, View.GONE, View.INVISIBLE
     */
    private void viewVisibleState(View view, int visibleState) {
        view.setVisibility(visibleState);
    }

    public boolean add(HomeItem item) {
        return mItems.add(item);
    }

    public HomeItem remove(int position) {
        return mItems.remove(position);
    }

    public boolean remove(HomeItem item) {
        return mItems.remove(item);
    }


}
