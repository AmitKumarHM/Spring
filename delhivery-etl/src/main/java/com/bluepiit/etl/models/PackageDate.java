package com.bluepiit.etl.models;

import java.io.Serializable;

import org.joda.time.DateTime;

/**
 * Created by jyoti on 1/2/16.
 * A class represting each of the dates corresponding to a package looked up from the database.
 */
public class PackageDate implements Serializable {

    private String waybill;

    private String dateCode;

    private DateTime dateValue;

    private DateTime lastUpdatedTime;


    public PackageDate() {
    }

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

    public String getDateCode() {
        return dateCode;
    }

    public void setDateCode(String dateCode) {
        this.dateCode = dateCode;
    }

    public DateTime getDateValue() {
        return dateValue;
    }

    public void setDateValue(DateTime dateValue) {
        this.dateValue = dateValue;
    }

    public DateTime getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(DateTime lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
