package com.project.djoum.discovercomics.utilities;

import com.project.djoum.discovercomics.model.comics.Characters;
import com.project.djoum.discovercomics.model.comics.Comics;
import com.project.djoum.discovercomics.model.comics.Creators;
import com.project.djoum.discovercomics.model.comics.Image;
import com.project.djoum.discovercomics.model.comics.Item;
import com.project.djoum.discovercomics.model.comics.TextObject;
import com.project.djoum.discovercomics.model.comics.Thumbnail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    
    /**
     * this method will return a list of comics to display
     * for
     *
     * @param result json string to query
     * @return list of comics
     * @throws JSONException
     */
    public static List<Comics> jsonToComics(String result) throws JSONException {
        List<Comics> comicsList = new ArrayList<>();
        JSONObject comicsOject = new JSONObject(result);
        String title;
        long id;
        String description;
        JSONObject jsonObject;
        String path = null;
        String extension = null;
        String resourceUri;
        List<Image> images = null;
        List<TextObject> textObjectList = null;
        Characters characters = null;
        List<Item> items = null;
        List<Item> characterItems = null;
        
        if (comicsOject.has("data")) {
            JSONObject data = comicsOject.getJSONObject("data");
            if (data.has("results")) {
                JSONArray results = data.getJSONArray("results");
                for (int i = 0; i < results.length(); i++) {
                    jsonObject = results.getJSONObject(i);
                    
                    id = Long.parseLong(getJsonValue("id", jsonObject));
                    title = getJsonValue("title", jsonObject);
                    description = getJsonValue("description", jsonObject);
                    
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
                    resourceUri = getJsonValue("resourceURI", jsonObject);
                    if (jsonObject.has("images")) {
                        JSONArray imageArrays = jsonObject.getJSONArray("images");
                        images = getImageFromJson(imageArrays);
                    }
    
                    if (jsonObject.has("creators")) {
                        JSONObject creators = jsonObject.getJSONObject("creators");
                        if (creators.has("items")) {
                            JSONArray itemArray = creators.getJSONArray("items");
                            items = getItemsFromJson(itemArray);
                        }
                    }
    
                    if (jsonObject.has("characters")) {
                        JSONObject character = jsonObject.getJSONObject("characters");
                        if (character.has("items")) {
                            JSONArray itemArray = character.getJSONArray("items");
                            characterItems = getItemsFromJson(itemArray);
                        }
                    }
                    // TODO: 9/17/18 check the list whenever more object added
                    comicsList.add(new Comics(id, title, description, textObjectList, resourceUri,
                            new Thumbnail(path, extension), images, new Creators(items), new Characters(characterItems)));
                }
            }
        }
        return comicsList;
    }
    
    /**
     * method to return
     *
     * @param jsonValue
     * @param jsonObject
     * @return
     * @throws JSONException
     */
    private static String getJsonValue(String jsonValue, JSONObject jsonObject) throws JSONException {
        String value = null;
        if (jsonObject.has(jsonValue)) {
            value = jsonObject.getString(jsonValue);
        }
        return value;
    }
    
    /**
     * to fetch the image string from the json
     * and return them in a list
     *
     * @param result the json array
     * @return list of image value
     * @throws JSONException in case
     */
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
    
    /**
     * this method will return an array of creators
     *
     * @param result
     * @return
     */
    public static List<Item> getItemsFromJson(JSONArray result) throws JSONException {
        List<Item> thisItems = new ArrayList<>();
        Creators creators = null;
        for (int i = 0; i < result.length(); i++) {
            String name = null;
            String role = null;
            JSONObject object = result.getJSONObject(i);
            if (object.has("name")) {
                name = object.getString("name");
            }
            if (object.has("role")) {
                role = object.getString("role");
            }
            if (role != null) {
                thisItems.add(new Item(name, role));
            } else {
                thisItems.add(new Item(name));
            }
        }
        return thisItems;
    }
    
    /**
     * to get the text object from the comics api
     *
     * @param result
     * @return
     * @throws JSONException
     */
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
    
    
}
