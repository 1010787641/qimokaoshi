package com.example.ba101.qimokaoshi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ba101.qimokaoshi.bean.Bean;
import com.example.ba101.qimokaoshi.util.MyServer;
import com.example.ba101.qimokaoshi.util.Utils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.main2_iv)
    ImageView mMain2Iv;
    @BindView(R.id.bt)
    Button mBt;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.main2_iv, R.id.bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main2_iv:
                //上传文件
                upLoad();
                break;
            case R.id.bt:
                //回传
                Intent intent = getIntent();
                intent.putExtra("data", mUrl);
                setResult(20, intent);
                finish();
                break;
        }
    }
    private void upLoad() {
        File file = new File(Utils.getSDPath() + "/" + "a_01.png");
        OkHttpClient build = new OkHttpClient.Builder()
                .build();
        String type = "image/png";
        if (file.exists()) {
            //file文件流
            RequestBody body = RequestBody.create(MediaType.parse(type), file);
            //创建文件上传对象
            MultipartBody.Builder builder = new MultipartBody.Builder();
            RequestBody build1 = builder.setType(MultipartBody.FORM)
                    .addFormDataPart("key", "1806b")
                    .addFormDataPart("file", file.getName(), body)
                    .build();
            Request build2 = new Request.Builder()
                    .url(MyServer.UPLOAD_URL)
                    .post(build1)
                    .build();
            Call call = build.newCall(build2);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("onFailure+++", e.getMessage());
                }
                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    String string = response.body().string();

                    final Bean bean = new Gson().fromJson(string, Bean.class);
                    if (bean.getCode() == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mUrl = bean.getData().getUrl();
                                Glide.with(Main2Activity.this).load(mUrl).into(mMain2Iv);
                            }
                        });
                    } else {
                        Log.e("onResponse", bean.getRes());
                    }
                }
            });
        }
    }
}