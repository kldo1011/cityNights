package com.citynights.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.citynights.dao.DatabaseSchema.AddressEntry;
import com.citynights.dao.DatabaseSchema.BookEntry;
import com.citynights.dao.DatabaseSchema.CustomerEntry;
import com.citynights.dao.DatabaseSchema.FavoriteEntry;
import com.citynights.dao.DatabaseSchema.ProposalEntry;
import com.citynights.dao.DatabaseSchema.ProviderEntry;
import com.citynights.dao.DatabaseSchema.RatingEntry;
import com.citynights.model.Customer;
import com.citynights.model.Address;

/**
 * Created by Dominik on 02.12.2015.
 *
 *
 * TODO METHODEN SQL Anpassen
 */



public class CityNightsDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "cityNights.db";

    private static final String SQL_CREATE_TABLE_ADDRESS =
            "CREATE TABLE " + AddressEntry.TABLE_NAME + " (" +
                    AddressEntry._ID + " INTEGER PRIMARY KEY," +
                    AddressEntry.COLUMN_NAME_STREET + " TEXT," +
                    AddressEntry.COLUMN_NAME_NUMBER + " TEXT," +
                    AddressEntry.COLUMN_NAME_ZIPCODE + " TEXT," +
                    AddressEntry.COLUMN_NAME_CITY + " TEXT," +
                    AddressEntry.COLUMN_NAME_COUNTRY + " TEXT" +
                    AddressEntry.COLUMN_NAME_X_COORD + " TEXT" +
                    AddressEntry.COLUMN_NAME_Y_COORD + "TEXT"+
                    ");";

    private static final String SQL_CREATE_TABLE_CUSTOMER =
            "CREATE TABLE " + CustomerEntry.TABLE_NAME + " (" +
                    CustomerEntry._ID + " INTEGER PRIMARY KEY," +
                    CustomerEntry.COLUMN_NAME_FIRSTNAME + " TEXT," +
                    CustomerEntry.COLUMN_NAME_LASTNAME + " TEXT," +
                    CustomerEntry.COLUMN_NAME_PHONE + " TEXT," +
                    CustomerEntry.COLUMN_NAME_MAIL + " TEXT," +
                    CustomerEntry.COLUMN_ADDRESS_FK + " INTEGER," +
                    CustomerEntry.COLUMN_NAME_PASSWORD + "TEXT, "+
                    CustomerEntry.COLUMN_NAME_NEWSLETTER + "BOOLEAN,"+
                    "FOREIGN KEY(" + DatabaseSchema.CustomerEntry.COLUMN_ADDRESS_FK + ") REFERENCES " + AddressEntry.TABLE_NAME + "(" + AddressEntry._ID + ")" +
                    ")";
    private static final String SQL_CREATE_TABLE_PROVIDER =
            "CREATE TABLE " + ProviderEntry.TABLE_NAME + " (" +
                    ProviderEntry._ID + " INTEGER PRIMARY KEY," +
                    ProviderEntry.COLUMN_NAME_FIRSTNAME + " TEXT," +
                    ProviderEntry.COLUMN_NAME_LASTNAME + " TEXT," +
                    ProviderEntry.COLUMN_NAME_PHONE + " TEXT," +
                    ProviderEntry.COLUMN_NAME_MAIL + " TEXT," +
                    ProviderEntry.COLUMN_ADDRESS_FK + " INTEGER," +
                    ProviderEntry.COLUMN_NAME_PASSWORD + "TEXT, "+
                    "FOREIGN KEY(" + DatabaseSchema.ProviderEntry.COLUMN_ADDRESS_FK + ") REFERENCES " + AddressEntry.TABLE_NAME + "(" + AddressEntry._ID + ")" +
                    ")";
    private static final String SQL_CREATE_TABLE_FAVORITE =
            "CREATE TABLE " + FavoriteEntry.TABLE_NAME + " (" +
                    FavoriteEntry._ID + " INTEGER PRIMARY KEY," +
                    FavoriteEntry.COLUMN_CUSTOMER_FK + " INTEGER," +
                    FavoriteEntry.COLUMN_PROPOSAL_FK + " INTEGER," +
                    /*
                    Nochmal anschauen
                     */
                    "FOREIGN KEY(" + FavoriteEntry.COLUMN_CUSTOMER_FK + FavoriteEntry.COLUMN_PROPOSAL_FK +") REFERENCES " + CustomerEntry.TABLE_NAME + ProposalEntry.TABLE_NAME + "(" + CustomerEntry._ID + ProposalEntry._ID + ")" +
                    ")";

    private static final String SQL_CREATE_TABLE_RATING =
            "CREATE TABLE " + RatingEntry.TABLE_NAME + " (" +
                    RatingEntry._ID + " INTEGER PRIMARY KEY," +
                    RatingEntry.COLUMN_CUSTOMER_FK + " INTEGER," +
                    RatingEntry.COLUMN_PROPOSAL_FK + " INTEGER," +
                    RatingEntry.COLUMN_NAME_STARS +" INTEGER, "+
                    RatingEntry.COLUMN_NAME_DESCRIPTION +" TEXT, "+
                    RatingEntry.COLUMN_NAME_TITLE +" TEXT, "+
                    RatingEntry.COLUMN_NAME_TIMESTAMP + "TIMESTAMP"+
                    /*
                    Nochmal anschauen
                     */
                    "FOREIGN KEY(" + RatingEntry.COLUMN_CUSTOMER_FK + RatingEntry.COLUMN_PROPOSAL_FK +") REFERENCES " + CustomerEntry.TABLE_NAME + ProposalEntry.TABLE_NAME + "(" + CustomerEntry._ID + ProposalEntry._ID + ")" +
                    ")";
    private static final String SQL_CREATE_TABLE_BOOK =
            "CREATE TABLE " + BookEntry.TABLE_NAME + " (" +
                    BookEntry._ID + " INTEGER PRIMARY KEY," +
                    BookEntry.COLUMN_CUSTOMER_FK + " INTEGER," +
                    BookEntry.COLUMN_PROPOSAL_FK + " INTEGER," +
                    BookEntry.COLUMN_NAME_GUESTS + " INTEGER," +
                    BookEntry.COLUMN_NAME_PERIOD_OF_TIME_FROM + " TEXT,"+
                    BookEntry.COLUMN_NAME_PERIOD_OF_TIME_TO +   " TEXT,"+
                    BookEntry.COLUMN_NAME_PRICE + " DOUBLE, "+
                    BookEntry.COLUMN_NAME_TIMESTAMP +" TIMESTAMP, "+

                    /*
                    Nochmal anschauen
                     */
                    "FOREIGN KEY(" + BookEntry.COLUMN_CUSTOMER_FK + BookEntry.COLUMN_PROPOSAL_FK +") REFERENCES " + CustomerEntry.TABLE_NAME + ProposalEntry.TABLE_NAME + "(" + CustomerEntry._ID + ProposalEntry._ID + ")" +
                    ")";
    private static final String SQL_CREATE_TABLE_PROPOSAL =
            "CREATE TABLE " + ProposalEntry.TABLE_NAME + " (" +
                    ProposalEntry._ID + " INTEGER PRIMARY KEY," +
                    ProposalEntry.COLUMN_PROVIDER_FK + " INTEGER," +
                    ProposalEntry.COLUMN_ADDRESS_FK + " INTEGER," +
                    ProposalEntry.COLUMN_NAME_TYPE_OF_ACCOMMONDATION + "TEXT,"+
                    ProposalEntry.COLUMN_NAME_DESCRIPTION + "TEXT, " +
                    ProposalEntry.COLUMN_NAME_BATHROOM + " TEXT, "+
                    ProposalEntry.COLUMN_NAME_TELEVSION + " TEXT, "+
                    ProposalEntry.COLUMN_NAME_WIFI + " TEXT, "+
                    ProposalEntry.COLUMN_NAME_INTERNET_ACCESS + " TEXT, "+
                    ProposalEntry.COLUMN_NAME_RESTAURANT + " TEXT, "+
                    ProposalEntry.COLUMN_NAME_AIR_CONDITIONING + " TEXT, "+
                    ProposalEntry.COLUMN_NAME_POOL + " TEXT ,"+
                    ProposalEntry.COLUMN_NAME_ROOM1_NAME + " TEXT ," +
                    ProposalEntry.COLUMN_NAME_ROOM1_description +" TEXT, "+
                    ProposalEntry.COLUMN_NAME_ROOM1_price + "DOUBLE ,"+
                    ProposalEntry.COLUMN_NAME_ROOM2_NAME + " TEXT ," +
                    ProposalEntry.COLUMN_NAME_ROOM2_description +" TEXT, "+
                    ProposalEntry.COLUMN_NAME_ROOM2_price + "DOUBLE ,"+
                    ProposalEntry.COLUMN_NAME_ROOM3_NAME + " TEXT ," +
                    ProposalEntry.COLUMN_NAME_ROOM3_description +" TEXT, "+
                    ProposalEntry.COLUMN_NAME_ROOM3_price + "DOUBLE ,"+
                    ProposalEntry.COLUMN_NAME_ROOM4_NAME + " TEXT ," +
                    ProposalEntry.COLUMN_NAME_ROOM4_description +" TEXT, "+
                    ProposalEntry.COLUMN_NAME_ROOM4_price + "DOUBLE ,"+
                    ProposalEntry.COLUMN_NAME_ROOM5_NAME + " TEXT ," +
                    ProposalEntry.COLUMN_NAME_ROOM5_description +" TEXT, "+
                    ProposalEntry.COLUMN_NAME_ROOM5_price + "DOUBLE ,"+
                    ProposalEntry.COLUMN_NAME_ROOM6_NAME + " TEXT ," +
                    ProposalEntry.COLUMN_NAME_ROOM6_description +" TEXT, "+
                    ProposalEntry.COLUMN_NAME_ROOM6_price + "DOUBLE ,"+
                    ProposalEntry.COLUMN_NAME_ROOM7_NAME + " TEXT ," +
                    ProposalEntry.COLUMN_NAME_ROOM7_description +" TEXT, "+
                    ProposalEntry.COLUMN_NAME_ROOM7_price + "DOUBLE ,"+
                    ProposalEntry.COLUMN_NAME_ROOM8_NAME + " TEXT ," +
                    ProposalEntry.COLUMN_NAME_ROOM8_description +" TEXT, "+
                    ProposalEntry.COLUMN_NAME_ROOM8_price + "DOUBLE ,"+
                    ProposalEntry.COLUMN_NAME_ROOM9_NAME + " TEXT ," +
                    ProposalEntry.COLUMN_NAME_ROOM9_description +" TEXT, "+
                    ProposalEntry.COLUMN_NAME_ROOM9_price + "DOUBLE ,"+

                    /*
                    Nochmal anschauen
                     */
                    "FOREIGN KEY(" + ProposalEntry.COLUMN_PROVIDER_FK + ProposalEntry.COLUMN_ADDRESS_FK +") REFERENCES " + ProviderEntry.TABLE_NAME + AddressEntry.TABLE_NAME + "(" + ProviderEntry._ID + AddressEntry._ID + ")" +
                    ")";


    /**
     * Datenbank mit Beispiel Daten füllen
     *
     */
    private static final String SQL_INSERT_TABLE_ADDRESS_BERLIN_1 = "INSERT INTO " + AddressEntry.TABLE_NAME + "VALUES(1, \"Tempelhofer Ufer\", \"14\", \"10963\", \"Berlin\", \"Deutschland\", \"52.498579\",\"13.384430\");";



    private static final String SQL_DROP_TABLE_CUSTOMER = "DROP TABLE IF EXISTS " + CustomerEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_ADDRESS = "DROP TABLE IF EXISTS " + AddressEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_PROVIDER = "DROP TABLE IF EXISTS " + ProviderEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_BOOK= "DROP TABLE IF EXISTS " + BookEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_RATING = "DROP TABLE IF EXISTS " + RatingEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_FAVORITE = "DROP TABLE IF EXISTS " + FavoriteEntry.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_PROPOSAL = "DROP TABLE IF EXISTS " + ProposalEntry.TABLE_NAME + ";";
    private static CityNightsDBHelper instance = null;
    private Context ctx;

    /**
     * Private constructor for CustomerDBHelper to prevent instantiation
     *
     * @param context context
     */
    private CityNightsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    /**
     * Using singleton pattern to retrieve instance of database helper
     *
     * @param ctx context
     * @return new instance of CustomerDBHelper or existing one
     */
    public static CityNightsDBHelper getInstance(Context ctx) {
        if (instance == null) {
            return new CityNightsDBHelper(ctx.getApplicationContext());
        }
        return instance;
    }

    /**
     * Used by Loader to retrieve all Customer from database
     *
     * @return Cursor containing all rows
     */
    public Cursor findAllCustomer() {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String sortOrder = CustomerEntry.COLUMN_NAME_LASTNAME + " ASC";
        qb.setTables(CustomerEntry.TABLE_NAME + " JOIN " + AddressEntry.TABLE_NAME + " ON " + CustomerEntry.TABLE_NAME + "." + CustomerEntry.COLUMN_ADDRESS_FK + "=" + AddressEntry.TABLE_NAME + "." + AddressEntry._ID);
        return qb.query(getReadableDatabase(), null, null, null, null, null, sortOrder);
    }

    public Cursor findCustomerByName(String name) {
        String whereClause = CustomerEntry.COLUMN_NAME_FIRSTNAME + " LIKE ? OR " + CustomerEntry.COLUMN_NAME_LASTNAME + " LIKE ?";
        String[] whereArgs = new String[]{name + "%", name + "%"};
        String sortOrder = CustomerEntry.COLUMN_NAME_LASTNAME + " ASC";
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(CustomerEntry.TABLE_NAME + " JOIN " + AddressEntry.TABLE_NAME + " ON " + CustomerEntry.TABLE_NAME + "." + CustomerEntry.COLUMN_ADDRESS_FK + "=" + AddressEntry.TABLE_NAME + "." + AddressEntry._ID);
        return qb.query(getReadableDatabase(),null,whereClause, whereArgs, null, null, sortOrder);
    }


    /** GetAddresses
     *
     */

    public Address findAddressByCityname(String city) {
        String query = "Select * FROM " + AddressEntry.TABLE_NAME + " WHERE " + AddressEntry.COLUMN_NAME_CITY + " =  \"" + city + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Address address = new Address();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            address.setId(Integer.parseInt(cursor.getString(0)));
            address.setStreet(cursor.getString(1));
            address.setNumber(cursor.getString(2));
            address.setZipCode(cursor.getString(3));
            address.setCity(cursor.getString(4));
            address.setCountry(cursor.getString(5));
            address.setXCoord(cursor.getString(6));
            address.setYCoord(cursor.getString(7));
            cursor.close();
        } else {
            address = null;
        }
        db.close();
        return address;
    }

    /**
     * Inserts customer into SQLite database
     *
     * @param customer the customer which should be persisted
     * @return inserted customer or null if an error occurred
     */
    public Customer insertcustomer(Customer customer) {

        Address address = insertAddress(customer.getAddress());
        if (address == null) {
            return null;
        }

        customer.setAddress(address);

        ContentValues values = getcustomerValues(customer);
        values.put(CustomerEntry.COLUMN_ADDRESS_FK, customer.getAddress().getId());

        long customerId = getWritableDatabase().insert(CustomerEntry.TABLE_NAME, null, values);
        if (customerId == -1) {
            return null;
        }

        customer.setId(customerId);
        return customer;
    }

    /**
     * Inserts Address into SQLite database
     *
     * @param address the Adress which should be persisted
     * @return inserted Address or null if an error occurred
     */
    private Address insertAddress(Address address) {
        ContentValues values = getAddressValues(address);

        long id = getWritableDatabase().insert(AddressEntry.TABLE_NAME, null, values);
        if (id == -1) {
            return null;
        }
        address.setId(id);
        return address;
    }

    /**
     * Updates given customer in SQLite database
     *
     * @param customer the customer which should be updated
     * @return number of rows affected
     */
    public int updatecustomer(Customer customer) {

        int affected = updateAddress(customer.getAddress());
        if (affected != 1) {
            return affected;
        }

        String whereClause = CustomerEntry._ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(customer.getId())};

        ContentValues values = getcustomerValues(customer);
        values.put(CustomerEntry.COLUMN_ADDRESS_FK, customer.getAddress().getId());
        Log.d("CustomerDBHelper", "updatecustomer: " + customer.toString());

        return getWritableDatabase().update(CustomerEntry.TABLE_NAME, values, whereClause, whereArgs);
    }

    /**
     * Updates given Address in SQLite database
     *
     * @param address the Address which should be updated
     * @return number of rows affected
     */
    private int updateAddress(Address address) {
        String whereClause = AddressEntry._ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(address.getId())};

        ContentValues values = getAddressValues(address);
        Log.d("CustomerDBHelper", "updateAddress: " + address.toString());

        return getWritableDatabase().update(AddressEntry.TABLE_NAME, values, whereClause, whereArgs);
    }

    /**
     * Deletes given customer from SQLite database
     *
     * @param customer the customer which should be deleted
     * @return number of rows affected
     */
    public int deletecustomer(Customer customer) {
        String whereClause = CustomerEntry._ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(customer.getId())};

        int affected = deleteAddress(customer.getAddress());
        if (affected != 1) {
            return affected;
        }

        return getWritableDatabase().delete(CustomerEntry.TABLE_NAME, whereClause, whereArgs);
    }

    /**
     * Deletes given Address from SQLite database
     *
     * @param address the Address which should be deleted
     * @return number of rows affected
     */
    private int deleteAddress(Address address) {
        String whereClause = AddressEntry._ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(address.getId())};

        return getWritableDatabase().delete(AddressEntry.TABLE_NAME, whereClause, whereArgs);
    }

    /**
     * Helper method which creates ContentValues for customer
     *
     * @param customer the customer
     * @return ContentValues including all database columns
     */
    private ContentValues getcustomerValues(Customer customer) {
        ContentValues values = new ContentValues();
        values.put(CustomerEntry.COLUMN_NAME_FIRSTNAME, customer.getFirstName());
        values.put(CustomerEntry.COLUMN_NAME_LASTNAME, customer.getLastName());
        values.put(CustomerEntry.COLUMN_NAME_MAIL, customer.getMail());
        values.put(CustomerEntry.COLUMN_NAME_PHONE, customer.getPhone());

        return values;
    }

    /**
     * Helper method which creates ContentValues for Address
     *
     * @param address the Address
     * @return ContentValues including all database columns
     */
    private ContentValues getAddressValues(Address address) {
        ContentValues values = new ContentValues();
        values.put(AddressEntry.COLUMN_NAME_STREET, address.getStreet());
        values.put(AddressEntry.COLUMN_NAME_NUMBER, address.getNumber());
        values.put(AddressEntry.COLUMN_NAME_ZIPCODE, address.getZipCode());
        values.put(AddressEntry.COLUMN_NAME_CITY, address.getCity());
        values.put(AddressEntry.COLUMN_NAME_COUNTRY, address.getCountry());
        values.put(AddressEntry.COLUMN_NAME_X_COORD, address.getXCoord());
        values.put(AddressEntry.COLUMN_NAME_Y_COORD, address.getYcoord());
        return values;
    }

    /**
     * Called when database gets created for first time and used to create tables
     *
     * @param db the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_ADDRESS);
        db.execSQL(SQL_CREATE_TABLE_CUSTOMER);
        db.execSQL(SQL_CREATE_TABLE_PROVIDER);
        db.execSQL(SQL_CREATE_TABLE_PROPOSAL);
        db.execSQL(SQL_CREATE_TABLE_BOOK);
        db.execSQL(SQL_CREATE_TABLE_FAVORITE);
        db.execSQL(SQL_CREATE_TABLE_RATING);
        db.execSQL(SQL_INSERT_TABLE_ADDRESS_BERLIN_1);

    }

    /**
     * Called when database version gets upgraded or tables get changed
     *
     * @param db         the database
     * @param oldVersion old database version number
     * @param newVersion new database version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE_CUSTOMER);
        db.execSQL(SQL_DROP_TABLE_ADDRESS);
        db.execSQL(SQL_DROP_TABLE_PROVIDER);
        db.execSQL(SQL_DROP_TABLE_PROPOSAL);
        db.execSQL(SQL_DROP_TABLE_BOOK);
        db.execSQL(SQL_DROP_TABLE_FAVORITE);
        db.execSQL(SQL_DROP_TABLE_RATING);
        onCreate(db);
    }

    /**
     * Called when database version gets downgraded
     *
     * @param db         the database
     * @param oldVersion old database version number
     * @param newVersion new database version number
     */
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}