package com.citynights.dao;

import android.provider.BaseColumns;

/**
 * Created by Dominik on 02.12.2015.
 */
public final class DatabaseSchema {


    /**
     * Private constructor for DatabaseSchema to prevent instantiation
     */
    private DatabaseSchema() {

    }

    /**
     * Defines the columns for customer table
     */
    public static abstract class CustomerEntry implements BaseColumns {
        public static final String TABLE_NAME = "customer";
        public static final String COLUMN_NAME_FIRSTNAME = "first_name";
        public static final String COLUMN_NAME_LASTNAME = "last_name";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_MAIL = "mail";
        public static final String COLUMN_ADDRESS_FK = "address_fk";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_NEWSLETTER = "newsletter";
    }

    /**
     * Defines the columns for address table
     */
    public static abstract class AddressEntry implements BaseColumns {
        public static final String TABLE_NAME = "address";
        public static final String COLUMN_NAME_STREET = "street";
        public static final String COLUMN_NAME_NUMBER = "number";
        public static final String COLUMN_NAME_ZIPCODE = "zip_code";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_COUNTRY = "country";
        // X und Y Koordinaten für Google Maps
        public static final String COLUMN_NAME_X_COORD = "xcoord";
        public static final String COLUMN_NAME_Y_COORD = "ycoord";
    }


    public static abstract class BookEntry implements BaseColumns {
        public static final String TABLE_NAME = "book";
        public static final String COLUMN_CUSTOMER_FK = "customer_fk";
        public static final String COLUMN_PROPOSAL_FK = "proposal_fk";
        public static final String COLUMN_NAME_GUESTS = "guests";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_PERIOD_OF_TIME_FROM = "period_of_time_from";
        public static final String COLUMN_NAME_PERIOD_OF_TIME_TO = "period_of_time_to";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";


    }

    public static abstract class ProposalEntry implements BaseColumns {

        public static final String TABLE_NAME = "proposal";
        public static final String COLUMN_PROVIDER_FK = "provider_fk";
        public static final String COLUMN_ADDRESS_FK = "address_fk";
        public static final String COLUMN_NAME_TYPE_OF_ACCOMMONDATION = "type_of_accomondation";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_BATHROOM = "bathroom";
        public static final String COLUMN_NAME_TELEVSION = "television";
        public static final String COLUMN_NAME_WIFI = "wifi";
        public static final String COLUMN_NAME_INTERNET_ACCESS = "internet_access";
        public static final String COLUMN_NAME_RESTAURANT = "restaurant";
        public static final String COLUMN_NAME_BAR = "bar";
        public static final String COLUMN_NAME_AIR_CONDITIONING = "air_conditioning";
        public static final String COLUMN_NAME_POOL = "pool";
        public static final String COLUMN_NAME_ROOM1_NAME = "room1_name";
        public static final String COLUMN_NAME_ROOM1_description = "room1_description";
        public static final String COLUMN_NAME_ROOM1_price = "room1_price";
        public static final String COLUMN_NAME_ROOM2_NAME = "room2_name";
        public static final String COLUMN_NAME_ROOM2_description = "room2_description";
        public static final String COLUMN_NAME_ROOM2_price = "room2_price";
        public static final String COLUMN_NAME_ROOM3_NAME = "room3_name";
        public static final String COLUMN_NAME_ROOM3_description = "room3_description";
        public static final String COLUMN_NAME_ROOM3_price = "room3_price";
        public static final String COLUMN_NAME_ROOM4_NAME = "room4_name";
        public static final String COLUMN_NAME_ROOM4_description = "room4_description";
        public static final String COLUMN_NAME_ROOM4_price = "room4_price";
        public static final String COLUMN_NAME_ROOM5_NAME = "room5_name";
        public static final String COLUMN_NAME_ROOM5_description = "room5_description";
        public static final String COLUMN_NAME_ROOM5_price = "room5_price";
        public static final String COLUMN_NAME_ROOM6_NAME = "room6_name";
        public static final String COLUMN_NAME_ROOM6_description = "room6_description";
        public static final String COLUMN_NAME_ROOM6_price = "room6_price";
        public static final String COLUMN_NAME_ROOM7_NAME = "room7_name";
        public static final String COLUMN_NAME_ROOM7_description = "room7_description";
        public static final String COLUMN_NAME_ROOM7_price = "room7_price";
        public static final String COLUMN_NAME_ROOM8_NAME = "room8_name";
        public static final String COLUMN_NAME_ROOM8_description = "room8_description";
        public static final String COLUMN_NAME_ROOM8_price = "room8_price";
        public static final String COLUMN_NAME_ROOM9_NAME = "room9_name";
        public static final String COLUMN_NAME_ROOM9_description = "room9_description";
        public static final String COLUMN_NAME_ROOM9_price = "room9_price";

    }

    public static abstract class ProviderEntry implements BaseColumns {
        public static final String TABLE_NAME = " provider";
        public static final String COLUMN_NAME_FIRSTNAME = "first_name";
        public static final String COLUMN_NAME_LASTNAME = "last_name";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_MAIL = "mail";
        public static final String COLUMN_ADDRESS_FK = "address_fk";
        public static final String COLUMN_NAME_PASSWORD = "password";

    }

    public static abstract class RatingEntry implements BaseColumns {
        public static final String TABLE_NAME = "rating";
        public static final String COLUMN_PROPOSAL_FK = "proposal_fk";
        public static final String COLUMN_CUSTOMER_FK = "customer_fk";
        public static final String COLUMN_NAME_STARS = "stars";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";


    }

    public static abstract class FavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_CUSTOMER_FK = "customer_fk";
        public static final String COLUMN_PROPOSAL_FK = "proposal_fk";

    }


}