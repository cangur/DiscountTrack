package com.discounttrack.discounttrack.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.discounttrack.discounttrack.R;
import com.discounttrack.discounttrack.models.Contact;

import java.util.List;


public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private Context mContext;
    private List<Contact> mData;

    public ContactRecyclerViewAdapter(Context mContext, List<Contact> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View masterView = LayoutInflater.from( mContext ).inflate( R.layout.item_contact, parent, false );
        return new ContactViewHolder( masterView );
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.getTv_name().setText( mData.get( position ).getName() );
        holder.getTv_phone().setText( mData.get( position ).getPhone() );
        holder.getImg().setImageResource( mData.get( position ).getPhoto() );
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
