package com.citynights.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.citynights.dao.DatabaseSchema.AddressEntry;
import com.citynights.dao.DatabaseSchema.BookEntry;
import com.citynights.dao.DatabaseSchema.CustomerEntry;
import com.citynights.dao.DatabaseSchema.FavoriteEntry;
import com.citynights.dao.DatabaseSchema.ProposalEntry;
import com.citynights.dao.DatabaseSchema.ProviderEntry;
import com.citynights.dao.DatabaseSchema.RatingEntry;
import com.citynights.model.Address;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by morih on 20.12.2015.
 */
public class AddressDataSource {

    private static final String LOG_TAG = AddressDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private CityNightsDBHelper dbHelper;

    private String[] addresscolumns = {
            AddressEntry.COLUMN_NAME_ID,
            AddressEntry.COLUMN_NAME_STREET,
            AddressEntry.COLUMN_NAME_NUMBER,
            AddressEntry.COLUMN_NAME_ZIPCODE,
            AddressEntry.COLUMN_NAME_CITY,
            AddressEntry.COLUMN_NAME_COUNTRY,
            AddressEntry.COLUMN_NAME_X_COORD,
            AddressEntry.COLUMN_NAME_Y_COORD};

    public AddressDataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new CityNightsDBHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }


    //Pegasus Hostel Berlin

    public Address createAddress() {
        ContentValues values = new ContentValues();

        values.put(AddressEntry.COLUMN_NAME_STREET, "Str. der Pariser Kommune");
        values.put(AddressEntry.COLUMN_NAME_NUMBER, "35");
        values.put(AddressEntry.COLUMN_NAME_ZIPCODE, "10243");
        values.put(AddressEntry.COLUMN_NAME_CITY, "Berlin");
        values.put(AddressEntry.COLUMN_NAME_COUNTRY, "Deutschland");
        values.put(AddressEntry.COLUMN_NAME_X_COORD, "52.516000");
        values.put(AddressEntry.COLUMN_NAME_Y_COORD, "13.439920");

        long insertId = database.insert(AddressEntry.TABLE_NAME, null, values);

        Cursor cursor = database.query(AddressEntry.TABLE_NAME,
                addresscolumns, AddressEntry.COLUMN_NAME_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Address address = cursorToAddress(cursor);
        cursor.close();

        return address;
    }

    private Address cursorToAddress(Cursor cursor) {

        //Spalte ID wird zum erzeugen der Addresse nicht benötigt (ist auch -1 --> Cursor funktioniert nicht)
        //int idIndex = cursor.getColumnIndex(AddressEntry.COLUMN_NAME_ID);
        //Log.d(LOG_TAG, "ID:"+ idIndex);
        int idStreet = cursor.getColumnIndex(AddressEntry.COLUMN_NAME_STREET);
        int idNumber = cursor.getColumnIndex(AddressEntry.COLUMN_NAME_NUMBER);
        int idZip = cursor.getColumnIndex(AddressEntry.COLUMN_NAME_ZIPCODE);
        int idCity = cursor.getColumnIndex(AddressEntry.COLUMN_NAME_CITY);
        int idCountry = cursor.getColumnIndex(AddressEntry.COLUMN_NAME_COUNTRY);
        int idXCoord = cursor.getColumnIndex(AddressEntry.COLUMN_NAME_X_COORD);
        int idYCoord = cursor.getColumnIndex(AddressEntry.COLUMN_NAME_Y_COORD);

        String street = cursor.getString(idStreet);
        String number = cursor.getString(idNumber);
        String zip = cursor.getString(idZip);
        String city = cursor.getString(idCity);
        String country = cursor.getString(idCountry);
        String xcoord = cursor.getString(idXCoord);
        String ycoord = cursor.getString(idYCoord);
        //long id = cursor.getLong(idIndex);
        //Log.d(LOG_TAG, "ID:"+id);

        Address address = new Address(street, number, zip, city, country, xcoord, ycoord);

        return address;
    }

    public List<Address> getAllAddresses() {
        List<Address> addressList = new ArrayList<>();

        Cursor cursor = database.query(AddressEntry.TABLE_NAME,
                addresscolumns, null, null, null, null, null);

        cursor.moveToFirst();
        Address address;

        while(!cursor.isAfterLast()) {
            address = cursorToAddress(cursor);
            addressList.add(address);
            Log.d(LOG_TAG, "ID: " + address.getId() + ", Inhalt: " + address.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return addressList;
    }


    public List<Address> findAddresses(String query) {


        List<Address> addressList = new ArrayList<>();

        Cursor cursor = database.query(AddressEntry.TABLE_NAME,
                addresscolumns, AddressEntry.COLUMN_NAME_CITY + "=" + query, null, null, null, null);


        cursor.moveToFirst();
        Address address;

        while(!cursor.isAfterLast()) {
            address = cursorToAddress(cursor);
            addressList.add(address);
            Log.d(LOG_TAG, "ID: " + address.getId() + ", Inhalt: " + address.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return addressList;
    }





}