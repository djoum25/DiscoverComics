package com.project.djoum.discovercomics.model.comics;

import android.os.Parcel;
import android.os.Parcelable;

public class TextObject implements Parcelable {
    
    public final static Parcelable.Creator<TextObject> CREATOR = new Creator<TextObject>() {
        @SuppressWarnings({
                "unchecked"
        })
        public TextObject createFromParcel(Parcel in) {
            return new TextObject(in);
        }
        
        public TextObject[] newArray(int size) {
            return (new TextObject[size]);
        }
    };
    private String type;
    private String language;
    private String text;
    
    protected TextObject(Parcel in) {
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.language = ((String) in.readValue((String.class.getClassLoader())));
        this.text = ((String) in.readValue((String.class.getClassLoader())));
    }
    
    /**
     * No args constructor for use in serialization
     */
    public TextObject() {
    }
    
    /**
     * @param text
     * @param language
     * @param type
     */
    public TextObject(String type, String language, String text) {
        super();
        this.type = type;
        this.language = language;
        this.text = text;
    }
    
    public TextObject(String text) {
        this.text = text;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(language);
        dest.writeValue(text);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public String toString() {
        return text;
    }
}
