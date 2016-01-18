package com.citynights;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import com.citynights.dao.CityNightsDBHelper;
import com.citynights.dao.DatabaseSchema;
import com.citynights.dao.ProposalDataSource;
import com.citynights.model.Proposal;


public class SearchableActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener   {

    public static final String LOG_TAG = SearchableActivity.class.getSimpleName();

    private ProposalDataSource dataSource;
    private SearchView searchView;
    private ListView myList;
    private MyCustomAdapter defaultAdapter;
    private ArrayList<Proposal> proposalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(SearchableActivity.this,MapsActivity.class);
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

        proposalList = new ArrayList<Proposal>();

        //relate the listView from java to the one created in xml
        myList = (ListView) findViewById(R.id.list);

        //show the ListView on the screen
        // The adapter MyCustomAdapter is responsible for maintaining the data backing this nameList and for producing
        // a view to represent an item in that data set.
        defaultAdapter = new MyCustomAdapter(SearchableActivity.this, proposalList);
        myList.setAdapter(defaultAdapter);

        //prepare the SearchView
        searchView = (SearchView) findViewById(R.id.searchView);

        //Sets the default or resting state of the search field. If true, a single search icon is shown by default and
        // expands to show the text field and other buttons when pressed. Also, if the default state is iconified, then it
        // collapses to that state when the close button is pressed. Changes to this property will take effect immediately.
        //The default value is true.
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);

        dataSource = new ProposalDataSource(this);
        dataSource.open();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (dataSource  != null) {
            dataSource.close();
        }
    }

    @Override
    public boolean onClose() {
        myList.setAdapter(defaultAdapter);
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        displayResults(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!newText.isEmpty()){
            displayResults(newText);
        } else {
            myList.setAdapter(defaultAdapter);
        }

        return false;
    }

    /**
     * Method used for performing the search and displaying the results. This method is called every time a letter
     * is introduced in the search field.
     *
     * @param query Query used for performing the search
     */
    private void displayResults(String query) {

        Cursor cursor = dataSource.searchByInputText((query != null ? query : "@@@@"));

        if (cursor != null) {

            String[] from = new String[] {DatabaseSchema.ProposalEntry.COLUMN_NAME_NAME};

            // Specify the view where we want the results to go
            int[] to = new int[] {R.id.search_result_text_view};

            // Create a simple cursor adapter to keep the search data
            SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.result_search_item, cursor, from, to);
            myList.setAdapter(cursorAdapter);

            // Click listener for the searched item that was selected
            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // Get the cursor, positioned to the corresponding row in the result set
                    Cursor cursor = (Cursor) myList.getItemAtPosition(position);

                    // Get the state's capital from this row in the database.
                    String selectedName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String grandhostel = "Grand Hostel Berlin";
                    Toast.makeText(SearchableActivity.this, selectedName, Toast.LENGTH_SHORT).show();
                    if(grandhostel.equals(selectedName)){
                        Intent in=new Intent(SearchableActivity.this,DetailActivity.class);
                        startActivity(in);
                    }


                    // Set the default adapter
                    myList.setAdapter(defaultAdapter);

                    // Find the position for the original list by the selected name from search
                    for (int pos = 0; pos < proposalList.size(); pos++) {
                        if (proposalList.get(pos).equals(selectedName)){
                            position = pos;
                            break;
                        }
                    }

                    searchView.setQuery("",true);
                }
            });

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
        if (id == R.id.nav_navigation) {
            Intent in=new Intent(SearchableActivity.this,MapsActivity.class);
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

            Intent in=new Intent(SearchableActivity.this,HomeActivity.class);
            startActivity(in);
            // Handle the camera action
        } else if (id == R.id.nav_suche) {


        } else if (id == R.id.nav_navigation) {
            Intent in=new Intent(SearchableActivity.this,MapsActivity.class);
            startActivity(in);

        } else if (id == R.id.nav_konto) {

        } else if (id == R.id.nav_bestellungen) {
            Intent in=new Intent(SearchableActivity.this,BookingActivity.class);
            startActivity(in);
        } else if (id == R.id.nav_favoriten) {

        } else if (id == R.id.nav_einstellungen) {

        } else if (id == R.id.nav_uber) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
