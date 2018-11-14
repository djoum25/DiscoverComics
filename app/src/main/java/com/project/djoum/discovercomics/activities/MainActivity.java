package com.project.djoum.discovercomics.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.djoum.discovercomics.IMainActivity;
import com.project.djoum.discovercomics.R;
import com.project.djoum.discovercomics.fragment.ComicDetailsFragment;
import com.project.djoum.discovercomics.fragment.MainFragment;
import com.project.djoum.discovercomics.model.comics.Comics;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                           IMainActivity,
                           ComicDetailsFragment.OnComicDetailsFragmentInteractionListener {
    private static final String TAG = "MainActivity";
    //    private final String PUBLIC_KEY = getString(R.string.public_key);
//    private final String PRIVATE_KEY = getString(R.string.private_key);
    private TextView mUserName;
    private TextView mUserEmail;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private View mHearderView;
    private NavigationView mNavigationView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mHearderView = mNavigationView.getHeaderView(0);
        mNavigationView.setNavigationItemSelectedListener(this);
        //init main fragment
        initialize();
    }
    
    public void initialize() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, MainFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        mUserName = mHearderView.findViewById(R.id.user_name);
        mUserEmail = mHearderView.findViewById(R.id.user_email);
        if (mUser != null) {
            // TODO: 10/22/18 set the user name
            mUserName.setText("set the user name here");
            mUserEmail.setText(mUser.getEmail());
            if (mNavigationView != null) {
                Menu menu = mNavigationView.getMenu();
                menu.findItem(R.id.nav_sign_in).setTitle(R.string.sign_out);
            }
        } else {
            mUserEmail.setText(null);
            mUserName.setText(null);
        }
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
        int id = item.getItemId();
        
        if (id == R.id.action_settings) {
            Toast.makeText(this, "this is the setting", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_sign_out) {
            mAuth.signOut();
            mUserName.setText(null);
            mUserEmail.setText(null);
            if (mNavigationView != null) {
                Menu menu = mNavigationView.getMenu();
                menu.findItem(R.id.nav_sign_in).setTitle(R.string.sign_in);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
    
        if (id == R.id.nav_sign_in) {
            if (mUser == null) {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            } else {
                mAuth.signOut();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        } else if (id == R.id.nav_series) {
        
        } else if (id == R.id.nav_characters) {
        
        } else if (id == R.id.nav_favorites) {
        
        } else if (id == R.id.nav_search) {
        
        }
        
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    
    @Override
    public void comicDetails(Comics comic) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, ComicDetailsFragment.newInstance(comic))
                .addToBackStack(null)
                .commit();
        
    }

//    public boolean isConnectionAvaillable() {
//        ConnectivityManager connectivityManager =
//                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivityManager != null) {
//            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//            return networkInfo != null && networkInfo.isConnectedOrConnecting();
//        }
//        return false;
//    }
    
}
