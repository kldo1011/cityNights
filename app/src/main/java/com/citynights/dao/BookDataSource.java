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
import com.citynights.model.Book;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by morih on 20.12.2015.
 */
public class BookDataSource {

    private static final String LOG_TAG = BookDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private CityNightsDBHelper dbHelper;


    private String[] bookcolumns = {
            BookEntry.COLUMN_CUSTOMER_FK,
            BookEntry.COLUMN_PROPOSAL_FK,
            BookEntry.COLUMN_NAME_GUESTS,
            BookEntry.COLUMN_NAME_PRICE,
            BookEntry.COLUMN_NAME_PERIOD_OF_TIME_FROM,
            BookEntry.COLUMN_NAME_PERIOD_OF_TIME_TO,
            BookEntry.COLUMN_NAME_TIMESTAMP};

    public BookDataSource(Context context) {
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




    private Book cursorToBook(Cursor cursor) {

        //Spalte ID wird zum erzeugen der Addresse nicht benötigt (ist auch -1 --> Cursor funktioniert nicht)
        //int idIndex = cursor.getColumnIndex(AddressEntry.COLUMN_NAME_ID);
        //Log.d(LOG_TAG, "ID:"+ idIndex);
        int idcustomer = cursor.getColumnIndex(BookEntry.COLUMN_CUSTOMER_FK);
        int idproposal = cursor.getColumnIndex(BookEntry.COLUMN_PROPOSAL_FK);
        int idguests = cursor.getColumnIndex(BookEntry.COLUMN_NAME_GUESTS);
        int idprice = cursor.getColumnIndex(BookEntry.COLUMN_NAME_PRICE);
        int idtimefrom = cursor.getColumnIndex(BookEntry.COLUMN_NAME_PERIOD_OF_TIME_FROM);
        int idtimeto = cursor.getColumnIndex(BookEntry.COLUMN_NAME_PERIOD_OF_TIME_TO);
        int idtimestamp = cursor.getColumnIndex(BookEntry.COLUMN_NAME_TIMESTAMP);

        Integer customer = cursor.getInt(idcustomer);
        Integer proposal = cursor.getInt(idproposal);
        Integer guests = cursor.getInt(idguests);
        Double price = cursor.getDouble(idprice);
        String timefrom = cursor.getString(idtimefrom);
        String timeto = cursor.getString(idtimeto);
        String timestamp = cursor.getString(idtimestamp);
        //long id = cursor.getLong(idIndex);
        //Log.d(LOG_TAG, "ID:"+id);

        Book book = new Book(customer, proposal, guests, price, timefrom, timeto, timestamp);

        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();

        Cursor cursor = database.query(BookEntry.TABLE_NAME,
                bookcolumns, null, null, null, null, null);

        cursor.moveToFirst();
        Book book;

        while(!cursor.isAfterLast()) {
            book = cursorToBook(cursor);
            bookList.add(book);
            Log.d(LOG_TAG, "ID: " + book.getId() + ", Inhalt: " + book.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return bookList;
    }

}