package com.sy.cfproject.tool.responsebean;

/**
 * Created by Trust on 2017/3/7.
 */
public class ResponseLogingBean {


    /**
     * content : {"pushId":"","nickName":"","termId":0}
     * status : true
     */

    private ContentBean content;
    private boolean status;

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ContentBean getContent() {
        return content;
    }

    public boolean getStatus() {
        return status;
    }

    public static class ContentBean {
        /**
         * pushId :
         * nickName :
         * termId : 0
         */

        private String pushId;
        private String nickName;
        private long termId;

        public void setPushId(String pushId) {
            this.pushId = pushId;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public void setTermId(long termId) {
            this.termId = termId;
        }

        public String getPushId() {
            return pushId;
        }

        public String getNickName() {
            return nickName;
        }

        public long getTermId() {
            return termId;
        }
    }
}
