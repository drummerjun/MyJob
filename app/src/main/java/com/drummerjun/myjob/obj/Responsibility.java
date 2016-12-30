package com.drummerjun.myjob.obj;

/**
 * Created by JunyenHuang on 12/30/2016.
 */

public class Responsibility {
    private int companyId;
    private String summary;
    private String description;

    public void setCompanyId(int id) {
        companyId = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setDescription(String desc) {
        description = desc;
    }

    public String getDescription() {
        return description;
    }
}
