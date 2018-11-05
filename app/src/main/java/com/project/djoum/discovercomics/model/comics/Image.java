package com.project.djoum.discovercomics.model.comics;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.net.MalformedURLException;
import java.net.URL;

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
    
    /**
     * @return the standard image
     */
    @Override
    public String toString() {
        return path.concat(".").concat(extension);
    }
    
    /**
     * @param aspectRationOfImage of the image
     * @return the complete string address for the image
     * to display with the chosen aspect ration
     */
    public URL imageToDisplay(String aspectRationOfImage) throws MalformedURLException {
        Uri uri = Uri.parse(path)
                          .buildUpon()
                          .appendEncodedPath(aspectRationOfImage)
                          .build();
        return new URL(uri.toString().concat(".").concat(extension));
    }
}

//http://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73/portrait_xlarge.jpg
