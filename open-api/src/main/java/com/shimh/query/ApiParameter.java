package com.shimh.query;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @author: shimh
 * @create: 2018年08月
 **/
public class ApiParameter {

    private List<Integer> storeIds;

    @NotBlank
    private String startDate;

    @NotBlank
    private String endDate;

    public List<Integer> getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(List<Integer> storeIds) {
        this.storeIds = storeIds;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ApiParameter{" +
                "storeIds=" + storeIds +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
