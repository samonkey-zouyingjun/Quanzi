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

public class JiaoLiuAdapter extends MyBaseAdapter<JinXuanBean.Content.JinXuanList>{
    public JiaoLiuAdapter(Context contenxt, List data) {
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
                        convertView = View.inflate(context, R.layout.inflate_ziyuan,null);
                        vh.ivcontent1 = (ImageView) convertView.findViewById(R.id.iv_jinxuan_contentimg);
                        break;
                    case 1:
                        convertView = View.inflate(context, R.layout.inflate_jiaoliu2,null);
                        vh.ivcontent1 = (ImageView) convertView.findViewById(R.id.iv_jiaoliu_1);
                        vh.ivcontent3 = (ImageView) convertView.findViewById(R.id.iv_jiaoliu_3);
                        break;
                    case 2:
                        convertView = View.inflate(context,R.layout.inflate_jiaoliu3,null);
                        vh.ivcontent1 = (ImageView) convertView.findViewById(R.id.iv_jiaoliu_1);
                        vh.ivcontent2 = (ImageView) convertView.findViewById(R.id.iv_jiaoliu_2);
                        vh.ivcontent3 = (ImageView) convertView.findViewById(R.id.iv_jiaoliu_3);
                        break;
                }
                vh.tvtitle  = (TextView) convertView.findViewById(R.id.tv_quanzi_jinxuan_title);
                vh.user  = (TextView) convertView.findViewById(R.id.tv_quanzi_ziyaun_user);
                vh.tveye = (TextView) convertView.findViewById(R.id.tv_quanzi_ziyaun_eye);
                vh.tvcomment = (TextView) convertView.findViewById(R.id.tv_quanzi_ziyaun_comment);
                vh.civ = (CircleImageView) convertView.findViewById(R.id.civ_quanzi_ziyuan);
            convertView.setTag(vh);

        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        JinXuanBean.Content.JinXuanList jinxuan = getItem(position);
        String articleTitle = jinxuan.getArticleTitle();
        String userImgPath = jinxuan.getUserImgPath();
        String userName = jinxuan.getUserName();

        int readCount = jinxuan.getReadCount();
        int replyCount = jinxuan.getReplyCount();

//        Log.v("Adapter","adater---------"+articleTitle+ivcontent);

        vh.user.setText(userName);
        AsyncImageLoader.dispaly(userImgPath,vh.civ);
        vh.tvtitle.setText(articleTitle);
        vh.tveye.setText(String.valueOf(readCount));
        vh.tvcomment.setText(String.valueOf(replyCount));

        switch (itemViewType){
            case 2:
                String ivcontent = jinxuan.getContentList().get(0).getContent();
                String ivcontent2 = jinxuan.getContentList().get(1).getContent();
                String ivcontent3 = jinxuan.getContentList().get(2).getContent();
                AsyncImageLoader.dispaly(ivcontent,vh.ivcontent1);
                AsyncImageLoader.dispaly(ivcontent2,vh.ivcontent2);
                AsyncImageLoader.dispaly(ivcontent3,vh.ivcontent3);
                break;
            case 1:
                String ivcontent21 = jinxuan.getContentList().get(0).getContent();
                String ivcontent22 = jinxuan.getContentList().get(1).getContent();
                AsyncImageLoader.dispaly(ivcontent21,vh.ivcontent1);
                AsyncImageLoader.dispaly(ivcontent22,vh.ivcontent3);
                break;
            case 0:
                List<JinXuanBean.Content.JinXuanList.ContentList> clist = jinxuan.getContentList();
                if(clist !=null&&clist.size()!=0){
                    String ivcontent31 = clist.get(0).getContent();
                    AsyncImageLoader.dispaly(ivcontent31,vh.ivcontent1);
                }
                break;
        }



        return convertView;
    }

    class ViewHolder{
        TextView tvtitle,tveye,tvcomment,user;
        ImageView ivcontent1,ivcontent2,ivcontent3;
        CircleImageView civ;
    }

    /**总共2中布局*/
    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        //TODO contentList size
        JinXuanBean.Content.JinXuanList jinxuan = getItem(position);

        int size = jinxuan.getContentList().size();

        if(size >=3){
            return 2;
        }else if(size ==2){
            return 1;
        }else{
            return 0;
        }
    }
}
