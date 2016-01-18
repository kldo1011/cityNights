package com.citynights;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.citynights.dao.LoginDataBaseAdapter;

/**
 * Created by Dominik on 20.12.2015.
 */

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String LOG_TAG = HomeActivity.class.getSimpleName();
    Button btnSignIn,btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // create a instance of SQLite Database
                loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        loginDataBaseAdapter.open();
                // Get The Reference Of Buttons
                btnSignIn=(Button)findViewById(R.id.buttonSignIN);
                btnSignUp=(Button)findViewById(R.id.buttonSignUP);




        // Set OnClick Listener on SignUp button
                btnSignUp.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                                // TODO Auto-generated method stub

                                /// Create Intent for SignUpActivity  abd Start The Activity
                                Intent intentSignUP = new Intent(getApplicationContext(),SignUPActivity.class);
                                startActivity(intentSignUP);
                            }
                    });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(HomeActivity.this,MapsActivity.class);
                startActivity(in);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // Methods to handleClick Event of Sign In Button
        public void signIn(View V)
        {
                final Dialog dialog = new Dialog(HomeActivity.this);
                dialog.setContentView(R.layout.login);
                dialog.setTitle("Login");

                // get the References of views
                final EditText editTextUserName = (EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
                final EditText editTextPassword = (EditText)dialog.findViewById(R.id.editTextPasswordToLogin);

                Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);

                        // Set On ClickListener
                                btnSignIn.setOnClickListener(new View.OnClickListener() {

                                                public void onClick(View v) {
                                                // get The User name and Password
                                                String userName = editTextUserName.getText().toString();
                                                String password = editTextPassword.getText().toString();

                                                // fetch the Password form database for respective user name
                                                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

                                                // check if the Stored password matches with  Password entered by user
                                                                if(password.equals(storedPassword))
                                                    {
                                                                Toast.makeText(HomeActivity.this, "Login Successfull", Toast.LENGTH_LONG).show();
                                                                dialog.dismiss();
                                                                Intent in=new Intent(HomeActivity.this,SearchableActivity.class);
                                                                startActivity(in);

                                                }
                                                else
                                                {
                                                            Toast.makeText(HomeActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                    });

                        dialog.show();
            }

                @Override
        protected void onDestroy() {
                super.onDestroy();
                // Close The Database
                        loginDataBaseAdapter.close();
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
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.nav_suche) {
            Intent in=new Intent(HomeActivity.this,SearchableActivity.class);
            startActivity(in);
            return true;
        }
        if (id == R.id.nav_navigation) {
            Intent in=new Intent(HomeActivity.this,MapsActivity.class);
            startActivity(in);
            return true;
        }
        if (id == R.id.nav_einstellungen) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_suche) {
            Intent in=new Intent(HomeActivity.this,SearchableActivity.class);
            startActivity(in);

        } else if (id == R.id.nav_navigation) {
            Intent in=new Intent(HomeActivity.this,MapsActivity.class);
            startActivity(in);

        } else if (id == R.id.nav_konto) {

        } else if (id == R.id.nav_bestellungen) {

        } else if (id == R.id.nav_favoriten) {

        } else if (id == R.id.nav_einstellungen) {

        } else if (id == R.id.nav_uber) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
