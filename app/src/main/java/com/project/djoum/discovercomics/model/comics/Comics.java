package com.project.djoum.discovercomics.model.comics;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
    private static final String TAG = "test";
    private long id;
    private long digitalId;
    private String title;
    private long issueNumber;
    private String variantDescription;
    private String description;
    private String modified;
    private String isbn;
    private String upc;
    private String diamondCode;
    private String ean;
    private String issn;
    private String format;
    private long pageCount;
    private List<TextObject> textObjects = null;
    private String resourceURI;
    private List<Url> urls = null;
    private Series series;
    private List<Date> dates = null;
    private List<Price> prices = null;
    private Thumbnail thumbnail;
    private List<Image> images = null;
    private Creators creators;
    private Characters characters;
    private Stories stories;
    private Events events;
    
    protected Comics(Parcel in) {
        this.id = ((long) in.readValue((long.class.getClassLoader())));
        this.digitalId = ((long) in.readValue((long.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.issueNumber = ((long) in.readValue((long.class.getClassLoader())));
        this.variantDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.modified = ((String) in.readValue((String.class.getClassLoader())));
        this.isbn = ((String) in.readValue((String.class.getClassLoader())));
        this.upc = ((String) in.readValue((String.class.getClassLoader())));
        this.diamondCode = ((String) in.readValue((String.class.getClassLoader())));
        this.ean = ((String) in.readValue((String.class.getClassLoader())));
        this.issn = ((String) in.readValue((String.class.getClassLoader())));
        this.format = ((String) in.readValue((String.class.getClassLoader())));
        this.pageCount = ((long) in.readValue((long.class.getClassLoader())));
        in.readList(this.textObjects, (com.project.djoum.discovercomics.model.comics.TextObject.class.getClassLoader()));
        this.resourceURI = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.urls, (com.project.djoum.discovercomics.model.comics.Url.class.getClassLoader()));
        this.series = ((Series) in.readValue((Series.class.getClassLoader())));
        in.readList(this.dates, (com.project.djoum.discovercomics.model.comics.Date.class.getClassLoader()));
        in.readList(this.prices, (com.project.djoum.discovercomics.model.comics.Price.class.getClassLoader()));
        this.thumbnail = ((Thumbnail) in.readValue((Thumbnail.class.getClassLoader())));
        in.readList(this.images, (com.project.djoum.discovercomics.model.comics.Image.class.getClassLoader()));
        this.creators = ((Creators) in.readValue((Creators.class.getClassLoader())));
        this.characters = ((Characters) in.readValue((Characters.class.getClassLoader())));
        this.stories = ((Stories) in.readValue((Stories.class.getClassLoader())));
        this.events = ((Events) in.readValue((Events.class.getClassLoader())));
    }
    
    /**
     * No args constructor for use in serialization
     */
    public Comics() {
    }
    
    /**
     * @param series
     * @param issn
     * @param events
     * @param id
     * @param title
     * @param dates
     * @param description
     * @param isbn
     * @param variants
     * @param digitalId
     * @param collections
     * @param pageCount
     * @param textObjects
     * @param urls
     * @param format
     * @param upc
     * @param modified
     * @param variantDescription
     * @param creators
     * @param ean
     * @param issueNumber
     * @param stories
     * @param thumbnail
     * @param resourceURI
     * @param images
     * @param collectedIssues
     * @param prices
     * @param characters
     * @param diamondCode
     */
    public Comics(long id, long digitalId, String title, long issueNumber,
                  String variantDescription, String description, String modified,
                  String isbn, String upc, String diamondCode, String ean, String issn,
                  String format, long pageCount, List<TextObject> textObjects, String resourceURI,
                  List<Url> urls, Series series, List<Object> variants, List<Object> collections,
                  List<Object> collectedIssues, List<Date> dates, List<Price> prices,
                  Thumbnail thumbnail, List<Image> images, Creators creators,
                  Characters characters, Stories stories, Events events) {
        super();
        this.id = id;
        this.digitalId = digitalId;
        this.title = title;
        this.issueNumber = issueNumber;
        this.variantDescription = variantDescription;
        this.description = description;
        this.modified = modified;
        this.isbn = isbn;
        this.upc = upc;
        this.diamondCode = diamondCode;
        this.ean = ean;
        this.issn = issn;
        this.format = format;
        this.pageCount = pageCount;
        this.textObjects = textObjects;
        this.resourceURI = resourceURI;
        this.urls = urls;
        this.series = series;
        this.dates = dates;
        this.prices = prices;
        this.thumbnail = thumbnail;
        this.images = images;
        this.creators = creators;
        this.characters = characters;
        this.stories = stories;
        this.events = events;
    }
    
    public Comics(long id, String title, String description, List<TextObject> myObject,
                  Thumbnail thumbnail, List<Image> images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.textObjects = myObject;
        this.thumbnail = thumbnail;
        this.images = images;
    }
    
    public static List<Comics> jsonToComics(String result) throws JSONException {
        List<Comics> comicsList = new ArrayList<>();
        JSONObject comicsOject = new JSONObject(result);
        String title = null;
        long id = 0;
        String description = null;
        JSONObject jsonObject = null;
        String path = null;
        String extension = null;
        List<Image> images = null;
        List<TextObject> textObjectList = null;
        
        if (comicsOject.has("data")) {
            JSONObject data = comicsOject.getJSONObject("data");
            if (data.has("results")) {
                JSONArray results = data.getJSONArray("results");
                for (int i = 0; i < results.length(); i++) {
                    jsonObject = results.getJSONObject(i);
                    if (jsonObject.has("id")) {
                        id = jsonObject.getLong("id");
                    }
                    if (jsonObject.has("title")) {
                        title = jsonObject.getString("title");
                    }
                    if (jsonObject.has("description")) {
                        description = jsonObject.getString("description");
                    }
                    if (jsonObject.has("thumbnail")) {
                        JSONObject thumbnail = jsonObject.getJSONObject("thumbnail");
                        if (thumbnail.has("path")) {
                            path = thumbnail.getString("path");
                        }
                        if (thumbnail.has("extension")) {
                            extension = thumbnail.getString("extension");
                        }
                    }
                    
                    if (jsonObject.has("textObjects")) {
                        JSONArray textObjectsArray = jsonObject.getJSONArray("textObjects");
                        textObjectList = getTextObject(textObjectsArray);
                    }
                    
                    if (jsonObject.has("images")) {
                        JSONArray imageArrays = jsonObject.getJSONArray("images");
                        images = getImageFromJson(imageArrays);
                    }
                    comicsList.add(new Comics(id, title, description, textObjectList,
                            new Thumbnail(path, extension), images));
                }
            }
        }
        return comicsList;
    }
    
    private static List<Image> getImageFromJson(JSONArray result) throws JSONException {
        List<Image> images = new ArrayList<>();
        for (int i = 0; i < result.length(); i++) {
            String path = null;
            String extension = null;
            JSONObject object = result.getJSONObject(i);
            if (object.has("path")) {
                path = object.getString("path");
            }
            if (object.has("extension")) {
                extension = object.getString("extension");
            }
            images.add(new Image(path, extension));
        }
        return images;
    }
    
    private static List<TextObject> getTextObject(JSONArray result) throws JSONException {
        List<TextObject> objects = new ArrayList<>();
        for (int i = 0; i < result.length(); i++) {
            String text = null;
            JSONObject object = result.getJSONObject(i);
            if (object.has("text")) {
                text = object.getString("text");
            }
            objects.add(new TextObject(text));
        }
        return objects;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public long getDigitalId() {
        return digitalId;
    }
    
    public void setDigitalId(long digitalId) {
        this.digitalId = digitalId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public long getIssueNumber() {
        return issueNumber;
    }
    
    public void setIssueNumber(long issueNumber) {
        this.issueNumber = issueNumber;
    }
    
    public String getVariantDescription() {
        return variantDescription;
    }
    
    public void setVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
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
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getUpc() {
        return upc;
    }
    
    public void setUpc(String upc) {
        this.upc = upc;
    }
    
    public String getDiamondCode() {
        return diamondCode;
    }
    
    public void setDiamondCode(String diamondCode) {
        this.diamondCode = diamondCode;
    }
    
    public String getEan() {
        return ean;
    }
    
    public void setEan(String ean) {
        this.ean = ean;
    }
    
    public String getIssn() {
        return issn;
    }
    
    public void setIssn(String issn) {
        this.issn = issn;
    }
    
    public String getFormat() {
        return format;
    }
    
    public void setFormat(String format) {
        this.format = format;
    }
    
    public long getPageCount() {
        return pageCount;
    }
    
    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }
    
    public List<TextObject> getTextObjects() {
        return textObjects;
    }
    
    public void setTextObjects(List<TextObject> textObjects) {
        this.textObjects = textObjects;
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
    
    public Series getSeries() {
        return series;
    }
    
    public void setSeries(Series series) {
        this.series = series;
    }
    
    public List<Date> getDates() {
        return dates;
    }
    
    public void setDates(List<Date> dates) {
        this.dates = dates;
    }
    
    public List<Price> getPrices() {
        return prices;
    }
    
    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
    
    public Thumbnail getThumbnail() {
        return thumbnail;
    }
    
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
    
    public List<Image> getImages() {
        return images;
    }
    
    public void setImages(List<Image> images) {
        this.images = images;
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
    
    public Events getEvents() {
        return events;
    }
    
    public void setEvents(Events events) {
        this.events = events;
    }
    
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(digitalId);
        dest.writeValue(title);
        dest.writeValue(issueNumber);
        dest.writeValue(variantDescription);
        dest.writeValue(description);
        dest.writeValue(modified);
        dest.writeValue(isbn);
        dest.writeValue(upc);
        dest.writeValue(diamondCode);
        dest.writeValue(ean);
        dest.writeValue(issn);
        dest.writeValue(format);
        dest.writeValue(pageCount);
        dest.writeList(textObjects);
        dest.writeValue(resourceURI);
        dest.writeList(urls);
        dest.writeValue(series);
        dest.writeList(dates);
        dest.writeList(prices);
        dest.writeValue(thumbnail);
        dest.writeList(images);
        dest.writeValue(creators);
        dest.writeValue(characters);
        dest.writeValue(stories);
        dest.writeValue(events);
    }
    
    public int describeContents() {
        return 0;
    }
}

