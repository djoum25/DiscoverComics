package com.project.djoum.discovercomics.model.comics;

import android.os.Parcel;
import android.os.Parcelable;

public class Url implements Parcelable {
    
    public final static Parcelable.Creator<Url> CREATOR = new Creator<Url>() {
        @SuppressWarnings({
                "unchecked"
        })
        public Url createFromParcel(Parcel in) {
            return new Url(in);
        }
        
        public Url[] newArray(int size) {
            return (new Url[size]);
        }
    };
    private String type;
    private String url;
    
    protected Url(Parcel in) {
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
    }
    
    /**
     * No args constructor for use in serialization
     */
    public Url() {
    }
    
    /**
     * @param type
     * @param url
     */
    public Url(String type, String url) {
        super();
        this.type = type;
        this.url = url;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(url);
    }
    
    public int describeContents() {
        return 0;
    }
    
}
