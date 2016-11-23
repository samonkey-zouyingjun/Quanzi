package com.example.zouyingjun.quanzi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zouyingjun.quanzi.R;
import com.example.zouyingjun.quanzi.bean.Comment;
import com.example.zouyingjun.quanzi.util.AsyncImageLoader;
import com.example.zouyingjun.quanzi.view.CircleImageView;

import java.util.List;

/**
 * Created by j on 2016/11/18.
 */

public class CommentAdaper extends MyBaseAdapter<Comment.Content.ListComment> {
    public CommentAdaper(Context context, List<Comment.Content.ListComment> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            vh = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_comment,null);
            vh.civ = (CircleImageView) convertView.findViewById(R.id.civ_comment);
            vh.tvname = (TextView) convertView.findViewById(R.id.tv_comment_user);
            vh.tvcontent = (TextView) convertView.findViewById(R.id.tv_comment_content);
            vh.tvtime = (TextView) convertView.findViewById(R.id.tv_comment_time);
            vh.tvdianzan = (TextView) convertView.findViewById(R.id.tv_commnet_dianzan);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        Comment.Content.ListComment item = getItem(position);
        String createTime = item.getCreateTime();
        String imgPath = item.getImgPath();
        String reviewContent = item.getReviewContent();
        String reviewUser = item.getReviewUser();
        int like = item.getLike();

        AsyncImageLoader.dispaly(imgPath,vh.civ);
        vh.tvname.setText(reviewUser);
        vh.tvcontent.setText(reviewContent);
        vh.tvtime.setText(createTime);
        vh.tvdianzan.setText(""+like);

        return convertView;
    }
    class ViewHolder{
        CircleImageView civ;
        TextView tvname,tvcontent,tvtime,tvdianzan;
        ImageView ivdianzan;

    }
}
