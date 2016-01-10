package com.citynights.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dominik on 02.12.2015.
 */
public class Proposal  implements Parcelable {

    public Proposal() {

    }


    public Proposal(Provider provider, Address address, String accommodationtype, String description, String bath, String tv, String WLAN, String internetaccess, String restaurant, String bar, String aircondition, String pool, String room1name, String room1descrition, double room1price, String room2name, String room2descrition, double room2price,String room3name, String room3descrition, double room3price) {
        this.provider = provider;
        this.address = address;
        this.accommodationtype = accommodationtype;
        this.description = description;
        this.bath = bath;
        this.tv= tv;
        this.WLAN = WLAN;
        this.internetaccess = internetaccess;
        this.restaurant = restaurant;
        this.bar = bar;
        this.aircondition = aircondition;
        this.pool = pool;
        this.room1name= room1name;
        this.room1descrition = room1descrition;
        this.room1price= room1price;
        this.room2name= room2name;
        this.room2descrition = room2descrition;
        this.room2price= room2price;
        this.room3name= room3name;
        this.room3descrition = room3descrition;
        this.room3price= room3price;
    }

    private long id;
    private Provider provider;
    private Address address;
    private String accommodationtype;
    private String description;
    private String bath;
    private String tv;
    private String WLAN;
    private String internetaccess;
    private String restaurant;
    private String bar;
    private String aircondition;



    private String pool;
    private String room1name;
    private String room1descrition;
    private double room1price;
    private String room2name;
    private String room2descrition;
    private double room2price;
    private String room3name;
    private String room3descrition;
    private double room3price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAccommodationtype() {
        return accommodationtype;
    }

    public void setAccommodationtype(String accommodationtype) {
        this.accommodationtype = accommodationtype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bath) {
        this.bath = bath;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getWLAN() {
        return WLAN;
    }

    public void setWLAN(String WLAN) {
        this.WLAN = WLAN;
    }

    public String getInternetaccess() {
        return internetaccess;
    }

    public void setInternetaccess(String internetaccess) {
        this.internetaccess = internetaccess;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public String getAircondition() {
        return aircondition;
    }

    public void setAircondition(String aircondition) {
        this.aircondition = aircondition;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public String getRoom1name() {
        return room1name;
    }

    public void setRoom1name(String room1name) {
        this.room1name = room1name;
    }

    public String getRoom1descrition() {
        return room1descrition;
    }

    public void setRoom1descrition(String room1descrition) {
        this.room1descrition = room1descrition;
    }

    public double getRoom1price() {
        return room1price;
    }

    public void setRoom1price(double room1price) {
        this.room1price = room1price;
    }

    public String getRoom2name() {
        return room2name;
    }

    public void setRoom2name(String room2name) {
        this.room2name = room2name;
    }

    public String getRoom2descrition() {
        return room2descrition;
    }

    public void setRoom2descrition(String room2descrition) {
        this.room2descrition = room2descrition;
    }

    public double getRoom2price() {
        return room2price;
    }

    public void setRoom2price(double room2price) {
        this.room2price = room2price;
    }

    public String getRoom3name() {
        return room3name;
    }

    public void setRoom3name(String room3name) {
        this.room3name = room3name;
    }

    public String getRoom3descrition() {
        return room3descrition;
    }

    public void setRoom3descrition(String room3descrition) {
        this.room3descrition = room3descrition;
    }

    public double getRoom3price() {
        return room3price;
    }

    public void setRoom3price(double room3price) {
        this.room3price = room3price;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", provider='" + provider + '\'' +
                ", address='" + address + '\'' +
                ", accommodationtype='" + accommodationtype + '\'' +
                ", bath='" + bath + '\'' +
                ", tv='" + tv+ '\'' +
                ", WLAN='" + WLAN + '\'' +
                ", internetaccess='" + internetaccess + '\'' +
                ", restaurant ='" + restaurant + '\'' +
                ", bar='" + bar + '\'' +
                ", aircondition='" + aircondition + '\'' +
                ", pool='" + pool + '\'' +
                ", room1name='" + room1name + '\'' +
                ", room1description='" + room1descrition + '\'' +
                ", room1price='" + room1price + '\'' +
                ", room2name='" + room2name + '\'' +
                ", room2description='" + room2descrition + '\'' +
                ", room2price='" + room2price + '\'' +
                ", room3name='" + room3name + '\'' +
                ", room3description='" + room3descrition + '\'' +
                ", room3price=" + room3price +
                '}';
    }

    /**
     * Describes the content of the Parcel
     *
     * @return bitmask
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Defines how to serialize the object into Parcel
     *
     * @param dest  Parcel in which the object should be stored
     * @param flags additional flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeParcelable(provider, flags);
        dest.writeParcelable(address, flags);
        dest.writeString(accommodationtype);
        dest.writeString(description);
        dest.writeString(bath);
        dest.writeString(tv);
        dest.writeString(WLAN);
        dest.writeString(internetaccess);
        dest.writeString(restaurant);
        dest.writeString(bar);
        dest.writeString(aircondition);
        dest.writeString(pool);
        dest.writeString(room1name);
        dest.writeString(room1descrition);
        dest.writeDouble(room1price);
        dest.writeString(room2name);
        dest.writeString(room2descrition);
        dest.writeDouble(room2price);
        dest.writeString(room3name);
        dest.writeString(room3descrition);
        dest.writeDouble(room3price);
    }

    /**
     * CREATOR field to generate instance of object from Parcel
     */
    public static final Parcelable.Creator<Proposal> CREATOR
            = new Parcelable.Creator<Proposal>() {
        /**
         * Creates new instance of Customer from Parcel
         * @param in the parcel
         * @return new instance of Customer
         */
        public Proposal createFromParcel(Parcel in) {
            return new Proposal(in);
        }

        /**
         * Creates new Array of Customers from Parcel
         * @param size size of the array
         * @return Array with new instances of Customers
         */
        public Proposal[] newArray(int size) {
            return new Proposal[size];
        }
    };

    /**
     * Private constructor for Customer used by CREATOR to deserialize Parcel
     *
     * @param in Parcel to deserialize
     */
    private Proposal(Parcel in) {
        id = in.readLong();
        provider = in.readParcelable(Provider.class.getClassLoader());
        address = in.readParcelable(Address.class.getClassLoader());
        accommodationtype = in.readString();
        description = in.readString();
        bath = in.readString();
        tv = in.readString();
        WLAN = in.readString();
        internetaccess= in.readString();
        restaurant = in.readString();
        bar = in.readString();
        aircondition = in.readString();
        pool = in.readString();
        room1name = in.readString();
        room1descrition = in.readString();
        room1price = in.readDouble();
        room2name = in.readString();
        room2descrition = in.readString();
        room2price = in.readDouble();
        room3name = in.readString();
        room3descrition = in.readString();
        room3price = in.readDouble();
    }
}

