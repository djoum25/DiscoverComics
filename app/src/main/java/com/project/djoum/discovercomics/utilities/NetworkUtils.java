package com.project.djoum.discovercomics.utilities;

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
    private static final String formatType = "formatType";
    private static final String PARAM_NO_VARIANT = "noVariants";
    private static final String PARAM_START_YEAR = "startYear";
    private static final String HASDIGITALISSUE = "hasDigitalIssue";
    private static final String PARAM_ORDER_BY = "orderBy";
    private static final String PARMAM_API_KEY = "apikey";
    private static final String TIMESTAMP = "ts";
    private static final String HASH = "hash";
    private static final String TAG = "test";
    
    
    private static String createHashKey(String publicKey, String privateKey, String timeStamp)
            throws NoSuchAlgorithmException {
        String algorithm = timeStamp.concat(privateKey).concat(publicKey);
        return new String(Hex.encodeHex(DigestUtils.md5(algorithm)));
    }
    
    public static URL comicsUrl(int startYear,
                                String orderBy, String publicKey, String privateKey)
            throws NoSuchAlgorithmException, MalformedURLException {
        String timeStamp = Long.toString(System.currentTimeMillis());
        Uri builtUri = Uri.parse(MARVEL_BASE_URL).buildUpon()
                               .appendPath(COMICS)
                               .appendQueryParameter(formatType, COMIC)
                               .appendQueryParameter(PARAM_NO_VARIANT, Boolean.toString(true))
                               .appendQueryParameter(PARAM_START_YEAR, Integer.toString(startYear))
                               .appendQueryParameter(HASDIGITALISSUE, Boolean.toString(true))
                               .appendQueryParameter(PARAM_ORDER_BY, orderBy)
                               .appendQueryParameter(TIMESTAMP, timeStamp)
                               .appendQueryParameter(PARMAM_API_KEY, publicKey)
                               .appendQueryParameter(HASH, createHashKey(publicKey, privateKey, timeStamp))
                               .build();
        return new URL(builtUri.toString());
    }
    
    public static Call queryUrl(URL myUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(myUrl).build();
        String myResponse = null;
        return client.newCall(request);
    }
    
    
}
