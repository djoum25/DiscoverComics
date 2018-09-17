package com.project.djoum.discovercomics.model.comics;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {
    
    public final static Parcelable.Creator<Image> CREATOR = new Creator<Image>() {
        @SuppressWarnings({
                "unchecked"
        })
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }
        
        public Image[] newArray(int size) {
            return (new Image[size]);
        }
    };
    private String path;
    private String extension;
    
    protected Image(Parcel in) {
        this.path = ((String) in.readValue((String.class.getClassLoader())));
        this.extension = ((String) in.readValue((String.class.getClassLoader())));
    }
    
    /**
     * No args constructor for use in serialization
     */
    public Image() {
    }
    
    /**
     * @param extension
     * @param path
     */
    public Image(String path, String extension) {
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
