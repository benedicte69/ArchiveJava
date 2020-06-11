package com.example.bened.androidnews;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

class NewsLoader extends AsyncTaskLoader<List<News>> {

    /**
     * Query URL
     */
    private final String mUrl;

    public NewsLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    /**
     * Subclasses must implement this to take care of loading their data,
     * as per {@link #startLoading()}.  This is not called by clients directly,
     * but as a result of a call to {@link #startLoading()}.
     * This will always be called from the process's main thread.
     */
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<News> loadInBackground() {

        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of news.
        return QueryUtils.fetchNewsData(mUrl);
    }
}
