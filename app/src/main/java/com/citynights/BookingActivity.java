package com.citynights;

import android.app.Dialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.citynights.dao.BookDataSource;
import com.citynights.dao.LoginDataBaseAdapter;
import com.citynights.model.Book;

import java.util.List;

/**
 * Created by morih on 18.01.2016.
 */
public class BookingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String LOG_TAG = BookingActivity.class.getSimpleName();
    private BookDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dataSource = new BookDataSource(this);

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();


        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        showAllListEntries();

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();




    }


    private void showAllListEntries () {
                List<Book> bookList = dataSource.getAllBooks();

                        ArrayAdapter<Book> bookArrayAdapter = new ArrayAdapter<>(
                                this,
                                android.R.layout.simple_list_item_multiple_choice,
                                bookList);

                ListView bookListView = (ListView) findViewById(R.id.listView);
                bookListView.setAdapter(bookArrayAdapter);
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
        if (id == R.id.nav_konto) {
            return true;
        }
        if (id == R.id.nav_favoriten) {
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
            Intent in=new Intent(BookingActivity.this,HomeActivity.class);
            startActivity(in);
            // Handle the camera action
        } else if (id == R.id.nav_suche) {
            Intent in=new Intent(BookingActivity.this,SearchableActivity.class);
            startActivity(in);

        } else if (id == R.id.nav_navigation) {
            Intent in=new Intent(BookingActivity.this,MapsActivity.class);
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
