package com.jjurisic.android.core;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.jjurisic.android.core.utils.Logger;
import com.jjurisic.android.core.utils.VolleyDateProdiver;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by jurisicJosip.
 */
public class GsonRequest<T> extends Request<T> {

    //Protocol data
    private static final String PROTOCOL_CHARSET = "UTF-8";
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    //Data
    private final Map<String, String> mHeaders = new HashMap<>();
    private final Gson mGson;
    private final Class<T> mClazz;
    private final Response.Listener<T> mListener;
    private final JsonObject mRequestBody;
    private Priority mPriority = Priority.NORMAL;

    public GsonRequest(String url, @NonNull Class<T> clazz, @Nullable JsonObject jsonRequest, @NonNull Response.Listener<T> listener, @NonNull Response.ErrorListener errorListener) {
        this(jsonRequest == null ? Method.GET : Method.POST, url, clazz, jsonRequest, listener, errorListener);
    }

    public GsonRequest(int method, String url, @NonNull Class<T> clazz, @Nullable JsonObject jsonRequest, @NonNull Response.Listener<T> listener, @NonNull Response.ErrorListener errorListener) {
        super(method, url, errorListener);

        setShouldCache(true);
        // Wait 20 seconds and don't retry more than once
        setRetryPolicy(new DefaultRetryPolicy(
                (int) TimeUnit.SECONDS.toMillis(20),
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mListener = listener;
        mRequestBody = jsonRequest;
        mClazz = clazz;

        this.mGson = VolleyDateProdiver.newGsonInstance().create();
    }

    public void setPriority(@NonNull Priority priority) {
        mPriority = priority;
    }

    @Override
    public Priority getPriority() {
        return mPriority;
    }

    public void appendHeaders(@NonNull Map<String, String> headers) {
        mHeaders.putAll(headers);
    }

    public void appendHeader(String key, String value) {
        mHeaders.put(key, value);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(mGson.fromJson(json, mClazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return !mHeaders.isEmpty() ? mHeaders : super.getHeaders();
    }

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        try {
            return mRequestBody != null ? mRequestBody.toString().getBytes(PROTOCOL_CHARSET) : null;
        } catch (UnsupportedEncodingException e) {
            Logger.doLogException(e);
        }
        return null;
    }
}