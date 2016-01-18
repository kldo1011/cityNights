package com.citynights.dao;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import com.citynights.model.Customer;
        import com.citynights.model.Proposal;

        import java.util.ArrayList;
        import java.util.List;


public class LoginDataBaseAdapter
{
    private SQLiteDatabase database;
    private CityNightsDBHelper dbHelper;
    private static final String LOG_TAG = LoginDataBaseAdapter.class.getSimpleName();

    private String[] customercolumns = {
            DatabaseSchema.CustomerEntry._ID,
            DatabaseSchema.CustomerEntry.COLUMN_NAME_USERNAME,
            DatabaseSchema.CustomerEntry.COLUMN_NAME_PASSWORD,
            DatabaseSchema.CustomerEntry.COLUMN_NAME_FIRSTNAME,
            DatabaseSchema.CustomerEntry.COLUMN_NAME_LASTNAME,
            DatabaseSchema.CustomerEntry.COLUMN_NAME_PHONE,
            DatabaseSchema.CustomerEntry.COLUMN_NAME_MAIL,
            DatabaseSchema.CustomerEntry.COLUMN_ADDRESS_FK,
            DatabaseSchema.CustomerEntry.COLUMN_NAME_NEWSLETTER};

    public LoginDataBaseAdapter(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new CityNightsDBHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }




    private Customer cursorToCustomer(Cursor cursor) {

        //Spalte ID wird zum erzeugen des Customers nicht benötigt (ist auch -1 --> Cursor funktioniert nicht)
        //int idIndex = cursor.getColumnIndex(DatabaseSchema.CustomerEntry._ID);
        //Log.d(LOG_TAG, "ID:"+ idIndex);
        int idUsername = cursor.getColumnIndex(DatabaseSchema.CustomerEntry.COLUMN_NAME_USERNAME);
        int idPassword = cursor.getColumnIndex(DatabaseSchema.CustomerEntry.COLUMN_NAME_PASSWORD);
        int idFirstName = cursor.getColumnIndex(DatabaseSchema.CustomerEntry.COLUMN_NAME_FIRSTNAME);
        int idLastName = cursor.getColumnIndex(DatabaseSchema.CustomerEntry.COLUMN_NAME_LASTNAME);
        int idPhone = cursor.getColumnIndex(DatabaseSchema.CustomerEntry.COLUMN_NAME_PHONE);
        int idMail = cursor.getColumnIndex(DatabaseSchema.CustomerEntry.COLUMN_NAME_MAIL);
        int idAddress = cursor.getColumnIndex(DatabaseSchema.CustomerEntry.COLUMN_ADDRESS_FK);
        int idNewsletter = cursor.getColumnIndex(DatabaseSchema.CustomerEntry.COLUMN_NAME_NEWSLETTER);



        String username = cursor.getString(idUsername);
        String password = cursor.getString(idPassword);
        String firstname = cursor.getString(idFirstName);
        String lastname = cursor.getString(idLastName);
        String phone = cursor.getString(idPhone);
        String mail = cursor.getString(idMail);
        Integer address = cursor.getInt(idAddress);
        String newsletter = cursor.getString(idNewsletter);

        //long id = cursor.getLong(idIndex);
        //Log.d(LOG_TAG, "ID:"+id);

        Customer customer = new Customer(username, password, firstname, lastname, phone, mail, address, newsletter);

        return customer;
    }



            public void insertEntry(String userName,String password)
            {
                ContentValues newValues = new ContentValues();
                // Assign values for each row.
                newValues.put(DatabaseSchema.CustomerEntry.COLUMN_NAME_USERNAME, userName);
                newValues.put(DatabaseSchema.CustomerEntry.COLUMN_NAME_PASSWORD, password);

                // Insert the row into your table
                database.insert(DatabaseSchema.CustomerEntry.TABLE_NAME, null, newValues);
                ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
            }
            public int deleteEntry(String UserName)
            {
                //String id=String.valueOf(ID);
                String where="username=?";
                int numberOFEntriesDeleted= database.delete(DatabaseSchema.CustomerEntry.TABLE_NAME, where, new String[]{UserName}) ;
                // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
                return numberOFEntriesDeleted;
            }
            public String getSinlgeEntry(String userName)
            {
                Cursor cursor = database.query(DatabaseSchema.CustomerEntry.TABLE_NAME, null, " username=?", new String[]{userName}, null, null, null);
                Log.d(LOG_TAG, "USERNAME = " + userName);
                List<Customer> clist = getAllCustomers();
                //Cursor cursor = database.rawQuery("Select * from customers where username=?", new String[]{userName});
                if(cursor.getCount()<1) // UserName Not Exist
                {
                    cursor.close();
                    return "NOT EXIST";
                }
                cursor.moveToFirst();
                String password = cursor.getString(2);
                Log.d(LOG_TAG, "PASSWORD = " + password);
                cursor.close();
                return password;
            }
            public void  updateEntry(String userName,String password)
            {
                // Define the updated row content.
                ContentValues updatedValues = new ContentValues();
                // Assign values for each row.
                updatedValues.put(DatabaseSchema.CustomerEntry.COLUMN_NAME_USERNAME, userName);
                updatedValues.put(DatabaseSchema.CustomerEntry.COLUMN_NAME_PASSWORD, password);

                String where="username = ?";
                database.update(DatabaseSchema.CustomerEntry.TABLE_NAME, updatedValues, where, new String[]{userName});
            }


    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();

        Cursor cursor = database.query(DatabaseSchema.CustomerEntry.TABLE_NAME,
                customercolumns, null, null, null, null, null);

        cursor.moveToFirst();
        Customer customer;

        while(!cursor.isAfterLast()) {
            customer = cursorToCustomer(cursor);
            customerList.add(customer);
            Log.d(LOG_TAG, "ID: " + customer.getId() + ", Inhalt: " + customer.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return customerList;
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }
        }