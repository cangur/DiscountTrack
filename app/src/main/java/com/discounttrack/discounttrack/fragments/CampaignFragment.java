package com.discounttrack.discounttrack.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.discounttrack.discounttrack.R;
import com.discounttrack.discounttrack.adapters.CampaignRecyclerViewAdapter;
import com.discounttrack.discounttrack.adapters.CampaignViewHolder;
import com.discounttrack.discounttrack.handlers.RecyclerItemTouchHelper;
import com.discounttrack.discounttrack.models.Item;
import com.discounttrack.discounttrack.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;


public class CampaignFragment extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private static final String URL = "https://api.androidhive.info/json/menu.json";
    private static final String TAG = MainActivity.class.getSimpleName();
    private View masterView;
    private List<Item> listCampaign;
    private RecyclerView recyclerView;
    private CampaignRecyclerViewAdapter mAdapter;
    private LinearLayout linearLayout;

    public CampaignFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        masterView = inflater.inflate( R.layout.campaign_fragment, container, false );

        //23.56


        linearLayout = masterView.findViewById( R.id.linear_layout );
        recyclerView = masterView.findViewById( R.id.campaign_recyclerview );
        listCampaign = new ArrayList<>();
        mAdapter = new CampaignRecyclerViewAdapter( getContext(), listCampaign );

        listCampaign.add( new Item( 1, "Adidas Samba ADV", "The adidas Samba is the second highest selling adidas model, following after the Stan Smith.", 140, R.drawable.adidas ) );
        listCampaign.add( new Item( 1, "Adidas Samba ADV", "The adidas Samba is the second highest selling adidas model, following after the Stan Smith.", 140, R.drawable.adidas ) );
        listCampaign.add( new Item( 1, "Adidas Samba ADV", "The adidas Samba is the second highest selling adidas model, following after the Stan Smith.", 140, R.drawable.adidas ) );
        listCampaign.add( new Item( 1, "Adidas Samba ADV", "The adidas Samba is the second highest selling adidas model, following after the Stan Smith.", 140, R.drawable.adidas ) );
        listCampaign.add( new Item( 1, "Adidas Samba ADV", "The adidas Samba is the second highest selling adidas model, following after the Stan Smith.", 140, R.drawable.adidas ) );
        listCampaign.add( new Item( 1, "Adidas Samba ADV", "The adidas Samba is the second highest selling adidas model, following after the Stan Smith.", 140, R.drawable.adidas ) );

        recyclerView.addItemDecoration( new DividerItemDecoration( getContext(), DividerItemDecoration.VERTICAL ) );
        //recyclerView.addItemDecoration( new SimpleDividerItemDecoration( getContext() ) );
        recyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        recyclerView.setAdapter( mAdapter );

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper( 0, ItemTouchHelper.LEFT, this );
        new ItemTouchHelper( itemTouchHelperCallback ).attachToRecyclerView( recyclerView );


        return masterView;
    }

    /*private void prepareCart() {
        JsonArrayRequest request = new JsonArrayRequest( URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText( getContext(), "Couldn't fetch the menu! Pleas try again.", Toast.LENGTH_LONG ).show();
                            return;
                        }

                        List<Item> items = new Gson().fromJson( response.toString(), new TypeToken<List<Item>>() {
                        }.getType() );

                        // adding items to cart list
                        listCampaign.clear();
                        listCampaign.addAll( items );

                        // refreshing recycler view
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.d( TAG, "Error: " + error.getMessage() );
                Toast.makeText( getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );

        MyApplication.getInstance().addToRequestQueue( request );
    }*/


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof CampaignViewHolder) {
            // get the removed item name to display it in snack bar
            String name = listCampaign.get( viewHolder.getAdapterPosition() ).getName();

            // backup of removed item for undo purpose
            final Item deletedItem = listCampaign.get( viewHolder.getAdapterPosition() );
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            mAdapter.removeItem( viewHolder.getAdapterPosition() );

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make( linearLayout, name + " removed from cart!", Snackbar.LENGTH_LONG );
            snackbar.setAction( "UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    mAdapter.restoreItem( deletedItem, deletedIndex );
                }
            } );
            snackbar.setActionTextColor( Color.YELLOW );
            snackbar.show();
        }
    }

}
