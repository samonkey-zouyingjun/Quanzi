package com.example.zouyingjun.quanzi.service;

import com.example.zouyingjun.quanzi.bean.ArticleBean;
import com.example.zouyingjun.quanzi.bean.Comment;
import com.example.zouyingjun.quanzi.bean.JinXuanBean;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by j on 2016/11/16.
 */

public interface GitHubService {
//        @GET("article/list/1/15/1")
//        Call<JinXuanBean> getQuamZiData();
    @GET("article/list/{page}/15/{type}")
    Call<JinXuanBean> getQuamZiData(@Path("page") int page, @Path("type") int type);

    @GET("article/{articleId}")
    Call<ArticleBean> getArticle(@Path("articleId") int articleId);
    @GET("review/article/content/{page}/15/{articleId}")
    Call<Comment> getComment(@Path("page") int page, @Path("articleId") int articleId);
}
