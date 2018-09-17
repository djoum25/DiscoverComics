package com.project.djoum.discovercomics.model.comics;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Events implements Parcelable {
    
    public final static Parcelable.Creator<Events> CREATOR = new Creator<Events>() {
        @SuppressWarnings({
                "unchecked"
        })
        public Events createFromParcel(Parcel in) {
            return new Events(in);
        }
        
        public Events[] newArray(int size) {
            return (new Events[size]);
        }
        
    };
    private long available;
    private String collectionURI;
    private List<Object> items = null;
    private long returned;
    
    protected Events(Parcel in) {
        this.available = ((long) in.readValue((long.class.getClassLoader())));
        this.collectionURI = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.items, (java.lang.Object.class.getClassLoader()));
        this.returned = ((long) in.readValue((long.class.getClassLoader())));
    }
    
    /**
     * No args constructor for use in serialization
     */
    public Events() {
    }
    
    /**
     * @param items
     * @param collectionURI
     * @param available
     * @param returned
     */
    public Events(long available, String collectionURI, List<Object> items, long returned) {
        super();
        this.available = available;
        this.collectionURI = collectionURI;
        this.items = items;
        this.returned = returned;
    }
    
    public long getAvailable() {
        return available;
    }
    
    public void setAvailable(long available) {
        this.available = available;
    }
    
    public String getCollectionURI() {
        return collectionURI;
    }
    
    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }
    
    public List<Object> getItems() {
        return items;
    }
    
    public void setItems(List<Object> items) {
        this.items = items;
    }
    
    public long getReturned() {
        return returned;
    }
    
    public void setReturned(long returned) {
        this.returned = returned;
    }
    
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(available);
        dest.writeValue(collectionURI);
        dest.writeList(items);
        dest.writeValue(returned);
    }
    
    public int describeContents() {
        return 0;
    }
    
}
