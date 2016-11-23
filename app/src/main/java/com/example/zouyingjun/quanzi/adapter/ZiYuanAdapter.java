package com.example.zouyingjun.quanzi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zouyingjun.quanzi.R;
import com.example.zouyingjun.quanzi.bean.JinXuanBean;
import com.example.zouyingjun.quanzi.util.AsyncImageLoader;
import com.example.zouyingjun.quanzi.view.CircleImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.util.List;

/**
 * Created by j on 2016/11/15.
 */

public class ZiYuanAdapter extends MyBaseAdapter<JinXuanBean.Content.JinXuanList>{
    public ZiYuanAdapter(Context contenxt, List data) {
        super(contenxt, data);
    }

  

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
                vh = new ViewHolder();
                convertView = View.inflate(context, R.layout.inflate_ziyuan,null);
                vh.ivcontent = (ImageView) convertView.findViewById(R.id.iv_jinxuan_contentimg);
                vh.tvtitle  = (TextView) convertView.findViewById(R.id.tv_quanzi_jinxuan_title);
                vh.tveye = (TextView) convertView.findViewById(R.id.tv_quanzi_ziyaun_eye);
                vh.user = (TextView) convertView.findViewById(R.id.tv_quanzi_ziyaun_user);
                vh.tvcomment = (TextView) convertView.findViewById(R.id.tv_quanzi_ziyaun_comment);
                vh.civ = (CircleImageView) convertView.findViewById(R.id.civ_quanzi_ziyuan);
            convertView.setTag(vh);

        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        JinXuanBean.Content.JinXuanList jinxuan = getItem(position);
        String articleTitle = jinxuan.getArticleTitle();
        String userName = jinxuan.getUserName();
        String userImgPath = jinxuan.getUserImgPath();
        String ivcontent = jinxuan.getContentList().get(0).getContent();
        int readCount = jinxuan.getReadCount();
        int replyCount = jinxuan.getReplyCount();
        Log.v("Adapter","adater---------"+articleTitle+ivcontent);

        AsyncImageLoader.dispaly(ivcontent,vh.ivcontent);
        AsyncImageLoader.dispaly(userImgPath,vh.civ);
        vh.tvtitle.setText(articleTitle);
        vh.user.setText(userName);
        vh.tveye.setText(String.valueOf(readCount));
        vh.tvcomment.setText(String.valueOf(replyCount));

        return convertView;
    }

    class ViewHolder{
        TextView tvtitle,tveye,tvcomment,user;
        ImageView ivcontent;
        CircleImageView civ;
    }
}
