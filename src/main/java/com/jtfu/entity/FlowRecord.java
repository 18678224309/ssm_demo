package com.jtfu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jtfu.eunm.FlowStatus;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jtfu
 * @since 2019-10-18
 */
public class FlowRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer flowId;

    private String flowName;

    private String initiatorName;

    /**
     * 发起人
     */
    private Integer initiator;

    /**
     * 当前审批人
     */
    private Integer currEx;

    private String currExName;


    private FlowStatus status;

    private String flowState;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }
    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }
    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }
    public Integer getInitiator() {
        return initiator;
    }

    public void setInitiator(Integer initiator) {
        this.initiator = initiator;
    }
    public Integer getCurrEx() {
        return currEx;
    }

    public void setCurrEx(Integer currEx) {
        this.currEx = currEx;
    }
    public String getCurrExName() {
        return currExName;
    }

    public void setCurrExName(String currExName) {
        this.currExName = currExName;
    }
    public FlowStatus getStatus() {
        return status;
    }

    public void setStatus(FlowStatus status) {
        this.status = status;
    }
    public String getFlowState() {
        return flowState;
    }

    public void setFlowState(String flowState) {
        this.flowState = flowState;
    }
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "FlowRecord{" +
            "id=" + id +
            ", flowId=" + flowId +
            ", flowName=" + flowName +
            ", initiatorName=" + initiatorName +
            ", initiator=" + initiator +
            ", currEx=" + currEx +
            ", currExName=" + currExName +
            ", status=" + status +
            ", flowState=" + flowState +
            ", beginTime=" + beginTime +
            ", endTime=" + endTime +
        "}";
    }
}
