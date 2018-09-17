package com.project.djoum.discovercomics.model.comics;

import android.os.Parcel;
import android.os.Parcelable;

public class Series implements Parcelable {
    public final static Parcelable.Creator<Series> CREATOR = new Creator<Series>() {
        @SuppressWarnings({
                "unchecked"
        })
        public Series createFromParcel(Parcel in) {
            return new Series(in);
        }
        
        public Series[] newArray(int size) {
            return (new Series[size]);
        }
    };
    private String resourceURI;
    private String name;
    
    protected Series(Parcel in) {
        this.resourceURI = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }
    
    /**
     * No args constructor for use in serialization
     */
    public Series() {
    }
    
    /**
     * @param resourceURI
     * @param name
     */
    public Series(String resourceURI, String name) {
        super();
        this.resourceURI = resourceURI;
        this.name = name;
    }
    
    public String getResourceURI() {
        return resourceURI;
    }
    
    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(resourceURI);
        dest.writeValue(name);
    }
    
    public int describeContents() {
        return 0;
    }
    
}
