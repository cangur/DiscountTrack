package com.discounttrack.discounttrack.ui;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.discounttrack.discounttrack.R;
import com.discounttrack.discounttrack.adapters.ViewPagerAdapter;
import com.discounttrack.discounttrack.animationtoolbar.animation.GuillotineAnimation;
import com.discounttrack.discounttrack.fragments.CampaignFragment;
import com.discounttrack.discounttrack.fragments.ContactFragment;
import com.discounttrack.discounttrack.fragments.FavFragment;

public class DashboardActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private static final long RIPPLE_DURATION = 250;


    Toolbar toolbar;
    FrameLayout root;
    ImageView contentHamburger;

    TabLayout tabLayout;
    ViewPager viewPager;
    int icons[] = {
            R.drawable.ic_campaign,
            R.drawable.ic_fav,
            R.drawable.ic_people_contact
    };
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dashboard );

        root = findViewById( R.id.root );
        toolbar = findViewById( R.id.toolbar );
        contentHamburger = findViewById( R.id.content_hamburger );
        tabLayout = findViewById( R.id.tablayout_id );
        viewPager = findViewById( R.id.viewpager_id );


        initGuillotineToolbar();
        initEvent();

    }

    private void initEvent() {
        viewPagerAdapter = new ViewPagerAdapter( getSupportFragmentManager() );

        viewPagerAdapter.addFragment( new CampaignFragment(), "Campaign" );
        viewPagerAdapter.addFragment( new FavFragment(), "Favoris" );
        viewPagerAdapter.addFragment( new ContactFragment(), "Contacts" );

        tabLayout.setSelectedTabIndicatorColor( Color.parseColor( "#7e57c2" ) );
        tabLayout.setTabTextColors( Color.parseColor( "#eeeeee" ), Color.parseColor( "#7e57c2" ) );

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation( 0.0F );

        viewPager.setAdapter( viewPagerAdapter );
        tabLayout.setupWithViewPager( viewPager );

        setupTabIcons();
        tabLayout.addOnTabSelectedListener( this );
    }

    private void setupTabIcons() {
        tabLayout.getTabAt( 0 ).setIcon( icons[0] );
        tabLayout.getTabAt( 1 ).setIcon( icons[1] );
        tabLayout.getTabAt( 2 ).setIcon( icons[2] );
    }

    private void initGuillotineToolbar() {
        if (toolbar != null) {
            setSupportActionBar( toolbar );
            getSupportActionBar().setTitle( null );
        }

        View guillotineMenu = LayoutInflater.from( this ).inflate( R.layout.guillotine, null );
        root.addView( guillotineMenu );

        new GuillotineAnimation.GuillotineBuilder( guillotineMenu, guillotineMenu.findViewById( R.id.guillotine_hamburger ), contentHamburger )
                .setStartDelay( RIPPLE_DURATION )
                .setActionBarViewForAnimation( toolbar )
                .setClosedOnStart( true )
                .build();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int tabIconColor = ContextCompat.getColor( getApplicationContext(), R.color.tabIconSelectedColor );
        tab.getIcon().setColorFilter( tabIconColor, PorterDuff.Mode.SRC_IN );
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        int tabIconColor = ContextCompat.getColor( getApplicationContext(), R.color.tabIconUnSelectedColor );
        tab.getIcon().setColorFilter( tabIconColor, PorterDuff.Mode.SRC_IN );
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}