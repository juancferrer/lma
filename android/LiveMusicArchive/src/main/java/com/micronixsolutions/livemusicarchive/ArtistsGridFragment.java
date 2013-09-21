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
public class ArtistsGridFragment extends Fragment implements LoaderManager.LoaderCallbacks<MessagesArtistsResponse>, EndlessScrollCallback{

    ArtistGridAdapter mAdapter;
    GridView mGridView;
    ProgressBar mProgressBar;
    String mNextPage;

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
        mGridView.setOnScrollListener(new EndlessScrollListener(this, 100));
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.artists_grid_progress_bar);
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }





    // Loader Stuff
    @Override
    public Loader<MessagesArtistsResponse> onCreateLoader(int i, Bundle bundle) {
        String nextPage = null;
        if(bundle != null){
            nextPage = bundle.getString("nextPage", null);
        }
        return new QueryArtistsLoader(getActivity(), nextPage);
    }


    @Override
    public void onLoadFinished(Loader<MessagesArtistsResponse> loader, MessagesArtistsResponse data) {
        mAdapter.setData(data.getArtists());
        mNextPage = data.getNextPage();
        int currPostion = mGridView.getFirstVisiblePosition();
        mGridView.setAdapter(mAdapter);
        mGridView.setSelection(currPostion); //When updating adapter, it wants to scroll to top
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<MessagesArtistsResponse> loader) {
        mAdapter.setData(null);
    }


    @Override
    public void endOfListReached() {
        // When the end of the list is reached, load the new data
        Bundle bundle = new Bundle();
        bundle.putString("nextPage", mNextPage);
        getLoaderManager().restartLoader(0, bundle, this);
    }

    public static class QueryArtistsLoader extends AsyncTaskLoader<MessagesArtistsResponse>{
        MessagesArtistsResponse mArtists;
        Music mApi;
        String mNextPage;

        public QueryArtistsLoader(Context context, String nextPage) {
            super(context);
            mNextPage = nextPage;
            Music.Builder builder = new Music.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
            mApi = builder.build();
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
        public void onCanceled(MessagesArtistsResponse data) {
            super.onCanceled(data);
        }

        @Override
        public MessagesArtistsResponse loadInBackground() {
            MessagesArtistsResponse artists = null;
            try{
                if(mNextPage == null){
                    artists = mApi.music().artists().execute();
                }
                else{
                    artists = mApi.music().artists().setNextPage(mNextPage).execute();
                }
            } catch (IOException e){
                Log.d("ARTISTGRID", e.getMessage(), e);
            }
            if(artists != null){
                mNextPage = artists.getNextPage();
            }
            return artists;
        }

        @Override
        public void deliverResult(MessagesArtistsResponse data) {
            super.deliverResult(data);
        }
    }
}
