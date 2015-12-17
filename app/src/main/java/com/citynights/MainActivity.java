package com.citynights;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.citynights.dao.CityNightsDBHelper;
import com.citynights.model.Address;


public class MainActivity extends ActionBarActivity {

    SearchView searchCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchCity = (SearchView) findViewById(R.id.searchView);
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

/*
* TODO: METHODE UM ADDRESSEN ANZUZEIGEN:
*
    public void findAddresses (View view) {
        CityNightsDBHelper dbHandler = new CityNightsDBHelper(this, null, null, 1);

        Address product =
                dbHandler.findAddressByCityname(searchCity.getQuery().toString());


        if (product != null) {
            idView.setText(String.valueOf(address.getId()));

            quantityBox.setText(String.valueOf(product.getQuantity()));
        } else {
            idView.setText("No Match Found");
        }
    }
*/
}
