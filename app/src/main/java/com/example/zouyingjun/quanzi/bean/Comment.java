package com.example.zouyingjun.quanzi.bean;

import java.util.List;

/**
 * Created by j on 2016/11/18.
 */

public class Comment {
    private int code;
    private String message;
    private Content content;
    public static class Content{
        int pageNumber;
        int pageSize;
        int total;
        int totalPages;
        List<ListComment> list;
        public static class ListComment{
            String createTime;
            int likeCount;
            String reviewContent;
            String imgPath;
            String reviewUser;
            int like;
            int reviewId;
            int reviewParent;

            public String getReviewContent() {
                return reviewContent;
            }

            public void setReviewContent(String reviewContent) {
                this.reviewContent = reviewContent;
            }

            @Override
            public String toString() {
                return "ListComment{" +
                        "createTime='" + createTime + '\'' +
                        ", likeCount=" + likeCount +
                        ", reviewContent='" + reviewContent + '\'' +
                        ", imgPath='" + imgPath + '\'' +
                        ", reviewUser='" + reviewUser + '\'' +
                        ", like=" + like +
                        ", reviewId=" + reviewId +
                        ", reviewParent=" + reviewParent +
                        '}';
            }

            public ListComment(String createTime, int likeCount, String reviewContent, String imgPath, String reviewUser, int like, int reviewId, int reviewParent) {
                this.createTime = createTime;
                this.likeCount = likeCount;
                this.reviewContent = reviewContent;
                this.imgPath = imgPath;
                this.reviewUser = reviewUser;
                this.like = like;
                this.reviewId = reviewId;
                this.reviewParent = reviewParent;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(int likeCount) {
                this.likeCount = likeCount;
            }



            public String getImgPath() {
                return imgPath;
            }

            public void setImgPath(String imgPath) {
                this.imgPath = imgPath;
            }

            public String getReviewUser() {
                return reviewUser;
            }

            public void setReviewUser(String reviewUser) {
                this.reviewUser = reviewUser;
            }

            public int getLike() {
                return like;
            }

            public void setLike(int like) {
                this.like = like;
            }

            public int getReviewId() {
                return reviewId;
            }

            public void setReviewId(int reviewId) {
                this.reviewId = reviewId;
            }

            public int getReviewParent() {
                return reviewParent;
            }

            public void setReviewParent(int reviewParent) {
                this.reviewParent = reviewParent;
            }

            public ListComment(String createTime, int likeCount, int reviewContent, String imgPath, String reviewUser, int like, int reviewId, int reviewParent) {
                this.createTime = createTime;
                this.likeCount = likeCount;
                this.imgPath = imgPath;
                this.reviewUser = reviewUser;
                this.like = like;
                this.reviewId = reviewId;
                this.reviewParent = reviewParent;
            }
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

        public List<ListComment> getList() {
            return list;
        }

        public void setList(List<ListComment> list) {
            this.list = list;
        }

        public Content(int pageNumber, int pageSize, int total, int totalPages, List<ListComment> list) {
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
            this.total = total;
            this.totalPages = totalPages;
            this.list = list;
        }
    }


    @Override
    public String toString() {
        return "Comment{" +
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

    public Comment(int code, String message, Content content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }
}
