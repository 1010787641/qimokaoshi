package com.example.ba101.qimokaoshi.presenter;

import com.example.ba101.qimokaoshi.bean.ListBean;
import com.example.ba101.qimokaoshi.model.IModel;
import com.example.ba101.qimokaoshi.view.IView;

import java.util.List;

/**
 * Created by Ba101 on 2019/1/15.
 */

public class PresenterImp implements IPresenter, IModel.HttpFinish {
    private IModel mIModel;
    private IView mIView;

    public PresenterImp(IModel IModel, IView IView) {
        mIModel = IModel;
        mIView = IView;
    }

    @Override
    public void getDataP(int page) {
        if (mIView != null){
            mIModel.getDataM("hot",this);
        }
    }

    @Override
    public void setShowList(List<ListBean.RecentBean> list) {
        if (mIView != null){
            mIView.showList(list);
        }
    }

    @Override
    public void setShowError(String error) {
        if (mIView != null){
            mIView.showError(error);
        }
    }
}
