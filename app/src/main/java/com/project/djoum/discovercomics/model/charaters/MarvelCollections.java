package com.project.djoum.discovercomics.model.charaters;

import android.os.Parcel;
import android.os.Parcelable;

import com.project.djoum.discovercomics.model.comics.Item;

import java.util.List;

public class MarvelCollections implements Parcelable {
    
    public final static Parcelable.Creator<MarvelCollections> CREATOR = new Creator<MarvelCollections>() {
        
        @SuppressWarnings({
                "unchecked"
        })
        public MarvelCollections createFromParcel(Parcel in) {
            return new MarvelCollections(in);
        }
        
        public MarvelCollections[] newArray(int size) {
            return (new MarvelCollections[size]);
        }
        
    };
    private long available;
    private String collectionURI;
    private List<Item> items = null;
    private long returned;
    
    protected MarvelCollections(Parcel in) {
        this.available = ((long) in.readValue((long.class.getClassLoader())));
        this.collectionURI = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.items, (com.project.djoum.discovercomics.model.comics.Item.class.getClassLoader()));
        this.returned = ((long) in.readValue((long.class.getClassLoader())));
    }
    
    /**
     * No args constructor for use in serialization
     */
    public MarvelCollections() {
    }
    
    /**
     * @param items
     * @param collectionURI
     * @param available
     * @param returned
     */
    public MarvelCollections(long available, String collectionURI, List<Item> items, long returned) {
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
    
    public List<Item> getItems() {
        return items;
    }
    
    public void setItems(List<Item> items) {
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
