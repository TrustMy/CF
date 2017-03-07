package com.sy.cfproject.tool.requestdata;

/**
 * Created by Trust on 2017/3/7.
 */
public class RequestCarTrajectoryBean {
    private long termId,startTime,endTime;

    public RequestCarTrajectoryBean(long termId, long startTime, long endTime) {
        this.termId = termId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getTermId() {
        return termId;
    }

    public void setTermId(long termId) {
        this.termId = termId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
