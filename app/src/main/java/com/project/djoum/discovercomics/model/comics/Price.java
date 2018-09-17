package com.project.djoum.discovercomics.model.comics;

import android.os.Parcel;
import android.os.Parcelable;

public class Price implements Parcelable {
    
    public final static Parcelable.Creator<Price> CREATOR = new Creator<Price>() {
        @SuppressWarnings({
                "unchecked"
        })
        public Price createFromParcel(Parcel in) {
            return new Price(in);
        }
        
        public Price[] newArray(int size) {
            return (new Price[size]);
        }
    };
    private String type;
    private double price;
    
    protected Price(Parcel in) {
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((double) in.readValue((double.class.getClassLoader())));
    }
    
    /**
     * No args constructor for use in serialization
     */
    public Price() {
    }
    
    /**
     * @param price
     * @param type
     */
    public Price(String type, double price) {
        super();
        this.type = type;
        this.price = price;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(price);
    }
    
    public int describeContents() {
        return 0;
    }
    
}
