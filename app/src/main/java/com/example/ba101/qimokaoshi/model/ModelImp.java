package com.example.ba101.qimokaoshi.model;

import com.example.ba101.qimokaoshi.bean.ListBean;
import com.example.ba101.qimokaoshi.util.MyServer;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ba101 on 2019/1/15.
 */

public class ModelImp implements IModel {
    @Override
    public void getDataM(String url, final HttpFinish httpFinish) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer server = retrofit.create(MyServer.class);
        final Observable<ListBean> http = server.getHttp(url);
        http.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListBean value) {
                        httpFinish.setShowList(value.getRecent());
                    }

                    @Override
                    public void onError(Throwable e) {
                        httpFinish.setShowError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
