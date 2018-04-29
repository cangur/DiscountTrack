package com.discounttrack.discounttrack.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.discounttrack.discounttrack.R;
import com.discounttrack.discounttrack.models.Item;

import java.util.List;

public class CampaignRecyclerViewAdapter extends RecyclerView.Adapter<CampaignViewHolder> {

    private Context mContext;
    private List<Item> campaignList;

    public CampaignRecyclerViewAdapter(Context mContext, List<Item> campaignList) {
        this.mContext = mContext;
        this.campaignList = campaignList;
    }

    @Override
    public CampaignViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.campaign_list_item, parent, false );
        return new CampaignViewHolder( itemView );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(CampaignViewHolder holder, int position) {
        final Item item = campaignList.get( position );

        holder.name.setText( item.getName() );
        holder.description.setText( item.getDescription() );
        holder.price.setText( "₺" + item.getPrice() );

        Glide.with( mContext )
                .load( item.getThumbnail() )
                .into( holder.thumbnail );
    }

    @Override
    public int getItemCount() {
        return campaignList.size();
    }

    public void removeItem(int position) {
        campaignList.remove( position );
        notifyItemRemoved( position );
    }

    public void restoreItem(Item item, int position) {
        campaignList.add( position, item );
        notifyItemInserted( position );
    }
}
