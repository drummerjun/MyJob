package com.drummerjun.myjob.obj;

/**
 * Created by JunyenHuang on 12/30/2016.
 */

public class Referral {
    private int companyId;
    private String name;
    private String title;
    private String relation;
    private String phone;
    private String email;

    public void setCompanyId(int id) {
        companyId = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setReferralName(String name) {
        this.name = name;
    }

    public String getReferralName() {
        return name;
    }

    public void setReferralTitle(String title) {
        this.title = title;
    }

    public String getReferralTitle() {
        return title;
    }

    public void setRelaton(String relation) {
        this.relation = relation;
    }

    public String getRelation() {
        return relation;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String mail) {
        email = mail;
    }

    public String getEmail() {
        return email;
    }
}
