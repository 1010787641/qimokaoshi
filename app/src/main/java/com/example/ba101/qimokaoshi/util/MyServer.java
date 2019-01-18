package com.example.ba101.qimokaoshi.util;

import com.example.ba101.qimokaoshi.bean.ListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Ba101 on 2019/1/15.
 */

public interface MyServer {
    String URL = "http://news-at.zhihu.com/api/4/news/";
    String UPLOAD_URL = "http://yun918.cn/study/public/file_upload.php";

//    hot
    @GET
    Observable<ListBean> getHttp(@Url String url);
}
