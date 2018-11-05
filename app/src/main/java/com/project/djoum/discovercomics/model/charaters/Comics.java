package com.project.djoum.discovercomics.model.charaters;

import android.os.Parcel;
import android.os.Parcelable;

import com.project.djoum.discovercomics.model.comics.Events;
import com.project.djoum.discovercomics.model.comics.Stories;
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
    private String name;
    private String description;
    private String modified;
    private Thumbnail thumbnail;
    private String resourceURI;
    private MarvelCollections mCollections;
    private MarvelCollections series;
    private Stories stories;
    private Events events;
    private List<Url> urls = null;
    
    protected Comics(Parcel in) {
        this.id = ((long) in.readValue((long.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.modified = ((String) in.readValue((String.class.getClassLoader())));
        this.thumbnail = ((Thumbnail) in.readValue((Thumbnail.class.getClassLoader())));
        this.resourceURI = ((String) in.readValue((String.class.getClassLoader())));
        this.mCollections = ((MarvelCollections) in.readValue((MarvelCollections.class.getClassLoader())));
        this.series = ((MarvelCollections) in.readValue((MarvelCollections.class.getClassLoader())));
        this.stories = ((Stories) in.readValue((Stories.class.getClassLoader())));
        this.events = ((Events) in.readValue((Events.class.getClassLoader())));
        in.readList(this.urls, (com.project.djoum.discovercomics.model.comics.Url.class.getClassLoader()));
    }
    
    /**
     * No args constructor for use in serialization
     */
    public Comics() {
    }
    
    /**
     * @param id
     * @param series
     * @param stories
     * @param thumbnail
     * @param resourceURI
     * @param urls
     * @param events
     * @param description
     * @param name
     * @param collections
     * @param modified
     */
    public Comics(long id, String name, String description, String modified,
                  Thumbnail thumbnail, String resourceURI, MarvelCollections collections,
                  MarvelCollections series, Stories stories, Events events, List<Url> urls) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.modified = modified;
        this.thumbnail = thumbnail;
        this.resourceURI = resourceURI;
        this.mCollections = collections;
        this.series = series;
        this.stories = stories;
        this.events = events;
        this.urls = urls;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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
    
    public String getResourceURI() {
        return resourceURI;
    }
    
    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }
    
    public MarvelCollections getComics() {
        return mCollections;
    }
    
    public void setComics(MarvelCollections comics) {
        this.mCollections = comics;
    }
    
    public MarvelCollections getSeries() {
        return series;
    }
    
    public void setSeries(MarvelCollections series) {
        this.series = series;
    }
    
    public Stories getStories() {
        return stories;
    }
    
    public void setStories(Stories stories) {
        this.stories = stories;
    }
    
    public Events getEvents() {
        return events;
    }
    
    public void setEvents(Events events) {
        this.events = events;
    }
    
    public List<Url> getUrls() {
        return urls;
    }
    
    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }
    
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(description);
        dest.writeValue(modified);
        dest.writeValue(thumbnail);
        dest.writeValue(resourceURI);
        dest.writeValue(mCollections);
        dest.writeValue(series);
        dest.writeValue(stories);
        dest.writeValue(events);
        dest.writeList(urls);
    }
    
    public int describeContents() {
        return 0;
    }
}
