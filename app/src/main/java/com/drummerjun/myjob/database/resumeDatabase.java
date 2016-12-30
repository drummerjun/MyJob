package com.drummerjun.myjob.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.drummerjun.myjob.obj.Job;
import com.drummerjun.myjob.obj.Referral;
import com.drummerjun.myjob.obj.Responsibility;

import java.util.ArrayList;

public class resumeDatabase extends SQLiteOpenHelper {
    private static final int DB_VER = 1;
    public static final String DATABASE_NAME = "junResume.db";
    //table jobs
    private static final String TABLE_JOBS = "jobs";
    private static final String KEY_COMPANY_NAME = "company_name";
    private static final String KEY_COMPANY_PROFILE = "company_profile";
    private static final String KEY_CITY = "city";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_DEPARTMENT = "department";
    private static final String KEY_JOB_TITLE = "job_title";
    private static final String KEY_START = "start";
    private static final String KEY_END = "end";
    private static final String KEY_REASON = "reason";
    //table responsibilities
    private static final String TABLE_RESPONSIBILTIES = "responsibilites";
    private static final String KEY_SUMMARY = "summary";
    private static final String KEY_DESCRIPTION = "description";
    //table referrals
    private static final String TABLE_REFERRAL = "referrals";
    private static final String KEY_REFERRAL_NAME = "r_name";
    private static final String KEY_REFERRAL_TITLE = "r_title";
    private static final String KEY_RELATION = "r_relation";
    private static final String KEY_REFERRAL_PHONE = "r_phone";
    private static final String KEY_REFERRAL_EMAIL = "r_email";
    //common columns
    private static final String KEY_ID = "_id";
    private static final String KEY_COMPANY_ID = "company_id";

    public resumeDatabase(Context context) {
        super(context, DATABASE_NAME, null, DB_VER);
    }

    public resumeDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createJobTable());
        db.execSQL(createResponsibilitiesTable());
        db.execSQL(createReferralTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOBS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESPONSIBILTIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REFERRAL);
        onCreate(db);
    }

    public long addJob(Job job) {
        long new_id;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COMPANY_ID, job.getCompanyId());
        values.put(KEY_COMPANY_NAME, job.getCompanyName());
        values.put(KEY_COMPANY_PROFILE, job.getCompanyProfile());
        values.put(KEY_CITY, job.getCity());
        values.put(KEY_COUNTRY, job.getCountry());
        values.put(KEY_DEPARTMENT, job.getDepartment());
        values.put(KEY_JOB_TITLE, job.getJobTitle());
        values.put(KEY_START, job.getStartTimeInMilliSec());
        values.put(KEY_END, job.getEndTimeInMilliSec());
        values.put(KEY_REASON, job.getReasonForLeaving());
        new_id = db.insert(TABLE_JOBS, null, values);
        db.close();
        return new_id;
    }

    public long addResponsibility(Responsibility resp) {
        long new_id;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COMPANY_ID, resp.getCompanyId());
        values.put(KEY_SUMMARY, resp.getSummary());
        values.put(KEY_DESCRIPTION, resp.getDescription());
        new_id = db.insert(TABLE_RESPONSIBILTIES, null, values);
        db.close();
        return new_id;
    }

    public long addReferral(Referral refer) {
        long new_id;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COMPANY_ID, refer.getCompanyId());
        values.put(KEY_REFERRAL_NAME, refer.getReferralName());
        values.put(KEY_REFERRAL_TITLE, refer.getReferralTitle());
        values.put(KEY_RELATION, refer.getRelation());
        values.put(KEY_REFERRAL_PHONE, refer.getPhone());
        values.put(KEY_REFERRAL_EMAIL, refer.getEmail());
        new_id = db.insert(TABLE_REFERRAL, null, values);
        db.close();
        return new_id;
    }

    public ArrayList<Referral> getReferrals(SQLiteDatabase db, int company_id) {
        ArrayList<Referral> referrals = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_RESPONSIBILTIES
                + " WHERE " + KEY_COMPANY_ID + "=" + String.valueOf(company_id);
        if(db == null) {
            db = getWritableDatabase();
        }
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {
            do {
                Referral referral = new Referral();
                referral.setCompanyId(c.getInt(1));
                referral.setReferralName(c.getString(2));
                referral.setReferralTitle(c.getString(3));
                referral.setRelaton(c.getString(4));
                referral.setPhone(c.getString(5));
                referral.setEmail(c.getString(6));
                referrals.add(referral);
            } while(c.moveToNext());
        }
        //db.close();
        c.close();
        return referrals;
    }

    public ArrayList<Responsibility> getResponsibilities(SQLiteDatabase db, int company_id) {
        ArrayList<Responsibility> responsibilities = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_RESPONSIBILTIES
                + " WHERE " + KEY_COMPANY_ID + "=" + String.valueOf(company_id);
        if(db == null) {
            db = getWritableDatabase();
        }
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {
            do {
                Responsibility responsibility = new Responsibility();
                responsibility.setCompanyId(c.getInt(1));
                responsibility.setSummary(c.getString(2));
                responsibility.setDescription(c.getString(3));
                responsibilities.add(responsibility);
            } while(c.moveToNext());
        }
        //db.close();
        c.close();
        return responsibilities;
    }

    public ArrayList<Job> getJobs() {
        ArrayList<Job> jobs = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_JOBS;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if(c.moveToFirst()) {
            do {
                Job job = new Job();
                job.setCompanyId(c.getInt(1));
                job.setCompanyName(c.getString(2));
                job.setCompanyProfile(c.getString(3));
                job.setCity(c.getString(4));
                job.setCountry(c.getString(5));
                job.setDepartment(c.getString(6));
                job.setJobTitle(c.getString(7));
                job.setStartTimeInMilliSec(c.getLong(8));
                job.setEndTimeInMilliSec(c.getLong(9));
                job.setReasonForLeaving(c.getString(10));
                job.setReferrals(getReferrals(db, job.getCompanyId()));
                job.setResponsibilities(getResponsibilities(db, job.getCompanyId()));
                jobs.add(job);
            } while(c.moveToNext());
        }
        db.close();
        c.close();
        return jobs;
    }

    private String createJobTable() {
        return "CREATE TABLE " + TABLE_JOBS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_COMPANY_ID + " INTEGER,"
                + KEY_COMPANY_NAME + " TEXT,"
                + KEY_COMPANY_PROFILE + " TEXT,"
                + KEY_CITY + " TEXT,"
                + KEY_COUNTRY + " TEXT,"
                + KEY_DEPARTMENT + " TEXT,"
                + KEY_JOB_TITLE + " TEXT,"
                + KEY_START + " INTEGER,"
                + KEY_END + " INTEGER,"
                + KEY_REASON + " TEXT"
                + ")";
    }

    private String createResponsibilitiesTable() {
        return "CREATE TABLE " + TABLE_RESPONSIBILTIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_COMPANY_ID + " INTEGER,"
                + KEY_SUMMARY + " TEXT,"
                + KEY_DESCRIPTION + " TEXT"
                + ")";
    }

    private String createReferralTable() {
        return "CREATE TABLE " + TABLE_REFERRAL + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_COMPANY_ID + " INTEGER,"
                + KEY_REFERRAL_NAME + " TEXT,"
                + KEY_REFERRAL_TITLE + " TEXT,"
                + KEY_RELATION + " TEXT,"
                + KEY_REFERRAL_PHONE + " TEXT,"
                + KEY_REFERRAL_EMAIL + " TEXT"
                + ")";
    }
}
