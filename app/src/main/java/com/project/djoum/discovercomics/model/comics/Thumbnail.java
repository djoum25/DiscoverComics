package com.project.djoum.discovercomics.model.comics;

import android.os.Parcel;
import android.os.Parcelable;

public class Thumbnail implements Parcelable {
    
    public final static Parcelable.Creator<Thumbnail> CREATOR = new Creator<Thumbnail>() {
        @SuppressWarnings({
                "unchecked"
        })
        public Thumbnail createFromParcel(Parcel in) {
            return new Thumbnail(in);
        }
        
        public Thumbnail[] newArray(int size) {
            return (new Thumbnail[size]);
        }
    };
    private String path;
    private String extension;
    
    protected Thumbnail(Parcel in) {
        this.path = ((String) in.readValue((String.class.getClassLoader())));
        this.extension = ((String) in.readValue((String.class.getClassLoader())));
    }
    
    /**
     * No args constructor for use in serialization
     */
    public Thumbnail() {
    }
    
    /**
     * @param extension
     * @param path
     */
    public Thumbnail(String path, String extension) {
        super();
        this.path = path;
        this.extension = extension;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public String getExtension() {
        return extension;
    }
    
    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(path);
        dest.writeValue(extension);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public String toString() {
        return path.concat(".").concat(extension);
    }
}
