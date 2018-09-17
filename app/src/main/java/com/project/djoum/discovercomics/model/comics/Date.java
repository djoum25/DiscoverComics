package com.project.djoum.discovercomics.model.comics;

import android.os.Parcel;
import android.os.Parcelable;

public class Date implements Parcelable {
    
    public final static Parcelable.Creator<Date> CREATOR = new Creator<Date>() {
        @SuppressWarnings({
                "unchecked"
        })
        public Date createFromParcel(Parcel in) {
            return new Date(in);
        }
        
        public Date[] newArray(int size) {
            return (new Date[size]);
        }
        
    };
    private String type;
    private String date;
    
    protected Date(Parcel in) {
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
    }
    
    /**
     * No args constructor for use in serialization
     */
    public Date() {
    }
    
    /**
     * @param date
     * @param type
     */
    public Date(String type, String date) {
        super();
        this.type = type;
        this.date = date;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(date);
    }
    
    public int describeContents() {
        return 0;
    }
    
}
