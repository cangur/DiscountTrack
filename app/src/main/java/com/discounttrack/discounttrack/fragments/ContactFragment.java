package com.discounttrack.discounttrack.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.discounttrack.discounttrack.R;
import com.discounttrack.discounttrack.adapters.ContactRecyclerViewAdapter;
import com.discounttrack.discounttrack.handlers.SimpleDividerItemDecoration;
import com.discounttrack.discounttrack.models.Contact;

import java.util.ArrayList;
import java.util.List;


public class ContactFragment extends Fragment {

    View masterView;

    private RecyclerView contactRV;
    private List<Contact> listContact;

    public ContactFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        masterView = inflater.inflate( R.layout.contact_fragment, container, false );

        contactRV = masterView.findViewById( R.id.contact_recyclerview );
        ContactRecyclerViewAdapter contactRVAdapter = new ContactRecyclerViewAdapter( getContext(), listContact );
        contactRV.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        contactRV.addItemDecoration( new SimpleDividerItemDecoration( getContext() ) );
        contactRV.setAdapter( contactRVAdapter );

        return masterView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        listContact = new ArrayList<>();
        listContact.add( new Contact( "Can Gur", "05536339459", R.drawable.man ) );
        listContact.add( new Contact( "Engin Kandıra", "05544552222", R.drawable.man ) );
        listContact.add( new Contact( "Hakan Cücük", "05538856633", R.drawable.man ) );
        listContact.add( new Contact( "Avadis Hacınlınyan", "05054552525", R.drawable.man ) );
        listContact.add( new Contact( "Manu Dube", "05458555555", R.drawable.man ) );
        listContact.add( new Contact( "Avadis Hacınlınyan", "05054552525", R.drawable.man ) );
        listContact.add( new Contact( "Hakan Cücük", "05538856633", R.drawable.man ) );
        listContact.add( new Contact( "Can Gur", "05536339459", R.drawable.man ) );
        listContact.add( new Contact( "Manu Dube", "05458555555", R.drawable.man ) );
        listContact.add( new Contact( "Engin Kandıra", "05544552222", R.drawable.man ) );
        listContact.add( new Contact( "Engin Kandıra", "05544552222", R.drawable.man ) );
        listContact.add( new Contact( "Engin Kandıra", "05544552222", R.drawable.man ) );
        listContact.add( new Contact( "Engin Kandıra", "05544552222", R.drawable.man ) );


    }
}
