package com.mp.tshepo.mobile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mp.tshepo.mobile.realm.Feed;
import com.mp.tshepo.mobile.realm.RealmHelper;
import com.mp.tshepo.mobile.views.FrgHistoryFeed;
import com.mp.tshepo.mobile.views.FrgOneVariable;
import com.mp.tshepo.mobile.views.FrgTwoVariable;

import io.realm.Realm;

public class Home extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    public Realm realm;
    public RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        loadFragment(new FrgHistoryFeed());
        setupBottomNavigationView();
        Realm.init(this);
        realmHelper = new RealmHelper();
        realm = realmHelper.initialize("feedsDB");

        if (realm.isEmpty()) {
            create(realm);
        }
        realmHelper.read(realm);
    }

    public void create(Realm realm) {
        realm.beginTransaction();
        Feed feed = realm.createObject(Feed.class, 1);
        feed.setTitle("Hello!");
        feed.setOutput("Tap hold on an output to save");
        feed.setTimeStamp("Enjoy (:");
        realm.commitTransaction();
    }

    private void setupBottomNavigationView() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                Fragment fragment = null;
                switch (id) {
                    case R.id.action_history: {
                        fragment = new FrgHistoryFeed();
                        loadFragment(fragment);
                        break;
                    }
                    case R.id.action_one_var: {
                        fragment = new FrgOneVariable();
                        loadFragment(fragment);
                        break;
                    }
                    case R.id.action_two_var: {
                        fragment = new FrgTwoVariable();
                        loadFragment(fragment);
                        break;
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
