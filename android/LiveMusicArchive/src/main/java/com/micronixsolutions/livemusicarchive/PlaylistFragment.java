package com.micronixsolutions.livemusicarchive;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by juan on 9/17/13.
 */
public class PlaylistFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().getActionBar().setTitle(getResources().getString(R.string.playlist_string));
        return inflater.inflate(R.layout.playlist_fragment, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
