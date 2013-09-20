package com.micronixsolutions.livemusicarchive;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.micronixsolutions.api.music.Music;
import com.micronixsolutions.api.music.model.MessagesArtistResponse;
import com.micronixsolutions.api.music.model.MessagesArtistsResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by juan on 9/18/13.
 */
public class ArtistsGridFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<MessagesArtistResponse>>{

    ArtistGridAdapter mAdapter;
    GridView mGridView;
    ProgressBar mProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
 @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new ArtistGridAdapter(getActivity());
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView  = inflater.inflate(R.layout.artists_grid, container, false);
        mGridView = (GridView) rootView.findViewById(R.id.artists_grid_view);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.artists_grid_progress_bar);
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }





    // Loader Stuff
    @Override
    public Loader<List<MessagesArtistResponse>> onCreateLoader(int i, Bundle bundle) {
        return new QueryArtistsLoader(getActivity());
    }


    @Override
    public void onLoadFinished(Loader<List<MessagesArtistResponse>> loader, List<MessagesArtistResponse> data) {
        mAdapter.setData(data);
        //mAdapter.notifyDataSetChanged();
        mGridView.setAdapter(mAdapter);
        //mGridView.invalidateViews();
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<List<MessagesArtistResponse>> loader) {
        mAdapter.setData(null);
    }






    public static class QueryArtistsLoader extends AsyncTaskLoader<List<MessagesArtistResponse>>{
        final Context mContext;
        List<MessagesArtistResponse> mArtists;

        public QueryArtistsLoader(Context context) {
            super(context);
            mContext = context;
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            if(mArtists != null){
                deliverResult(mArtists);
            }
            if(mArtists == null){
                forceLoad();
            }
        }

        @Override
        public void stopLoading() {
            super.stopLoading();
            cancelLoad();
        }

        @Override
        public void onCanceled(List<MessagesArtistResponse> data) {
            super.onCanceled(data);
        }

        @Override
        public List<MessagesArtistResponse> loadInBackground() {
            Music.Builder builder = new Music.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
            Music mApi = builder.build();
            MessagesArtistsResponse artists = null;
            try{
                artists = mApi.music().artists().execute();
            } catch (IOException e){
                Log.d("ARTISTGRID", e.getMessage(), e);
            }
            return artists.getArtists();
        }

        @Override
        public void deliverResult(List<MessagesArtistResponse> data) {
            super.deliverResult(data);
        }
    }
}
