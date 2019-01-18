package com.example.ba101.qimokaoshi.view;

import com.example.ba101.qimokaoshi.bean.ListBean;

import java.util.List;

/**
 * Created by Ba101 on 2019/1/15.
 */

public interface IView {
    void showList(List<ListBean.RecentBean> list);
    void showError(String error);
}
