package com.project.djoum.discovercomics.model.comics;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    
    public final static Parcelable.Creator<Item> CREATOR = new Creator<Item>() {
        @SuppressWarnings({
                "unchecked"
        })
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }
        
        public Item[] newArray(int size) {
            return (new Item[size]);
        }
    };
    private String resourceURI;
    private String name;
    private String role;
    private String type;
    
    protected Item(Parcel in) {
        this.resourceURI = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.role = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
    }
    
    /**
     * No args constructor for use in serialization
     */
    public Item() {
    }
    
    /**
     * @param resourceURI
     * @param name
     * @param role
     */
    public Item(String resourceURI, String name, String role) {
        super();
        this.resourceURI = resourceURI;
        this.name = name;
        this.role = role;
        this.type = type;
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
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(resourceURI);
        dest.writeValue(name);
        dest.writeValue(role);
        dest.writeValue(type);
    }
    
    public int describeContents() {
        return 0;
    }
    
}
