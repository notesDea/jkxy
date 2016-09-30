package com.jkxy.baiduwaimai.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jkxy.baiduwaimai.R;
import com.jkxy.baiduwaimai.controllers.MineAdapter;
import com.jkxy.baiduwaimai.controllers.MineItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {
    private ListView listView;
    private View rootView;
    private MineAdapter adapters;
    private String[][] texts = {{"我的送餐地址", "我的代金卷", "我的退款"},
            {"我的消息", "我的收藏", "我的评论"},
            {"百度钱包", "百度糯米"},
            {"常见问题"}};
    private MineItem[] cells = {
            new MineItem(R.drawable.receive_address_loc_icon, texts[0][0], View.GONE),
            new MineItem(R.drawable.mypage_list_icon_daijinjuan, texts[0][1], View.GONE),
            new MineItem(R.drawable.my_balance_icon, texts[0][2], View.VISIBLE),
            new MineItem(R.drawable.my_messages, texts[1][0], View.GONE),
            new MineItem(R.drawable.mypage_list_icon_star, texts[1][1], View.GONE),
            new MineItem(R.drawable.mypage_list_icon_comment, texts[1][2], View.VISIBLE),
            new MineItem(R.drawable.mypage_list_icon_wallet, texts[2][0], View.GONE),
            new MineItem(R.drawable.icon_nuomi, texts[2][1], View.VISIBLE),
            new MineItem(R.drawable.icon_common_problem, texts[3][0], View.VISIBLE),};

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_mine, container, false);
        ArrayList<MineItem> list = new ArrayList<>(Arrays.asList(cells));

        adapters = new MineAdapter(list, getContext());
        listView = (ListView) rootView.findViewById(R.id.lv_mine_item);
        listView.setAdapter(adapters);
        //添加视图到页眉
        listView.addHeaderView(LayoutInflater.from(getContext()).inflate(R.layout.include_mine_head,
                container, false));

        //添加视图到页尾
        listView.addFooterView(LayoutInflater.from(getContext()).inflate(R.layout.include_mine_foot,
                container, false));
        return rootView;
    }
}
