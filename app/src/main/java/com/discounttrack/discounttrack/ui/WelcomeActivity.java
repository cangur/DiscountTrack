package com.discounttrack.discounttrack.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.discounttrack.discounttrack.R;
import com.discounttrack.discounttrack.adapters.WelcomeScreenViewPagerAdapter;
import com.discounttrack.discounttrack.managers.PrefManager;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private PrefManager prefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        prefManager = new PrefManager( this );

        if (!prefManager.isFirstTimeLaunch()) {
            //launchHomeScreen();
            //finish();
        }

        if (Build.VERSION.SDK_INT > 21) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN );
        }

        setContentView( R.layout.activity_welcome );

        viewPager = findViewById( R.id.activity_welcome_view_pager );
        dotsLayout = findViewById( R.id.activity_welcome_layout_dots );
        btnSkip = findViewById( R.id.welcome_activity_btn_skip );
        btnNext = findViewById( R.id.welcome_activity_btn_next );

        layouts = new int[]{
                R.layout.welcome_slide1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4};

        addBottomDots( 0 );

        changeStatusBarColor();


        WelcomeScreenViewPagerAdapter myViewPagerAdapter = new WelcomeScreenViewPagerAdapter( layouts, this );
        viewPager.setAdapter( myViewPagerAdapter );
        viewPager.addOnPageChangeListener( this );

        btnNext.setOnClickListener( this );
        btnSkip.setOnClickListener( this );

    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS );
            window.setStatusBarColor( Color.TRANSPARENT );
        }
    }

    private void addBottomDots(int currentPage) {
        TextView[] dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray( R.array.array_dot_active );
        int[] colorsInactive = getResources().getIntArray( R.array.array_dot_inactive );

        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView( this );
            dots[i].setText( Html.fromHtml( "&#8226;" ) );
            dots[i].setTextSize( 35 );
            dots[i].setTextColor( colorsInactive[currentPage] );
            dotsLayout.addView( dots[i] );
        }
        if (dots.length > 0)
            dots[currentPage].setTextColor( colorsActive[currentPage] );
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch( false );
        startActivity( new Intent( WelcomeActivity.this, DashboardActivity.class ) );
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.welcome_activity_btn_next:
                int current = getItem( +1 );
                if (current < layouts.length) {
                    viewPager.setCurrentItem( current );
                } else {
                    launchHomeScreen();
                }
                break;

            case R.id.welcome_activity_btn_skip:
                launchHomeScreen();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        addBottomDots( position );

        // changing the next button text 'NEXT' / 'GOT IT'
        if (position == layouts.length - 1) {
            // last page. make button text to GOT IT
            btnNext.setText( getString( R.string.start ) );
            btnSkip.setVisibility( View.GONE );
        } else {
            // still pages are left
            btnNext.setText( getString( R.string.next ) );
            btnSkip.setVisibility( View.VISIBLE );
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
