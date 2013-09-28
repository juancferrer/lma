package com.micronixsolutions.livemusicarchive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.micronixsolutions.api.music.model.MessagesArtistResponse;
import com.micronixsolutions.api.music.model.MessagesShowRecordingResponse;

import java.util.List;

/**
 * Created by juan on 9/18/13.
 */
public class ShowsGridAdapter extends ArrayAdapter<MessagesShowRecordingResponse> {
    private Context mContext;

    public ShowsGridAdapter(Context context){
        super(context, R.layout.artist_grid_item, R.id.artist_grid_item_text);
        mContext = context;
    }

    /*
    public void setData (List<MessagesArtistResponse> data){
        if(data != null){
            addAll(data);
        }
    }
    */

    /*
    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridItem = convertView;
        if(gridItem == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridItem = inflater.inflate(R.layout.artist_grid_item, parent, false);
        }
        MessagesShowRecordingResponse show = getItem(position);
        TextView tv = (TextView) gridItem.findViewById(R.id.artist_grid_item_text);
        tv.setText(show.getTitle());
        return gridItem;
    }
}
