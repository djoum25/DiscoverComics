package com.project.djoum.discovercomics.utilities;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public final class NetworkUtils {
    private static final String MARVEL_BASE_URL = "https://gateway.marvel.com:443/v1/public/";
    private static final String COMICS = "comics";
    private static final String COMIC = "comic";
    private static final String CHARACTERS = "characters";
    private static final String STORIES = "stories";
    private static final String SERIES = "series";
    
    public static final String CONTAINS_VALUE = "contains";
    private static final String formatType = "formatType";
    private static final String PARAM_NO_VARIANT = "noVariants";
    private static final String PARAM_START_YEAR = "startYear";
    private static final String HASDIGITALISSUE = "hasDigitalIssue";
    private static final String PARAM_ORDER_BY = "orderBy";
    public static final String DIGITAL = "digital comic";
    private static final String TIMESTAMP = "ts";
    private static final String HASH = "hash";
    private static final String TAG = "test";
    private static final String SERIES_TYPES = "seriesType";
    private static final String PARAM_API_KEY = "apikey";
    
    /**
     * method to create the hash key to query
     * the json api
     *
     * @param publicKey
     * @param privateKey
     * @param timeStamp
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String createHashKey(String publicKey, String privateKey, String timeStamp)
            throws NoSuchAlgorithmException {
        String algorithm = timeStamp.concat(privateKey).concat(publicKey);
        return new String(Hex.encodeHex(DigestUtils.md5(algorithm)));
    }
    
    /**
     * fetches list of comics
     * @param startYear
     * @param orderBy
     * @param publicKey
     * @param privateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws MalformedURLException
     */
    public static URL comicsUrl(int startYear,
                                String orderBy, String publicKey, String privateKey)
            throws NoSuchAlgorithmException, MalformedURLException {
        if (orderBy == null) orderBy = "title";
        String timeStamp = getTimeStamp();
        Uri builtUri = Uri.parse(MARVEL_BASE_URL).buildUpon()
                               .appendPath(COMICS)
                               .appendQueryParameter(formatType, COMIC)
                               .appendQueryParameter(PARAM_NO_VARIANT, Boolean.toString(true))
                               .appendQueryParameter(PARAM_START_YEAR, Integer.toString(startYear))
                               .appendQueryParameter(HASDIGITALISSUE, Boolean.toString(true))
                               .appendQueryParameter(PARAM_ORDER_BY, orderBy)
                               .appendQueryParameter(TIMESTAMP, timeStamp)
                               .appendQueryParameter(PARAM_API_KEY, publicKey)
                               .appendQueryParameter(HASH, createHashKey(publicKey, privateKey, timeStamp))
                               .build();
        return new URL(builtUri.toString());
    }
    
    //https://gateway.marvel.com:443/v1/public/comics?orderBy=title&apikey=006ac57c9d265881686e05e0c49b6e5b
    
    /**
     * fetches a single comic by id
     *
     * @param comicId
     * @param publicKey
     * @param privateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws MalformedURLException
     */
    public static URL comicUrl(int comicId, String publicKey, String privateKey)
            throws NoSuchAlgorithmException, MalformedURLException {
        String timeStamp = getTimeStamp();
        Uri builtUri = Uri.parse(MARVEL_BASE_URL).buildUpon()
                               .appendPath(COMICS)
                               .appendPath(String.valueOf(comicId))
                               .appendQueryParameter(TIMESTAMP, timeStamp)
                               .appendQueryParameter(PARAM_API_KEY, publicKey)
                               .appendQueryParameter(HASH, createHashKey(publicKey, privateKey, timeStamp))
                               .build();
        return new URL(builtUri.toString());
    }
    
    private static String getTimeStamp() {
        return Long.toString(System.currentTimeMillis());
    }
    
    /**
     * method to query any url
     * @param myUrl to parse
     * @return an OkHttp call which will implement
     * asynchronously in the calling method
     * @throws IOException
     */
    public static Call queryUrl(URL myUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(myUrl).build();
        String myResponse = null;
        return client.newCall(request);
    }
    
    /**
     * method to construct a url for the Ressource uri
     * in the comic class
     *
     * @param baseResourceUri
     * @param timeStamp
     * @param publicKey
     * @param privateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws MalformedURLException
     */
    public static URL comicsResourceUri(String baseResourceUri, long timeStamp, String publicKey,
                                        String privateKey)
            throws NoSuchAlgorithmException, MalformedURLException {
        String timeStampString = Long.toString(timeStamp);
        Uri builUri = Uri.parse(baseResourceUri).buildUpon()
                              .appendQueryParameter(TIMESTAMP, timeStampString)
                              .appendQueryParameter(PARAM_API_KEY, publicKey)
                              .appendQueryParameter(HASH, createHashKey(publicKey, privateKey, timeStampString))
                              .build();
        return new URL(builUri.toString());
    }
    
    /**
     * method to query list of series
     *
     * @param orderBy
     * @param publicKey
     * @param privateKey
     * @return a general url
     * @throws NoSuchAlgorithmException
     * @throws MalformedURLException
     */
    public static URL seriesUrl(String orderBy, String publicKey, String privateKey)
            throws NoSuchAlgorithmException, MalformedURLException {
        String timeStamp = getTimeStamp();
        Uri buildUri = Uri.parse(MARVEL_BASE_URL).buildUpon()
                               .appendPath(SERIES)
                               .appendQueryParameter(CONTAINS_VALUE, DIGITAL)
                               .appendQueryParameter(PARAM_ORDER_BY, orderBy)
                               .appendQueryParameter(TIMESTAMP, timeStamp)
                               .appendQueryParameter(PARAM_API_KEY, publicKey)
                               .appendQueryParameter(HASH, createHashKey(publicKey, privateKey, timeStamp))
                               .build();
        return new URL(buildUri.toString());
    }
    
    /**
     * fetches a single serie by the serie id
     *
     * @param serieId
     * @param publicKey
     * @param privateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws MalformedURLException
     */
    public static URL seriesUrl(int serieId, String publicKey, String privateKey)
            throws NoSuchAlgorithmException, MalformedURLException {
        String timeStamp = getTimeStamp();
        Uri buildUri = Uri.parse(MARVEL_BASE_URL).buildUpon()
                               .appendPath(SERIES)
                               .appendPath(String.valueOf(serieId))
                               .appendQueryParameter(TIMESTAMP, timeStamp)
                               .appendQueryParameter(PARAM_API_KEY, publicKey)
                               .appendQueryParameter(HASH, createHashKey(publicKey, privateKey, timeStamp))
                               .build();
        return new URL(buildUri.toString());
    }
    
    /**
     * fetches a list of characters
     *
     * @param orderBy    (name)
     * @param publicKey
     * @param privateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws MalformedURLException
     */
    public static URL charactersUrl(String orderBy, String publicKey, String privateKey)
            throws NoSuchAlgorithmException, MalformedURLException {
        String timeStamp = getTimeStamp();
        Uri builtUri = Uri.parse(MARVEL_BASE_URL).buildUpon()
                               .appendPath(CHARACTERS)
                               .appendQueryParameter(PARAM_ORDER_BY, orderBy)
                               .appendQueryParameter(TIMESTAMP, timeStamp)
                               .appendQueryParameter(PARAM_API_KEY, publicKey)
                               .appendQueryParameter(HASH, createHashKey(publicKey, privateKey, timeStamp))
                               .build();
        return new URL(builtUri.toString());
    }
    
    public static boolean isConnectionAvaillable(Activity activity) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnectedOrConnecting();
        }
        return false;
    }

//    https://gateway.marvel.com:443/v1/public/characters?series=4532&orderBy=name&apikey=006ac57c9d265881686e05e0c49b6e5b
    
    private String getCurrentYear() {
        return null;
    }
}

