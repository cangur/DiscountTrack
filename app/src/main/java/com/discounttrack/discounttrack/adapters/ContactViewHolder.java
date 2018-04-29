package com.discounttrack.discounttrack.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.discounttrack.discounttrack.R;


public class ContactViewHolder extends RecyclerView.ViewHolder {

    private TextView tv_name;
    private TextView tv_phone;
    private ImageView img;

    public ContactViewHolder(View itemView) {
        super( itemView );

        tv_name = itemView.findViewById( R.id.txt_name_contact );
        tv_phone = itemView.findViewById( R.id.txt_phone_contact );
        img = itemView.findViewById( R.id.img_contact );
    }

    public TextView getTv_name() {
        return tv_name;
    }

    public TextView getTv_phone() {
        return tv_phone;
    }

    public ImageView getImg() {
        return img;
    }
}
