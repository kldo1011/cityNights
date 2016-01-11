package com.citynights.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import java.util.ArrayList;
import com.citynights.dao.DatabaseSchema.AddressEntry;
import com.citynights.dao.DatabaseSchema.BookEntry;
import com.citynights.dao.DatabaseSchema.CustomerEntry;
import com.citynights.dao.DatabaseSchema.FavoriteEntry;
import com.citynights.dao.DatabaseSchema.ProposalEntry;
import com.citynights.dao.DatabaseSchema.ProviderEntry;
import com.citynights.dao.DatabaseSchema.RatingEntry;
import com.citynights.model.Customer;
import com.citynights.model.Address;
import java.util.HashMap;

/**
 * Created by Dominik on 02.12.2015.
 *
 *
 * TODO METHODEN SQL Anpassen
 */

public class CityNightsDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "cityNights.db";
    private static final String LOG_TAG = CityNightsDBHelper.class.getSimpleName();

    private static final String SQL_CREATE_TABLE_ADDRESS =
            "CREATE TABLE " + AddressEntry.TABLE_NAME + " (" +
                    AddressEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    AddressEntry.COLUMN_NAME_STREET + " TEXT," +
                    AddressEntry.COLUMN_NAME_NUMBER + " TEXT," +
                    AddressEntry.COLUMN_NAME_ZIPCODE + " TEXT," +
                    AddressEntry.COLUMN_NAME_CITY + " TEXT," +
                    AddressEntry.COLUMN_NAME_COUNTRY + " TEXT," +
                    AddressEntry.COLUMN_NAME_X_COORD + " TEXT," +
                    AddressEntry.COLUMN_NAME_Y_COORD + " TEXT);";

    private static final String SQL_CREATE_TABLE_CUSTOMER =
            "CREATE TABLE " + CustomerEntry.TABLE_NAME + " (" +
                    CustomerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CustomerEntry.COLUMN_NAME_FIRSTNAME + " TEXT," +
                    CustomerEntry.COLUMN_NAME_LASTNAME + " TEXT," +
                    CustomerEntry.COLUMN_NAME_PHONE + " TEXT," +
                    CustomerEntry.COLUMN_NAME_MAIL + " TEXT," +
                    CustomerEntry.COLUMN_ADDRESS_FK + " INTEGER," +
                    CustomerEntry.COLUMN_NAME_PASSWORD + "TEXT, " +
                    CustomerEntry.COLUMN_NAME_NEWSLETTER + " BOOLEAN," +
                    "FOREIGN KEY(" + DatabaseSchema.CustomerEntry.COLUMN_ADDRESS_FK + ") REFERENCES " + AddressEntry.TABLE_NAME + "(" + AddressEntry._ID + ")" +
                    ")";
    private static final String SQL_CREATE_TABLE_PROVIDER =
            "CREATE TABLE " + ProviderEntry.TABLE_NAME + " (" +
                    ProviderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ProviderEntry.COLUMN_NAME_FIRSTNAME + " TEXT," +
                    ProviderEntry.COLUMN_NAME_LASTNAME + " TEXT," +
                    ProviderEntry.COLUMN_NAME_PHONE + " TEXT," +
                    ProviderEntry.COLUMN_NAME_MAIL + " TEXT," +
                    ProviderEntry.COLUMN_ADDRESS_FK + " INTEGER," +
                    ProviderEntry.COLUMN_NAME_PASSWORD + " TEXT, " +
                    "FOREIGN KEY(" + DatabaseSchema.ProviderEntry.COLUMN_ADDRESS_FK + ") REFERENCES " + AddressEntry.TABLE_NAME + "(" + AddressEntry._ID + ")" +
                    ")";
    private static final String SQL_CREATE_TABLE_FAVORITE =
            "CREATE TABLE " + FavoriteEntry.TABLE_NAME + " (" +
                    FavoriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FavoriteEntry.COLUMN_CUSTOMER_FK + " INTEGER," +
                    FavoriteEntry.COLUMN_PROPOSAL_FK + " INTEGER," +

                    "FOREIGN KEY(" + FavoriteEntry.COLUMN_CUSTOMER_FK + ") REFERENCES " + CustomerEntry.TABLE_NAME + "(" + CustomerEntry._ID + ")," +
                    "FOREIGN KEY(" + FavoriteEntry.COLUMN_PROPOSAL_FK + ") REFERENCES " + ProposalEntry.TABLE_NAME + "(" + ProposalEntry._ID + "));";


    private static final String SQL_CREATE_TABLE_RATING =
            "CREATE TABLE " + RatingEntry.TABLE_NAME + " (" +
                    RatingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    RatingEntry.COLUMN_CUSTOMER_FK + " INTEGER," +
                    RatingEntry.COLUMN_PROPOSAL_FK + " INTEGER," +
                    RatingEntry.COLUMN_NAME_STARS + " INTEGER, " +
                    RatingEntry.COLUMN_NAME_DESCRIPTION + " TEXT, " +
                    RatingEntry.COLUMN_NAME_TITLE + " TEXT, " +
                    RatingEntry.COLUMN_NAME_TIMESTAMP + " TIMESTAMP," +

                    "FOREIGN KEY(" + RatingEntry.COLUMN_CUSTOMER_FK + ") REFERENCES " + CustomerEntry.TABLE_NAME + "(" + CustomerEntry._ID + ")," +
                    "FOREIGN KEY(" + RatingEntry.COLUMN_PROPOSAL_FK + ") REFERENCES " + ProposalEntry.TABLE_NAME + "(" + ProposalEntry._ID + "));";

    private static final String SQL_CREATE_TABLE_BOOK =
            "CREATE TABLE " + BookEntry.TABLE_NAME + " (" +
                    BookEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    BookEntry.COLUMN_CUSTOMER_FK + " INTEGER," +
                    BookEntry.COLUMN_PROPOSAL_FK + " INTEGER," +
                    BookEntry.COLUMN_NAME_GUESTS + " INTEGER," +
                    BookEntry.COLUMN_NAME_PERIOD_OF_TIME_FROM + " TEXT," +
                    BookEntry.COLUMN_NAME_PERIOD_OF_TIME_TO + " TEXT," +
                    BookEntry.COLUMN_NAME_PRICE + " DOUBLE, " +
                    BookEntry.COLUMN_NAME_TIMESTAMP + " TIMESTAMP, " +

                    "FOREIGN KEY(" + BookEntry.COLUMN_CUSTOMER_FK + ") REFERENCES " + CustomerEntry.TABLE_NAME + "(" + CustomerEntry._ID + ")," +

                    "FOREIGN KEY(" + BookEntry.COLUMN_PROPOSAL_FK + ") REFERENCES " + ProposalEntry.TABLE_NAME + "(" + ProposalEntry._ID + ")" +
                    ")";

    private static final String SQL_CREATE_TABLE_PROPOSAL =
            "CREATE TABLE " + ProposalEntry.TABLE_NAME + " (" +
                    ProposalEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ProposalEntry.COLUMN_PROVIDER_FK + " INTEGER, " +
                    ProposalEntry.COLUMN_ADDRESS_FK + " INTEGER, " +
                    ProposalEntry.COLUMN_NAME_NAME + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_TYPE_OF_ACCOMMONDATION + " TEXT," +
                    ProposalEntry.COLUMN_NAME_DESCRIPTION + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_BATHROOM + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_TELEVSION + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_WIFI + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_INTERNET_ACCESS + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_RESTAURANT + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_BAR + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_AIR_CONDITIONING + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_POOL + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_ROOM1_NAME + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_ROOM1_description + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_ROOM1_price + " DOUBLE, " +
                    ProposalEntry.COLUMN_NAME_ROOM2_NAME + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_ROOM2_description + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_ROOM2_price + " DOUBLE, " +
                    ProposalEntry.COLUMN_NAME_ROOM3_NAME + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_ROOM3_description + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_ROOM3_price + " DOUBLE, " +
                    /*ProposalEntry.COLUMN_NAME_ROOM4_NAME + " TEXT ," +
                    ProposalEntry.COLUMN_NAME_ROOM4_description + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_ROOM4_price + "DOUBLE ," +
                    ProposalEntry.COLUMN_NAME_ROOM5_NAME + " TEXT ," +
                    ProposalEntry.COLUMN_NAME_ROOM5_description + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_ROOM5_price + "DOUBLE ," +
                    ProposalEntry.COLUMN_NAME_ROOM6_NAME + " TEXT ," +
                    ProposalEntry.COLUMN_NAME_ROOM6_description + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_ROOM6_price + "DOUBLE ," +
                    ProposalEntry.COLUMN_NAME_ROOM7_NAME + " TEXT ," +
                    ProposalEntry.COLUMN_NAME_ROOM7_description + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_ROOM7_price + "DOUBLE ," +
                    ProposalEntry.COLUMN_NAME_ROOM8_NAME + " TEXT ," +
                    ProposalEntry.COLUMN_NAME_ROOM8_description + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_ROOM8_price + "DOUBLE ," +
                    ProposalEntry.COLUMN_NAME_ROOM9_NAME + " TEXT ," +
                    ProposalEntry.COLUMN_NAME_ROOM9_description + " TEXT, " +
                    ProposalEntry.COLUMN_NAME_ROOM9_price + "DOUBLE ," +*/

                    "FOREIGN KEY(" + ProposalEntry.COLUMN_PROVIDER_FK + ") REFERENCES " + ProviderEntry.TABLE_NAME + "(" + ProviderEntry._ID + ")," +
                    "FOREIGN KEY(" + ProposalEntry.COLUMN_ADDRESS_FK + ") REFERENCES " + AddressEntry.TABLE_NAME + "(" + AddressEntry._ID + "));";


    /**
     * Datenbank mit Beispiel Daten füllen
    */
    //ADDRESSES
    //Grand Hostel Berlin
    private static final String SQL_INSERT_TABLE_ADDRESS_BERLIN_1 = "INSERT INTO " + AddressEntry.TABLE_NAME + " VALUES(1, \"Tempelhofer Ufer\", \"14\", \"10963\", \"Berlin\", \"Deutschland\", \"52.498579\",\"13.384430\");";
    //Smarthostel Berlin
    private static final String SQL_INSERT_TABLE_ADDRESS_BERLIN_2 = "INSERT INTO " + AddressEntry.TABLE_NAME + " VALUES(2, \"Genter Straße\", \"53A\", \"13353\", \"Berlin\", \"Deutschland\", \"52.547612\",\"13.353171\");";
    //Cityhostel Berlin
    private static final String SQL_INSERT_TABLE_ADDRESS_BERLIN_3 = "INSERT INTO " + AddressEntry.TABLE_NAME + " VALUES(3, \"Glinkastraße\", \"5-7\", \"10117\", \"Berlin\", \"Deutschland\", \"52.511225\",\"13.386047\");";
    //Pegasus Hostel Berlin
    private static final String SQL_INSERT_TABLE_ADDRESS_BERLIN_4 = "INSERT INTO " + AddressEntry.TABLE_NAME + " VALUES(4, \"Str. der Pariser Kommune\", \"35\", \"10243\", \"Berlin\", \"Deutschland\", \"52.515972\",\"13.440060\");";
    //Aurora Hostel Berlin
    private static final String SQL_INSERT_TABLE_ADDRESS_BERLIN_5 = "INSERT INTO " + AddressEntry.TABLE_NAME + " VALUES(5, \"Pappelallee\", \"21\", \"10437\", \"Berlin\", \"Deutschland\", \"52.544150\",\"13.416049\");";
    //Sunflower Hostel Berlin
    private static final String SQL_INSERT_TABLE_ADDRESS_BERLIN_6 = "INSERT INTO " + AddressEntry.TABLE_NAME + " VALUES(6, \"Helsingforser Straße\", \"17\", \"10243\", \"Berlin\", \"Deutschland\", \"52.509630\",\"13.446274\");";

    //PROPOSALS
    //Grand Hostel Berlin
    private static final String SQL_INSERT_TABLE_PROPOSAL_1 = "INSERT INTO " + ProposalEntry.TABLE_NAME + " VALUES(1, 1, 1, \"Grand Hostel Berlin\", \"Hostel\", \"Ein sehr günstiges Hostel!\", 1,1,1,1,1,1,1,1, \"Einzelzimmer\", \"Zimmer für eine Person\", \"35€\", \"Doppelzimmer\", \"Zimmer mit Doppelbett\", \"30€\", \"6 Bett Zimmer\", \"Zimmer mit 6 Personen\", \"29€\");";
    //Smarthostel Berlin
    private static final String SQL_INSERT_TABLE_PROPOSAL_2 = "INSERT INTO " + ProposalEntry.TABLE_NAME + " VALUES(2, 1, 2, \"Smarthostel Berlin\", \"Hostel\", \"Ein sehr smartes Hostel!\", 1,0,0,1,0,1,0,1, \"Einzelzimmer\", \"Zimmer für eine Person\", \"32€\", \"Doppelzimmer\", \"Zimmer mit Doppelbett\", \"25€\", \"6 Bett Zimmer\", \"Zimmer mit 6 Personen\", \"22€\");";
    //Cityhostel Berlin
    private static final String SQL_INSERT_TABLE_PROPOSAL_3 = "INSERT INTO " + ProposalEntry.TABLE_NAME + " VALUES(3, 1, 3, \"Cityhostel Berlin\", \"Hostel\", \"Ein sehr gutes Hostel mitten in der City!\", 0,1,0,1,1,0,1,0, \"Einzelzimmer\", \"Zimmer für eine Person\", \"25€\", \"Doppelzimmer\", \"Zimmer mit Doppelbett\", \"24€\", \"6 Bett Zimmer\", \"Zimmer mit 6 Personen\", \"20€\");";
    //Pegasus Hostel Berlin
    private static final String SQL_INSERT_TABLE_PROPOSAL_4 = "INSERT INTO " + ProposalEntry.TABLE_NAME + " VALUES(4, 1, 4, \"Pegasus Hostel Berlin\", \"Hostel\", \"Come in and find sleep!\", 0,0,1,0,0,1,0,0, \"Einzelzimmer\", \"Zimmer für eine Person\", \"22€\", \"Doppelzimmer\", \"Zimmer mit Doppelbett\", \"20€\", \"6 Bett Zimmer\", \"Zimmer mit 6 Personen\", \"19€\");";
    //Aurora Hostel Berlin
    private static final String SQL_INSERT_TABLE_PROPOSAL_5 = "INSERT INTO " + ProposalEntry.TABLE_NAME + " VALUES(5, 1, 5, \"Aurora Hostel Berlin\", \"Hostel\", \"Wir haben die besten Betten!\", 0,0,0,0,0,0,0,0, \"Einzelzimmer\", \"Zimmer für eine Person\", \"19€\", \"Doppelzimmer\", \"Zimmer mit Doppelbett\", \"19€\", \"6 Bett Zimmer\", \"Zimmer mit 6 Personen\", \"19€\");";
    //Sunflower Hostel Berlin
    private static final String SQL_INSERT_TABLE_PROPOSAL_6 = "INSERT INTO " + ProposalEntry.TABLE_NAME + " VALUES(6, 1, 6, \"Sunflower Hostel Berlin\", \"Hostel\", \"Sonnenblume!\", 1,1,0,1,1,0,0,0, \"Einzelzimmer\", \"Zimmer für eine Person\", \"31€\", \"Doppelzimmer\", \"Zimmer mit Doppelbett\", \"26€\", \"6 Bett Zimmer\", \"Zimmer mit 6 Personen\", \"22€\");";




    private static final String SQL_DROP_TABLE_CUSTOMER = "DROP TABLE IF EXISTS " + CustomerEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_ADDRESS = "DROP TABLE IF EXISTS " + AddressEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_PROVIDER = "DROP TABLE IF EXISTS " + ProviderEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_BOOK = "DROP TABLE IF EXISTS " + BookEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_RATING = "DROP TABLE IF EXISTS " + RatingEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_FAVORITE = "DROP TABLE IF EXISTS " + FavoriteEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_PROPOSAL = "DROP TABLE IF EXISTS " + ProposalEntry.TABLE_NAME + ";";


    public CityNightsDBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }

    // Die onCreate-Methode wird nur aufgerufen, falls die Datenbank noch nicht existiert
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabellen werden angelegt.");
            db.execSQL(SQL_CREATE_TABLE_ADDRESS);
            db.execSQL(SQL_CREATE_TABLE_CUSTOMER);
            db.execSQL(SQL_CREATE_TABLE_PROVIDER);
            db.execSQL(SQL_CREATE_TABLE_PROPOSAL);
            db.execSQL(SQL_CREATE_TABLE_FAVORITE);
            db.execSQL(SQL_CREATE_TABLE_RATING);
            db.execSQL(SQL_CREATE_TABLE_BOOK);
            //Addresses
            db.execSQL(SQL_INSERT_TABLE_ADDRESS_BERLIN_1);
            db.execSQL(SQL_INSERT_TABLE_ADDRESS_BERLIN_2);
            db.execSQL(SQL_INSERT_TABLE_ADDRESS_BERLIN_3);
            db.execSQL(SQL_INSERT_TABLE_ADDRESS_BERLIN_4);
            db.execSQL(SQL_INSERT_TABLE_ADDRESS_BERLIN_5);
            db.execSQL(SQL_INSERT_TABLE_ADDRESS_BERLIN_6);
            //Proposal
            db.execSQL(SQL_INSERT_TABLE_PROPOSAL_1);
            db.execSQL(SQL_INSERT_TABLE_PROPOSAL_2);
            db.execSQL(SQL_INSERT_TABLE_PROPOSAL_3);
            db.execSQL(SQL_INSERT_TABLE_PROPOSAL_4);
            db.execSQL(SQL_INSERT_TABLE_PROPOSAL_5);
            db.execSQL(SQL_INSERT_TABLE_PROPOSAL_6);
            //Customer
            //Provider
            //Favorite
            //Rating
            //Book

        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}