package com.project.djoum.discovercomics.model.series;

import android.os.Parcel;
import android.os.Parcelable;

import com.project.djoum.discovercomics.model.charaters.Comics_;
import com.project.djoum.discovercomics.model.comics.Characters;
import com.project.djoum.discovercomics.model.comics.Creators;
import com.project.djoum.discovercomics.model.comics.Events;
import com.project.djoum.discovercomics.model.comics.Thumbnail;
import com.project.djoum.discovercomics.model.comics.Url;

import java.util.List;

public class Comics implements Parcelable {
    
    public final static Parcelable.Creator<Comics> CREATOR = new Creator<Comics>() {
        
        
        @SuppressWarnings({
                "unchecked"
        })
        public Comics createFromParcel(Parcel in) {
            return new Comics(in);
        }
        
        public Comics[] newArray(int size) {
            return (new Comics[size]);
        }
        
    };
    private long id;
    private String title;
    private Object description;
    private String resourceURI;
    private List<Url> urls = null;
    private long startYear;
    private long endYear;
    private String rating;
    private String type;
    private String modified;
    private Thumbnail thumbnail;
    private Creators creators;
    private Characters characters;
    private Stories stories;
    private Comics_ comics;
    private Events events;
    
    protected Comics(Parcel in) {
        this.id = ((long) in.readValue((long.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((Object) in.readValue((Object.class.getClassLoader())));
        this.resourceURI = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.urls, (com.project.djoum.discovercomics.model.comics.Url.class.getClassLoader()));
        this.startYear = ((long) in.readValue((long.class.getClassLoader())));
        this.endYear = ((long) in.readValue((long.class.getClassLoader())));
        this.rating = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.modified = ((String) in.readValue((String.class.getClassLoader())));
        this.thumbnail = ((Thumbnail) in.readValue((Thumbnail.class.getClassLoader())));
        this.creators = ((Creators) in.readValue((Creators.class.getClassLoader())));
        this.characters = ((Characters) in.readValue((Characters.class.getClassLoader())));
        this.stories = ((Stories) in.readValue((Stories.class.getClassLoader())));
        this.comics = ((Comics_) in.readValue((Comics_.class.getClassLoader())));
        this.events = ((Events) in.readValue((Events.class.getClassLoader())));
    }
    
    /**
     * No args constructor for use in serialization
     */
    public Comics() {
    }
    
    /**
     * @param endYear
     * @param previous
     * @param events
     * @param urls
     * @param next
     * @param type
     * @param modified
     * @param id
     * @param creators
     * @param title
     * @param stories
     * @param thumbnail
     * @param resourceURI
     * @param startYear
     * @param description
     * @param rating
     * @param comics
     * @param characters
     */
    public Comics(long id, String title, Object description, String resourceURI, List<Url> urls, long startYear, long endYear, String rating, String type, String modified, Thumbnail thumbnail, Creators creators, Characters characters, Stories stories, Comics_ comics, Events events, Object next, Object previous) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.resourceURI = resourceURI;
        this.urls = urls;
        this.startYear = startYear;
        this.endYear = endYear;
        this.rating = rating;
        this.type = type;
        this.modified = modified;
        this.thumbnail = thumbnail;
        this.creators = creators;
        this.characters = characters;
        this.stories = stories;
        this.comics = comics;
        this.events = events;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Object getDescription() {
        return description;
    }
    
    public void setDescription(Object description) {
        this.description = description;
    }
    
    public String getResourceURI() {
        return resourceURI;
    }
    
    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }
    
    public List<Url> getUrls() {
        return urls;
    }
    
    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }
    
    public long getStartYear() {
        return startYear;
    }
    
    public void setStartYear(long startYear) {
        this.startYear = startYear;
    }
    
    public long getEndYear() {
        return endYear;
    }
    
    public void setEndYear(long endYear) {
        this.endYear = endYear;
    }
    
    public String getRating() {
        return rating;
    }
    
    public void setRating(String rating) {
        this.rating = rating;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getModified() {
        return modified;
    }
    
    public void setModified(String modified) {
        this.modified = modified;
    }
    
    public Thumbnail getThumbnail() {
        return thumbnail;
    }
    
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
    
    public Creators getCreators() {
        return creators;
    }
    
    public void setCreators(Creators creators) {
        this.creators = creators;
    }
    
    public Characters getCharacters() {
        return characters;
    }
    
    public void setCharacters(Characters characters) {
        this.characters = characters;
    }
    
    public Stories getStories() {
        return stories;
    }
    
    public void setStories(Stories stories) {
        this.stories = stories;
    }
    
    public Comics_ getComics() {
        return comics;
    }
    
    public void setComics(Comics_ comics) {
        this.comics = comics;
    }
    
    public Events getEvents() {
        return events;
    }
    
    public void setEvents(Events events) {
        this.events = events;
    }
    
    
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(description);
        dest.writeValue(resourceURI);
        dest.writeList(urls);
        dest.writeValue(startYear);
        dest.writeValue(endYear);
        dest.writeValue(rating);
        dest.writeValue(type);
        dest.writeValue(modified);
        dest.writeValue(thumbnail);
        dest.writeValue(creators);
        dest.writeValue(characters);
        dest.writeValue(stories);
        dest.writeValue(comics);
        dest.writeValue(events);
    }
    
    public int describeContents() {
        return 0;
    }
    
}
