package com.discounttrack.discounttrack.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.discounttrack.discounttrack.R;


public class CampaignViewHolder extends RecyclerView.ViewHolder {

    public TextView name, description, price;
    public ImageView thumbnail;
    public RelativeLayout viewBackground, viewForeground;

    public CampaignViewHolder(View itemView) {
        super( itemView );

        name = itemView.findViewById( R.id.name );
        description = itemView.findViewById( R.id.description );
        price = itemView.findViewById( R.id.price );
        thumbnail = itemView.findViewById( R.id.thumbnail );
        viewBackground = itemView.findViewById( R.id.view_background );
        viewForeground = itemView.findViewById( R.id.view_foreground );
    }
}
