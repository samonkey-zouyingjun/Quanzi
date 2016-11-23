package com.example.zouyingjun.quanzi.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.example.zouyingjun.quanzi.R;
import com.example.zouyingjun.quanzi.adapter.CommentAdaper;
import com.example.zouyingjun.quanzi.app.MyApp;
import com.example.zouyingjun.quanzi.bean.ArticleBean;
import com.example.zouyingjun.quanzi.bean.Comment;
import com.example.zouyingjun.quanzi.constant.Constant;
import com.example.zouyingjun.quanzi.service.GitHubService;
import com.example.zouyingjun.quanzi.util.AsyncImageLoader;
import com.example.zouyingjun.quanzi.util.RetrofitUtils;
import com.example.zouyingjun.quanzi.view.CircleImageView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AticleActivity extends Activity {

    int articleId;
    String userName,userImgPath,createTime,articleTitle;
    WebView wb;
    ImageView ivback;
    TextView loading;

//    XRefreshView xf;
    ListView lv;
    List<Comment.Content.ListComment> list;

    private static final String APP_CACAHE_DIRNAME = "/webcache";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setSystemStatus();
        setContentView(R.layout.activity_aticle);
        initData();
        initView();
        wb.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //TODO 显示加载页面
//                Toast.makeText(MyApp.context,"onPageStarted",Toast.LENGTH_SHORT).show();
                ivback.setVisibility(View.VISIBLE);
                ivback.setImageResource(R.drawable.img_ing);
                loading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //TODO 隐藏加载页面
//                Toast.makeText(MyApp.context,"onPageFinished",Toast.LENGTH_SHORT).show();
                ivback.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.INVISIBLE);
                tvarticleTitle.setVisibility(View.VISIBLE);
                llowner.setVisibility(View.VISIBLE);
                lluser.setVisibility(View.VISIBLE);
                llcenter.setVisibility(View.VISIBLE);
                wb.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                //TODO 隐藏加载页面
//                Toast.makeText(MyApp.context,"onReceivedError",Toast.LENGTH_SHORT).show();
                    ivback.setVisibility(View.VISIBLE);
                    ivback.setImageResource(R.drawable.img_nointernet);
            }
        });

        loadAritcle(articleId);
    }

    int page = 1;

    private void initView() {
        wb = (WebView) findViewById(R.id.wb_article);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        wb.getSettings().setDomStorageEnabled(true);
        wb.getSettings().setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath()+ APP_CACAHE_DIRNAME;
        wb.getSettings().setDatabasePath(cacheDirPath);
        wb.getSettings().setAppCacheEnabled(true);
        //设置  Application Caches 缓存目录
        wb.getSettings().setAppCachePath(cacheDirPath);
        //开启 Application Caches 功能

        ivback = (ImageView) findViewById(R.id.iv_artitle_webview);
        ImageView ib = (ImageView) findViewById(R.id.iv_back);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        TextView tv = (TextView) findViewById(R.id.tv_article_title);
        tv.setText("详情");
        TextView tvowner = (TextView) findViewById(R.id.tv_article_owner);
        tvowner.setText(userName);
        TextView tvcreateTimer = (TextView) findViewById(R.id.tv_article_time);
        tvcreateTimer.setText(createTime);
        tvarticleTitle = (TextView) findViewById(R.id.tv_article_title_content);
        loading = (TextView) findViewById(R.id.tv_article_loading);
        tvarticleTitle.setText(articleTitle);

        CircleImageView civcontent = (CircleImageView) findViewById(R.id.civ_article_content);
        ImageLoader.getInstance().displayImage(userImgPath,civcontent);
        llowner = (LinearLayout) findViewById(R.id.ll_artitle_owner);

        lluser = (LinearLayout) findViewById(R.id.ll_artitle_user);
        llcenter = (FrameLayout) findViewById(R.id.ll_artitle_center);

        //评论
//        xf = (XRefreshView) findViewById(R.id.xrfv_artitle);
        lv = (ListView) findViewById(R.id.lv_aticle);

        adapter = new CommentAdaper(this,list);
        lv.setAdapter(adapter);
        loadPinLun(1,true);


//        xf.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//
//            @Override
//            public void onLoadMore(boolean isSilence) {
//
//            }
//
//            @Override
//            public void onRelease(float direction) {
//
//            }
//
//            @Override
//            public void onHeaderMove(double headerMovePercent, int offsetY) {
//
//            }
//        });
    }



    FrameLayout llcenter;
    LinearLayout lluser,llowner;
    TextView tvarticleTitle;
    CommentAdaper adapter;

    /**加载评论信息*/
    private void loadPinLun(int page, final boolean isclear) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://samworld.samonkey.com/v2_0/")
                .client(RetrofitUtils.getClient()).addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        GitHubService gs = retrofit.create(GitHubService.class);
        Call<Comment> call = gs.getComment(page, articleId);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                List<Comment.Content.ListComment> list = response.body().getContent().getList();

//                Log.d("TAG","Comment------"+response.body().toString());
                if(list!=null&&list.size()!=0){
                    adapter.addAll(list,isclear);
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.toString());
            }
        });


    }


    private void initData() {
        Intent intent = getIntent();

        articleId = intent.getIntExtra("articleId", -1);
        userName = intent.getStringExtra("userName");
        userImgPath = intent.getStringExtra("userImgPath");
        createTime = intent.getStringExtra("createTime");
        articleTitle = intent.getStringExtra("articleTitle");
        list = new ArrayList<>();

    }

    /**5.0以后会有透明条*/
    private void setSystemStatus() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

//            Log.d("TAG","SNK版本:"+Build.VERSION.SDK_INT);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#FF443F"));

        }

    }

    public void loadAritcle(int articleId){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://samworld.samonkey.com/v2_0/")
                .client(RetrofitUtils.getClient()).addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        GitHubService gs = retrofit.create(GitHubService.class);
        Call<ArticleBean> call = gs.getArticle(articleId);
        call.enqueue(new Callback<ArticleBean>() {
            @Override
            public void onResponse(Call<ArticleBean> call, Response<ArticleBean> response) {
                ArticleBean.Content content = response.body().getContent();
                Log.d("A",content.toString());
                String webString = content.getContentList().get(0).getContent();
                loadData(webString);

            }

            @Override
            public void onFailure(Call<ArticleBean> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.toString());
            }
        });
    }

    private void loadData(String webString) {

        String html5 = Constant.HTML1+webString+Constant.HTML2;
        wb.loadData(html5, "text/html; charset=UTF-8", null);

    }


}
