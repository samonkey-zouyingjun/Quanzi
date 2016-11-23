package com.example.zouyingjun.quanzi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.listener.OnBottomLoadMoreTime;
import com.andview.refreshview.listener.OnTopRefreshTime;
import com.example.zouyingjun.quanzi.R;
import com.example.zouyingjun.quanzi.adapter.JinXuanAdapter;
import com.example.zouyingjun.quanzi.app.MyApp;
import com.example.zouyingjun.quanzi.bean.JinXuanBean;
import com.example.zouyingjun.quanzi.service.GitHubService;
import com.example.zouyingjun.quanzi.ui.AticleActivity;
import com.example.zouyingjun.quanzi.util.RetrofitUtils;
import com.fz.afinal.annotation.view.ViewInject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QJinXuanFragment extends Fragment implements View.OnClickListener {

    int jinXuanPage = 1 ;

    private XRefreshView xf;
    private ListView lv;
    List<String> data;

    public ImageView ivpost;
//    private ArrayAdapter<String> adapter;
    private JinXuanAdapter adapter2;
    List<JinXuanBean.Content.JinXuanList> jinXuanLists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_qjin_xuan, container, false);

        initData();

        initXRefreshView(v);

        ivpost = (ImageView) v.findViewById(R.id.iv_jinxuan_post);

        ivpost.setOnClickListener(this);
        return v;
    }

    private void initData() {
        jinXuanLists = new ArrayList<>();
//        data = new ArrayList();
//        loadData();


    }





    private void loadJianXuan(int page, final boolean isClear){

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://samworld.samonkey.com/v2_0/")
                .client(RetrofitUtils.getClient()).addConverterFactory(GsonConverterFactory.
                        create(new Gson())).build();
        GitHubService gs = retrofit.create(GitHubService.class);
        Call<JinXuanBean> jbc = gs.getQuamZiData(page,1);
        jbc.enqueue(new Callback<JinXuanBean>() {
            @Override
            public void onResponse(Call<JinXuanBean> call, Response<JinXuanBean> response) {
                Log.d("TAG",response.body().toString());

                JinXuanBean jinxuan = response.body();

                List<JinXuanBean.Content.JinXuanList> list = jinxuan.getContent().getList();

                Log.i("TAG","加载数据源"+list.toString());


//                Log.i("TAG","加载数据源--------------------："+jinXuanLists.toString());
                adapter2.addAll(list,isClear);
//                Log.d("TAG","数据：---------------------"+jinXuanLists.toString());
            }

            @Override
            public void onFailure(Call<JinXuanBean> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.toString());
            }
        });

    }

    private void loadData() {
        data.add("A");
        data.add("A");
        data.add("A");
        data.add("A");
        data.add("A");
        data.add("A");
        data.add("A");
        data.add("A");
        data.add("A");
        data.add("A");
        data.add("A");
        data.add("A");
    }

    private void initXRefreshView(View v) {

        xf = (XRefreshView) v.findViewById(R.id.xrfv_jinxuan);
        xf.setPullLoadEnable(true);
        lv = (ListView) v.findViewById(R.id.lv_jianxuan);

//        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
        adapter2 = new JinXuanAdapter(getActivity(),jinXuanLists);

        lv.setAdapter(adapter2);

        View v2 = View.inflate(getActivity(),R.layout.headview_lv_xf,null);
        lv.addHeaderView(v2);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0) return;
//                Toast.makeText(MyApp.context,"articleId", Toast.LENGTH_SHORT).show();

                JinXuanBean.Content.JinXuanList jinxuan = adapter2.getItem(position-1);
                String createTime = jinxuan.getCreateTime();
                String articleTitle = jinxuan.getArticleTitle();
                String userName = jinxuan.getUserName();
                String userImgPath = jinxuan.getUserImgPath();

                int articleId = jinxuan.getArticleId();
//              Toast.makeText(MyApp.context,"articleId", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), AticleActivity.class);
                intent.putExtra("articleId",articleId);
                intent.putExtra("userName",userName);
                intent.putExtra("userImgPath",userImgPath);
                intent.putExtra("createTime",createTime);
                intent.putExtra("articleTitle",articleTitle);

                startActivity(intent);
            }
        });

        loadJianXuan(1,true);


        xf.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                loadJianXuan(1,true);
                jinXuanPage = 1;
                xf.stopRefresh();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                loadJianXuan(++jinXuanPage,false);
                xf.stopLoadMore();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
        xf.setOnTopRefreshTime(new OnTopRefreshTime() {
            @Override
            public boolean isTop() {
//                Log.d("TAG","第一行"+lv.getFirstVisiblePosition());
                return lv.getFirstVisiblePosition() == 0;
            }
        });
        xf.setOnBottomLoadMoreTime(new OnBottomLoadMoreTime() {
            @Override
            public boolean isBottom() {
                return lv.getLastVisiblePosition() == (adapter2.getCount()-1);
            }
        });
    }


    @Override
    public void onClick(View v) {
            Toast.makeText(MyApp.context,"ok",Toast.LENGTH_SHORT).show();
    }
}
