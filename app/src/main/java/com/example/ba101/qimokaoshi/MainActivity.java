package com.example.ba101.qimokaoshi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ba101.qimokaoshi.bean.ListBean;
import com.example.ba101.qimokaoshi.model.ModelImp;
import com.example.ba101.qimokaoshi.presenter.PresenterImp;
import com.example.ba101.qimokaoshi.util.MyRecyAdapter;
import com.example.ba101.qimokaoshi.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.tool_iv)
    ImageView mToolIv;
    @BindView(R.id.tool_tv)
    TextView mToolTv;
    @BindView(R.id.tool)
    Toolbar mTool;
    @BindView(R.id.recy)
    XRecyclerView mRecy;

    private List<ListBean.RecentBean> mList = new ArrayList<>();
    private MyRecyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTool.setTitle("");
        setSupportActionBar(mTool);
        mRecy.setLayoutManager(new LinearLayoutManager(this));

        PresenterImp presenterImp = new PresenterImp(new ModelImp(), this);
        presenterImp.getDataP(0);
//        绑定适配器
        mAdapter = new MyRecyAdapter(mList, this);
        mRecy.setAdapter(mAdapter);
    }
//    重写回调方法
    @Override
    public void showList(List<ListBean.RecentBean> list) {
        mAdapter.setData(list);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        Log.e("showError", error);
    }
//    点击跳转
    @OnClick(R.id.tool_iv)
    public void onViewClicked() {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivityForResult(intent, 10);
    }
    //回传
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == 20) {
            String url = data.getStringExtra("data");

            RequestOptions res = new RequestOptions().circleCrop();
            Glide.with(this).load(url).apply(res).into(mToolIv);
        }
    }
}
