package com.example.ba101.qimokaoshi.model;

import com.example.ba101.qimokaoshi.bean.ListBean;

import java.util.List;

/**
 * Created by Ba101 on 2019/1/15.
 */

public interface IModel {

    interface HttpFinish{
        void setShowList(List<ListBean.RecentBean> list);
        void setShowError(String error);
    }

    void getDataM(String url,HttpFinish httpFinish);
}
