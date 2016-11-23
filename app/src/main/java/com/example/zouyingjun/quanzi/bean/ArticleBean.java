package com.example.zouyingjun.quanzi.bean;

import java.util.List;

/**
 * Created by j on 2016/11/17.
 */

public class ArticleBean {
    private int code;
    private String message;
    private Content content;
    public static class Content{
        String createTime;
        int collectCount;
        int replyCount;
        int likeCount;
        String userImgPath;
        String typeTitle;
        String articleTitle;
        int readCount;
        List<ContentList> contentList;
        String userName;
        int contentType;
        int like;
        int articleId;
        int collect;

        public static class ContentList{
            String content;
            int contentId;
            int contentSort;
            String contentType;
            int imgHeight;
            int imgWidth;

            @Override
            public String toString() {
                return "ContentList{" +
                        "content='" + content + '\'' +
                        ", contentId=" + contentId +
                        ", contentSort=" + contentSort +
                        ", contentType='" + contentType + '\'' +
                        ", imgHeight=" + imgHeight +
                        ", imgWidth=" + imgWidth +
                        '}';
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getContentId() {
                return contentId;
            }

            public void setContentId(int contentId) {
                this.contentId = contentId;
            }

            public int getContentSort() {
                return contentSort;
            }

            public void setContentSort(int contentSort) {
                this.contentSort = contentSort;
            }

            public String getContentType() {
                return contentType;
            }

            public void setContentType(String contentType) {
                this.contentType = contentType;
            }

            public int getImgHeight() {
                return imgHeight;
            }

            public void setImgHeight(int imgHeight) {
                this.imgHeight = imgHeight;
            }

            public int getImgWidth() {
                return imgWidth;
            }

            public void setImgWidth(int imgWidth) {
                this.imgWidth = imgWidth;
            }

            public ContentList(String content, int contentId, int contentSort, String contentType, int imgHeight, int imgWidth) {
                this.content = content;
                this.contentId = contentId;
                this.contentSort = contentSort;
                this.contentType = contentType;
                this.imgHeight = imgHeight;
                this.imgWidth = imgWidth;
            }
        }

        @Override
        public String toString() {
            return "Content{" +
                    "createTime='" + createTime + '\'' +
                    ", collectCount=" + collectCount +
                    ", replyCount=" + replyCount +
                    ", likeCount=" + likeCount +
                    ", userImgPath='" + userImgPath + '\'' +
                    ", typeTitle='" + typeTitle + '\'' +
                    ", articleTitle='" + articleTitle + '\'' +
                    ", readCount=" + readCount +
                    ", contentList=" + contentList +
                    ", userName='" + userName + '\'' +
                    ", contentType=" + contentType +
                    ", like=" + like +
                    ", articleId=" + articleId +
                    ", collect=" + collect +
                    '}';
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public String getUserImgPath() {
            return userImgPath;
        }

        public void setUserImgPath(String userImgPath) {
            this.userImgPath = userImgPath;
        }

        public String getTypeTitle() {
            return typeTitle;
        }

        public void setTypeTitle(String typeTitle) {
            this.typeTitle = typeTitle;
        }

        public String getArticleTitle() {
            return articleTitle;
        }

        public void setArticleTitle(String articleTitle) {
            this.articleTitle = articleTitle;
        }

        public int getReadCount() {
            return readCount;
        }

        public void setReadCount(int readCount) {
            this.readCount = readCount;
        }

        public List<ContentList> getContentList() {
            return contentList;
        }

        public void setContentList(List<ContentList> contentList) {
            this.contentList = contentList;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getContentType() {
            return contentType;
        }

        public void setContentType(int contentType) {
            this.contentType = contentType;
        }

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public int getArticleId() {
            return articleId;
        }

        public void setArticleId(int articleId) {
            this.articleId = articleId;
        }

        public int getCollect() {
            return collect;
        }

        public void setCollect(int collect) {
            this.collect = collect;
        }

        public Content(String createTime, int collectCount, int replyCount, int likeCount, String userImgPath, String typeTitle, String articleTitle, int readCount, List<ContentList> contentList, String userName, int contentType, int like, int articleId, int collect) {
            this.createTime = createTime;
            this.collectCount = collectCount;
            this.replyCount = replyCount;
            this.likeCount = likeCount;
            this.userImgPath = userImgPath;
            this.typeTitle = typeTitle;
            this.articleTitle = articleTitle;
            this.readCount = readCount;
            this.contentList = contentList;
            this.userName = userName;
            this.contentType = contentType;
            this.like = like;
            this.articleId = articleId;
            this.collect = collect;
        }
    }


    @Override
    public String toString() {
        return "ArticleBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public ArticleBean(int code, String message, Content content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }
}
