package com.jkxy.baiduwaimai.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jkxy.baiduwaimai.R;
import com.jkxy.baiduwaimai.controllers.HomeAdapter;
import com.jkxy.baiduwaimai.controllers.HomeItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HomeFragment extends Fragment {

    private HomeItem[] cells = new HomeItem[]{
            new HomeItem(R.drawable.shopone, "麻辣烫", 4.5f, true, true, false, false, 25, 2, 45,
                    "1.4km", 31, "在线支付满16元减5元,满30元减10元", "", "支付满10元，随机赢取霸王餐券"),
            new HomeItem(R.drawable.shoptwo, "一洋码头", 3.0f, false, false, true, true, 80, 5, 45,
                    "4.6km", 22, "在线支付满80元8减35元，120元减45元", "", ""),
            new HomeItem(R.drawable.shopthree, "麦当劳", 1.0f, true, false, false, false, 55, 11, 500,
                    "3.0km", 333, "在线支付满20元减5元，满30元减7元，满50元减11元", "满100减10", "满100减10"),
            new HomeItem(R.drawable.shopone, "正宗重庆麻辣烫（杭州分店）", 3.5f, false, true, true, true,
                    88, 4, 1, "300", 556, "", "", ""),
            new HomeItem(R.drawable.shoptwo, "肯德基", 3.0f, false, false, true, false, 20, 5, 10,
                    "1km", 1001, "满50元减3元，满10元减7元", "", "支付满10元赢取霸王餐"),
            new HomeItem(R.drawable.shopthree, "麻辣烫2", 4.5f, false, false, false, false, 30, 5, 12,
                    "1.2km", 556, "", "满10单可以免1单", ""),};
    private List<HomeItem> mItems;
    private HomeAdapter adapter;
    private ListView homeListView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home_page, container, false);
        homeListView = (ListView) rootView.findViewById(R.id.homeListView);

        mItems = new ArrayList<>(Arrays.asList(cells));
        adapter = new HomeAdapter(mItems, getContext());
        homeListView.setAdapter(adapter);

        homeListView.addHeaderView(inflater.inflate(R.layout.include_home_category, container, false));
        homeListView.addHeaderView(inflater.inflate(R.layout.include_home_title, container, false));
        return rootView;
    }

}
