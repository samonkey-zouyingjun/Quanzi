package com.example.zouyingjun.quanzi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.listener.OnBottomLoadMoreTime;
import com.andview.refreshview.listener.OnTopRefreshTime;
import com.example.zouyingjun.quanzi.R;
import com.example.zouyingjun.quanzi.adapter.ZiYuanAdapter;
import com.example.zouyingjun.quanzi.bean.JinXuanBean;
import com.example.zouyingjun.quanzi.service.GitHubService;
import com.example.zouyingjun.quanzi.ui.AticleActivity;
import com.example.zouyingjun.quanzi.util.RetrofitUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QZiYuanFragment extends Fragment {

    int pager = 1;

    XRefreshView xrv;
    ListView lv;
    ZiYuanAdapter adapter;
    List<JinXuanBean.Content.JinXuanList> jinxuans;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_qzi_yuan, container, false);

        initData();
        initXRefreshView(v);

        return v;
    }

    private void initData() {
        jinxuans = new ArrayList<>();
        loadZiyuan(1,true);
    }


    private void loadZiyuan(int pager, final boolean isClear){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://samworld.samonkey.com/v2_0/").client(RetrofitUtils.getClient())
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();
        GitHubService gs = retrofit.create(GitHubService.class);
        Call<JinXuanBean> call = gs.getQuamZiData(pager, 3);
        call.enqueue(new Callback<JinXuanBean>() {
            @Override
            public void onResponse(Call<JinXuanBean> call, Response<JinXuanBean> response) {
                Log.d("TAG","ziyuan - "+response.body().toString());

                JinXuanBean jinxuan = response.body();

                List<JinXuanBean.Content.JinXuanList> list = jinxuan.getContent().getList();

                Log.i("TAG","ziyuan -加载数据源"+list.toString());
                adapter.addAll(list,isClear);
            }

            @Override
            public void onFailure(Call<JinXuanBean> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.toString());
            }
        });

    }


    private void initXRefreshView(View v) {
        xrv = (XRefreshView) v.findViewById(R.id.xrfv_ziyaun);
        xrv.setPullLoadEnable(true);
        lv = (ListView) v.findViewById(R.id.lv_ziyuan);
        adapter = new ZiYuanAdapter(getActivity(),jinxuans);
        lv.setAdapter(adapter);
        View v2 = View.inflate(getActivity(),R.layout.headview_lv_xf,null);
        lv.addHeaderView(v2);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Toast.makeText(MyApp.context,"articleId", Toast.LENGTH_SHORT).show();

                JinXuanBean.Content.JinXuanList jinxuan = adapter.getItem(position-1);
                String createTime = jinxuan.getCreateTime();
                String articleTitle = jinxuan.getArticleTitle();
                String userName = jinxuan.getUserName();
                String userImgPath = jinxuan.getUserImgPath();

                int articleId = jinxuan.getArticleId();
//                Toast.makeText(MyApp.context,"articleId", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), AticleActivity.class);
                intent.putExtra("articleId",articleId);
                intent.putExtra("userName",userName);
                intent.putExtra("userImgPath",userImgPath);
                intent.putExtra("createTime",createTime);
                intent.putExtra("articleTitle",articleTitle);

                startActivity(intent);
            }
        });
        xrv.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                loadZiyuan(1,true);
                pager = 1;
                xrv.stopRefresh();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                loadZiyuan(++pager,false);
                xrv.stopLoadMore();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
        xrv.setOnTopRefreshTime(new OnTopRefreshTime() {
            @Override
            public boolean isTop() {
//                Log.d("TAG","第一行"+lv.getFirstVisiblePosition());
                return lv.getFirstVisiblePosition() == 0;
            }
        });
        xrv.setOnBottomLoadMoreTime(new OnBottomLoadMoreTime() {
            @Override
            public boolean isBottom() {
                return lv.getLastVisiblePosition() == (adapter.getCount()-1);
            }
        });
    }

}
