package com.example.zouyingjun.quanzi.bean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by j on 2016/11/16.
 */

public class JinXuanBean implements Serializable {


    private int code;
    private String message;
    private Content content;

    public static class Content {
        int pageNumber;
        int pageSize;
        int total;
        int totalPages;
        List<JinXuanList> list;
        public static class JinXuanList{
            String createTime;
            int replyCount;
            int likeCount;
            String userImgPath;
            String typeTitle;
            String articleTitle;
            int readCount;
            List<ContentList> contentList;
            String chosenDesc;
            String userName;
            int articleId;
            int resourceType;

            public static class ContentList{
                @Override
                public String toString() {
                    return "ContentList{" +
                            "content='" + content + '\'' +
                            ", contentId=" + contentId +
                            ", contentSort=" + contentSort +
                            ", contentType=" + contentType +
                            ", imgHeight=" + imgHeight +
                            ", imgWidth=" + imgWidth +
                            '}';
                }

                String content;
                int contentId;
                int contentSort;
                int contentType;
                int imgHeight;
                int imgWidth;

                public ContentList(String content, int contentId, int contentSort, int contentType, int imgHeight, int imgWidth) {
                    this.content = content;
                    this.contentId = contentId;
                    this.contentSort = contentSort;
                    this.contentType = contentType;
                    this.imgHeight = imgHeight;
                    this.imgWidth = imgWidth;
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

                public int getContentType() {
                    return contentType;
                }

                public void setContentType(int contentType) {
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

            }

            public JinXuanList(String createTime, int replyCount, int likeCount, String userImgPath, String typeTitle, String articleTitle, int readCount, List<ContentList> contentList, String chosenDesc, String userName, int articleId, int resourceType) {
                this.createTime = createTime;
                this.replyCount = replyCount;
                this.likeCount = likeCount;
                this.userImgPath = userImgPath;
                this.typeTitle = typeTitle;
                this.articleTitle = articleTitle;
                this.readCount = readCount;
                this.contentList = contentList;
                this.chosenDesc = chosenDesc;
                this.userName = userName;
                this.articleId = articleId;
                this.resourceType = resourceType;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
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

            public String getChosenDesc() {
                return chosenDesc;
            }

            public void setChosenDesc(String chosenDesc) {
                this.chosenDesc = chosenDesc;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public int getArticleId() {
                return articleId;
            }

            public void setArticleId(int articleId) {
                this.articleId = articleId;
            }

            public int getResourceType() {
                return resourceType;
            }

            public void setResourceType(int resourceType) {
                this.resourceType = resourceType;
            }

            @Override
            public String toString() {
                return "JinXuanList{" +
                        "createTime='" + createTime + '\'' +
                        ", replyCount=" + replyCount +
                        ", likeCount=" + likeCount +
                        ", userImgPath='" + userImgPath + '\'' +
                        ", typeTitle='" + typeTitle + '\'' +
                        ", articleTitle='" + articleTitle + '\'' +
                        ", readCount=" + readCount +
                        ", contentList=" + contentList +
                        ", chosenDesc='" + chosenDesc + '\'' +
                        ", userName='" + userName + '\'' +
                        ", articleId=" + articleId +
                        ", resourceType=" + resourceType +
                        '}';
            }
        }

        public Content(int pageNumber, int pageSize, int total, int totalPages, List<JinXuanList> list) {
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
            this.total = total;
            this.totalPages = totalPages;
            this.list = list;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public List<JinXuanList> getList() {
            return list;
        }

        public void setList(List<JinXuanList> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "Content{" +
                    "pageNumber=" + pageNumber +
                    ", pageSize=" + pageSize +
                    ", total=" + total +
                    ", totalPages=" + totalPages +
                    ", list=" + list +
                    '}';
        }
    }

    public JinXuanBean(int code, String message, Content content) {
        this.code = code;
        this.message = message;
        this.content = content;
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

    @Override
    public String toString() {
        return "JinXuanBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
