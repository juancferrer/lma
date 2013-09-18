package com.micronixsolutions.livemusicarchive;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

/**
 * Created by juan on 9/18/13.
 */
public class ArtistsGridFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView  = inflater.inflate(R.layout.library_fragment, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.artists_grid_view);
        gridView.setAdapter(new ArtistGridAdapter(getActivity()));
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
