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
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.util.List;

/**
 * Created by j on 2016/11/15.
 */

public class JinXuanAdapter extends MyBaseAdapter<JinXuanBean.Content.JinXuanList>{
    public JinXuanAdapter(Context contenxt, List data) {
        super(contenxt, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        int itemViewType = getItemViewType(position);
        if(convertView==null){
                vh = new ViewHolder();
                switch (itemViewType){
                    case 0:
                        convertView = View.inflate(context, R.layout.inflate_jianxuan_1,null);
                        break;
                    case 1:
                        convertView = View.inflate(context, R.layout.inflate_jianxuan_2,null);
                        break;
                }
                vh.ivcontent = (ImageView) convertView.findViewById(R.id.iv_jinxuan_contentimg);
                vh.tvtitle  = (TextView) convertView.findViewById(R.id.tv_quanzi_jinxuan_title);
                vh.tveye = (TextView) convertView.findViewById(R.id.tv_quanzi_jixuan_eye);
                vh.tvcomment = (TextView) convertView.findViewById(R.id.tv_quanzi_jixuan_comment);
                vh.tvtime = (TextView) convertView.findViewById(R.id.tv_quanzi_jixuan_time);

            convertView.setTag(vh);

        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        JinXuanBean.Content.JinXuanList jinxuan = getItem(position);
        String articleTitle = jinxuan.getArticleTitle();
        String ivcontent = jinxuan.getContentList().get(0).getContent();
        int readCount = jinxuan.getReadCount();
        int replyCount = jinxuan.getReplyCount();
        String createTime = jinxuan.getCreateTime();
        Log.v("Adapter","adater---------"+articleTitle+ivcontent);


//        ImageLoader.getInstance().displayImage(ivcontent,vh.ivcontent,option_1);
//        ImageLoader.getInstance().displayImage(ivcontent,vh.ivcontent,option_1);
        AsyncImageLoader.dispaly(ivcontent,vh.ivcontent);
        vh.tvtitle.setText(articleTitle);
        vh.tveye.setText(String.valueOf(readCount));
        vh.tvcomment.setText(String.valueOf(replyCount));
        vh.tvtime.setText(createTime);

        return convertView;
    }

    class ViewHolder{
        TextView tvtitle,tveye,tvcomment,tvtime;
        ImageView ivcontent;
    }

    /**总共2中布局*/
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        //TODO 能被4正除
        if(position%4==0){
            return 1;
        }else{
            return 0;
        }
    }
}
