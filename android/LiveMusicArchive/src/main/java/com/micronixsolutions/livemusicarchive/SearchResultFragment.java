package com.micronixsolutions.livemusicarchive;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.micronixsolutions.api.music.Music;
import com.micronixsolutions.api.music.model.MessagesArtistResponse;
import com.micronixsolutions.api.music.model.MessagesSearchResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by juan on 9/21/13.
 */
public class SearchResultFragment extends Fragment implements LoaderManager.LoaderCallbacks<MessagesSearchResponse> {

    private ProgressBar mProgressBar;
    private View mRootView;
    private ViewGroup mRootContainer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootContainer = container;
        mRootView = inflater.inflate(R.layout.search_result_block, container, false);
        mProgressBar = (ProgressBar) mRootView.findViewById(R.id.search_progress_bar);
        return mRootView;
    }

    @Override
    public Loader<MessagesSearchResponse> onCreateLoader(int i, Bundle bundle) {
        String query = bundle.getString("query", "");
        return new SearchLoader(getActivity(), query);
    }

    @Override
    public void onLoadFinished(Loader<MessagesSearchResponse> messagesSearchResponseLoader, MessagesSearchResponse messagesSearchResponse) {
        updateSearchResults(messagesSearchResponse);
        mProgressBar.setVisibility(View.GONE);
        getLoaderManager().destroyLoader(0);  // Kill the loader, so we get a new instance next time
    }

    @Override
    public void onLoaderReset(Loader<MessagesSearchResponse> messagesSearchResponseLoader) {
 }



    public void updateSearchResults(MessagesSearchResponse response){
        LinearLayout lm = (LinearLayout) mRootContainer.getChildAt(0);
        //FrameLayout lm = (FrameLayout) mRootContainer;
        View artistView = createResultItemView(response.getArtists(), "Artists", new ArtistGridAdapter(getActivity()));
        View showsView = createResultItemView(response.getShows(), "Shows", new ShowsGridAdapter(getActivity()));
        //View songsView = createResultItemView(response);
        lm.removeAllViews();
        if(artistView != null){
            lm.addView(artistView);
        }
        if(showsView != null){
            lm.addView(showsView);
        }
    }

    public View createResultItemView(List data, String title, ArrayAdapter adapter){
        if(data == null){
            return null;
        }
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.search_result_item, null);
        TextView text = (TextView) view.findViewById(R.id.title);
        text.setText(title);
        TextView more = (TextView) view.findViewById(R.id.more);
        more.setText(data.size() + " more");
        GridView grid = (GridView) view.findViewById(R.id.grid_view);
        //ArtistGridAdapter adapter = new ArtistGridAdapter(getActivity());
        int numResults = getResources().getInteger(R.integer.search_result_num_results);
        numResults = (data.size()<=numResults)?data.size():numResults;
        /*
        if(data.size() <= numResults){
            adapter.addAll(data.subList(0, data.size()));
        }
        else{
            int num = Math.min(numResults, data.size());
            adapter.addAll(data.subList(0, getResources().getInteger(R.dik)));
        }
        */
        adapter.addAll(data.subList(0, numResults));
        grid.setAdapter(adapter);
        return view;
     }

    public void doSearch(String query){
        mProgressBar.setVisibility(View.VISIBLE);
        LoaderManager lm = getLoaderManager();
        Bundle bundle = new Bundle();
        bundle.putString("query", query);
        lm.destroyLoader(0);
        lm.initLoader(0, bundle, this);
    }




    public static class SearchLoader extends AsyncTaskLoader<MessagesSearchResponse> {
        Music mApi;
        String mQuery;

        public SearchLoader(Context context, String query) {
            super(context);
            Music.Builder builder = new Music.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
            mApi = builder.build();
            mQuery = query;
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            /*
            if(mSearchResponse != null){
                deliverResult(mSearchResponse);
            }
            if(mSearchResponse == null){
                forceLoad();
            }
            */
            forceLoad();
        }

        @Override
        public void stopLoading() {
            super.stopLoading();
            cancelLoad();
        }

        @Override
        public void onCanceled(MessagesSearchResponse data) {
            super.onCanceled(data);
        }

        @Override
        public MessagesSearchResponse loadInBackground() {
            MessagesSearchResponse results = null;
            try{
                results = mApi.search().all(mQuery).execute();
                }
            catch (IOException e){
                Log.d("SEARCH", e.getMessage(), e);
            }
            return results;
        }

    }
}
