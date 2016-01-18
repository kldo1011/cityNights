package com.citynights.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.citynights.model.Proposal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by morih on 10.01.2016.
 */
public class ProposalDataSource {

    private static final String LOG_TAG = ProposalDataSource.class.getSimpleName();

    private final String findbycityname =   "Select p.* from " +
                                            DatabaseSchema.ProposalEntry.TABLE_NAME +
                                            " p join " + DatabaseSchema.AddressEntry.TABLE_NAME + " a on p." + DatabaseSchema.ProposalEntry.COLUMN_ADDRESS_FK + " = a." + DatabaseSchema.AddressEntry._ID +
                                            " where a." + DatabaseSchema.AddressEntry.COLUMN_NAME_CITY + " like '%";
    private SQLiteDatabase database;
    private CityNightsDBHelper dbHelper;

    private String[] proposalcolumns = {
            DatabaseSchema.ProposalEntry._ID,
            DatabaseSchema.ProposalEntry.COLUMN_PROVIDER_FK,
            DatabaseSchema.ProposalEntry.COLUMN_ADDRESS_FK,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_NAME,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_TYPE_OF_ACCOMMONDATION ,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_DESCRIPTION,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_BATHROOM,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_TELEVSION,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_WIFI,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_INTERNET_ACCESS,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_RESTAURANT,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_BAR,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_AIR_CONDITIONING,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_POOL,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM1_NAME,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM1_description,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM1_price,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM2_NAME,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM2_description,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM2_price,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM3_NAME,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM3_description,
            DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM3_price};

    public ProposalDataSource(Context context) {
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


        private Proposal cursorToProposal(Cursor cursor) {

        //Spalte ID wird zum erzeugen des Proposals nicht benötigt (ist auch -1 --> Cursor funktioniert nicht)
        //int idIndex = cursor.getColumnIndex(DatabaseSchema.ProposalEntry._ID);
        //Log.d(LOG_TAG, "ID:"+ idIndex);
        int idProvider = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_PROVIDER_FK);
        int idAddress = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_ADDRESS_FK);
        int idName = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_NAME);
        int idType = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_TYPE_OF_ACCOMMONDATION);
        int idDescription = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_DESCRIPTION);
        int idBathroom = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_BATHROOM);
        int idTelevision = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_TELEVSION);
        int idWifi = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_WIFI);
        int idInternet = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_INTERNET_ACCESS);
        int idRestaurant = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_RESTAURANT);
        int idBar = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_BAR);
        int idAirC = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_AIR_CONDITIONING);
        int idPool = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_POOL);
        int idRoomname1 = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM1_NAME);
        int idRoomdescr1 = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM1_description);
        int idRoomprice1 = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM1_price);
        int idRoomname2 = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM2_NAME);
        int idRoomdesc2 = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM2_description);
        int idRoomprice2 = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM2_price);
        int idRoomname3 = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM3_NAME);
        int idRoomdesc3 = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM3_description);
        int idRoomprice3 = cursor.getColumnIndex(DatabaseSchema.ProposalEntry.COLUMN_NAME_ROOM3_price);


        Integer provider = cursor.getInt(idProvider);
        Integer address = cursor.getInt(idAddress);
        String name = cursor.getString(idName);
        String type = cursor.getString(idType);
        String description = cursor.getString(idDescription);
        String bathroom = cursor.getString(idBathroom);
        String tv = cursor.getString(idTelevision);
        String wifi = cursor.getString(idWifi);
        String internet = cursor.getString(idInternet);
        String restaurant = cursor.getString(idRestaurant);
        String bar = cursor.getString(idBar);
        String airc = cursor.getString(idAirC);
        String pool = cursor.getString(idPool);
        String roomname1 = cursor.getString(idRoomname1);
        String roomdesc1 = cursor.getString(idRoomdescr1);
        Double roomprice1 = cursor.getDouble(idRoomprice1);
        String roomname2 = cursor.getString(idRoomname2);
        String roomdesc2 = cursor.getString(idRoomdesc2);
        Double roomprice2 = cursor.getDouble(idRoomprice2);
        String roomname3 = cursor.getString(idRoomname3);
        String roomdesc3 = cursor.getString(idRoomdesc3);
        Double roomprice3 = cursor.getDouble(idRoomprice3);

        //long id = cursor.getLong(idIndex);
        //Log.d(LOG_TAG, "ID:"+id);

        Proposal proposal = new Proposal(provider, address, name, type, description, bathroom, tv, wifi, internet,restaurant, bar, airc, pool, roomname1, roomdesc1, roomprice1, roomname2, roomdesc2, roomprice2, roomname3, roomdesc3, roomprice3);

        return proposal;
    }

    public List<Proposal> getAllProposals() {
        List<Proposal> proposalList = new ArrayList<>();

        Cursor cursor = database.query(DatabaseSchema.ProposalEntry.TABLE_NAME,
                proposalcolumns, null, null, null, null, null);

        cursor.moveToFirst();
        Proposal proposal;

        while(!cursor.isAfterLast()) {
            proposal = cursorToProposal(cursor);
            proposalList.add(proposal);
            Log.d(LOG_TAG, "ID: " + proposal.getId() + ", Inhalt: " + proposal.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return proposalList;
    }


    public List<Proposal> findProposal(String query) {


        List<Proposal> proposalList = new ArrayList<>();

        Cursor cursor = database.rawQuery(findbycityname + query + ";", null);
        //Cursor cursor = database.query(DatabaseSchema.ProposalEntry.TABLE_NAME,
                //proposalcolumns, DatabaseSchema.ProposalEntry.COLUMN_NAME_CITY + "=" + query, null, null, null, null);


        cursor.moveToFirst();
        Proposal proposal;

        while(!cursor.isAfterLast()) {
            proposal = cursorToProposal(cursor);
            proposalList.add(proposal);
            Log.d(LOG_TAG, "ID: " + proposal.getId() + ", Inhalt: " + proposal.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return proposalList;
    }

    public Cursor searchByInputText(String inputText) {


        Cursor cursor = database.rawQuery(findbycityname + inputText + "%';", null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;

    }


}
