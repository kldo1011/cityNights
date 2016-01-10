/*package com.citynights;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;

import com.citynights.dao.AddressDataSource;
import com.citynights.dao.CityNightsDBHelper;
import com.citynights.model.Address;


public class MainActivity extends ActionBarActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private AddressDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource = new AddressDataSource(this);

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        Address address = dataSource.createAddress();
        Log.d(LOG_TAG, "Es wurde der folgende Eintrag in die Datenbank geschrieben:");
        Log.d(LOG_TAG, "ID: " + address.getId() + ", Inhalt: " + address.toString());

        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        showAllListEntries();

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();
    }

    private void showAllListEntries () {
        List<Address> addressList = dataSource.getAllAddresses();

        ArrayAdapter<Address> addressArrayAdapter = new ArrayAdapter<> (
                this,
                android.R.layout.simple_list_item_multiple_choice,
                addressList);

        ListView addressListView = (ListView) findViewById(R.id.listview_addresses);
        addressListView.setAdapter(addressArrayAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
/*
* TODO: METHODE UM ADDRESSEN ANZUZEIGEN
* TODO: Suche auf neue Activity verlinken

*/


