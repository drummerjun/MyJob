package com.drummerjun.myjob;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        initDrawer();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initDrawer() {
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_checked}, // selected
                new int[] {-android.R.attr.state_checked} // selected
        };
        int[] jobColors = new int[] { ContextCompat.getColor(this, R.color.colorJob), Color.DKGRAY };
        int[] schoolColors = new int[] { ContextCompat.getColor(this, R.color.colorSchool), Color.DKGRAY };
        int[] bioColors = new int[] { ContextCompat.getColor(this, R.color.colorBio), Color.DKGRAY };
        int[] textColors = new int[] { Color.BLACK, Color.DKGRAY };
        ColorStateList jobList = new ColorStateList(states, jobColors);
        ColorStateList schoolList = new ColorStateList(states, schoolColors);
        ColorStateList bioList = new ColorStateList(states, bioColors);
        ColorStateList textList = new ColorStateList(states, textColors);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemTextColor(textList);
        navigationView.setItemIconTintList(null);

        navigationView.getMenu().findItem(R.id.nav_jobs).getIcon().setTintList(jobList);
        navigationView.getMenu().findItem(R.id.nav_school).getIcon().setTintList(schoolList);
        navigationView.getMenu().findItem(R.id.nav_bio).getIcon().setTintList(bioList);
        navigationView.getMenu().findItem(R.id.nav_signout).setVisible(false);

        navigationView.setNavigationItemSelectedListener(this);
    }

    public static class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {

        public ScrollAwareFABBehavior() {
            super();
        }

        public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                           FloatingActionButton child,
                                           View directTargetChild,
                                           View target, int nestedScrollAxes)
        {
            return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                    super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target,
                            nestedScrollAxes);
        }

        @Override
        public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                                   View target, int dxConsumed, int dyConsumed,
                                   int dxUnconsumed, int dyUnconsumed)
        {
            super.onNestedScroll(coordinatorLayout, child, target,
                    dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
            if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
                child.hide();
            } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
                child.show();
            }
        }
    }

}
