package com.citynights.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dominik on 02.12.2015.
 */
public class Book implements Parcelable  {

    public Book () {

    }


    /*
        TODO Variablentypen anpassen
         */
    private long id;
    private Integer customer;
    private Integer proposal;
    private Integer guestnumbers;
    private double price;
    private String timefrom;
    private String timeto;
    private String timestamp;

    public Book(Integer customer, Integer proposal,int guestnumbers, double price,String timefrom, String timeto,String timestamp)
    {

        this.customer=customer;
        this.guestnumbers=guestnumbers;
        this.price=price;
        this.proposal=proposal;
        this.timefrom=timefrom;
        this.timeto=timeto;
        this.timestamp=timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public Integer getProposal() {
        return proposal;
    }

    public void setProposal(Integer proposal) {
        this.proposal = proposal;
    }

    public int getGuestnumbers() {
        return guestnumbers;
    }

    public void setGuestnumbers(int guestnumbers) {
        this.guestnumbers = guestnumbers;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTimefrom() {
        return timefrom;
    }

    public void setTimefrom(String timefrom) {
        this.timefrom = timefrom;
    }

    public String getTimeto() {
        return timeto;
    }

    public void setTimeto(String timeto) {
        this.timeto = timeto;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", customer='" + customer+ '\'' +
                ", proposal='" + proposal + '\'' +
                ", guestsnumber='" + guestnumbers + '\'' +
                ", price='" + price + '\'' +
                ", timefrom=" + timefrom +
                ", timeto=" + timeto +
                ", timestamp=" + timestamp +
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
        dest.writeInt(customer);
        dest.writeInt(proposal);
        dest.writeInt(guestnumbers);
        dest.writeDouble(price);
        dest.writeString(timefrom);
        dest.writeString(timeto);
        dest.writeString(timestamp);
    }

    /**
     * CREATOR field to generate instance of object from Parcel
     */
    public static final Parcelable.Creator<Book> CREATOR
            = new Parcelable.Creator<Book>() {
        /**
         * Creates new instance of Customer from Parcel
         * @param in the parcel
         * @return new instance of Customer
         */
        public Book createFromParcel(Parcel in) {
            return new Book (in);
        }

        /**
         * Creates new Array of Customers from Parcel
         * @param size size of the array
         * @return Array with new instances of Customers
         */
        public Book [] newArray(int size) {
            return new Book [size];
        }
    };

    /**
     * Private constructor for Customer used by CREATOR to deserialize Parcel
     *
     * @param in Parcel to deserialize
     */
    private Book (Parcel in) {
        id = in.readLong();
        customer = in.readInt();
        proposal = in.readInt();
        guestnumbers= in.readInt();
        price = in.readDouble();
        timefrom = in.readString();
        timeto = in.readString();
        timestamp = in.readString();

    }
}



