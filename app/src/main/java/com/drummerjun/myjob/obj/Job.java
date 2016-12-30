package com.drummerjun.myjob.obj;

import java.util.ArrayList;

/**
 * Created by JunyenHuang on 12/30/2016.
 */

public class Job {
    private int companyId;
    private String companyName;
    private String companyProfile;
    private String city;
    private String country;
    private String department;
    private String jobTitle;
    private long startTimeInMilliSec;
    private long endTimeInMilliSec;
    private ArrayList<Referral> referrals;
    private ArrayList<Responsibility> responsibilities;
    private String reasonForLeaving;

    public void setCompanyId(int id) {
        companyId = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyName(String name) {
        companyName = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyProfile(String profile) {
        companyProfile = profile;
    }

    public String getCompanyProfile() {
        return companyProfile;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setDepartment(String dept) {
        department = dept;
    }

    public String getDepartment() {
        return department;
    }

    public void setJobTitle(String title) {
        jobTitle = title;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setStartTimeInMilliSec(long time) {
        startTimeInMilliSec = time;
    }

    public long getStartTimeInMilliSec() {
        return startTimeInMilliSec;
    }

    public void setEndTimeInMilliSec(long time) {
        endTimeInMilliSec = time;
    }

    public long getEndTimeInMilliSec() {
        return endTimeInMilliSec;
    }

    public void setResponsibilities(ArrayList<Responsibility> list) {
        responsibilities = list;
    }

    public ArrayList<Responsibility> getResponsibilities() {
        return responsibilities;
    }

    public void setReferrals(ArrayList<Referral> list) {
        referrals = list;
    }

    public ArrayList<Referral> getReferrals() {
        return referrals;
    }

    public void setReasonForLeaving(String reason) {
        reasonForLeaving = reason;
    }

    public String getReasonForLeaving() {
        return reasonForLeaving;
    }
}
