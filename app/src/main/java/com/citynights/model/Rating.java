package com.citynights.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dominik on 02.12.2015.
 */
public class Rating implements Parcelable {

    public Rating(){

    }



    private Long id;
    private Proposal proposal;
    private Customer customer;
    private int stars;
    private String headline;
    private String description;
    private String timestamp;
    public Rating (Proposal proposal, Customer customer, int stars, String headline, String description, String timestamp){

        this.customer=customer;
        this.proposal=proposal;
        this.stars=stars;
        this.headline=headline;
        this.description=description;
        this.timestamp=timestamp;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }




    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", proposal'" + proposal+ '\'' +
                ", customer='" + customer + '\'' +
                ", stars='" + stars + '\'' +
                ", headline='" + headline + '\'' +
                ", description=" + description +
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
        dest.writeParcelable(customer, flags);
        dest.writeParcelable(proposal, flags);
        dest.writeInt(stars);
        dest.writeString(headline);
        dest.writeString(description);
        dest.writeString(timestamp);

    }

    /**
     * CREATOR field to generate instance of object from Parcel
     */
    public static final Parcelable.Creator<Rating> CREATOR
            = new Parcelable.Creator<Rating>() {
        /**
         * Creates new instance of Customer from Parcel
         * @param in the parcel
         * @return new instance of Customer
         */
        public Rating createFromParcel(Parcel in) {
            return new Rating(in);
        }

        /**
         * Creates new Array of Customers from Parcel
         * @param size size of the array
         * @return Array with new instances of Customers
         */
        public Rating[] newArray(int size) {
            return new Rating [size];
        }
    };

    /**
     * Private constructor for Customer used by CREATOR to deserialize Parcel
     *
     * @param in Parcel to deserialize
     */
    private Rating (Parcel in) {
        id = in.readLong();
        customer = in.readParcelable(Customer.class.getClassLoader());
        proposal = in.readParcelable(Proposal.class.getClassLoader());
        stars = in.readInt();
        headline = in.readString();
        description = in.readString();
        timestamp = in.readString();
    }
}
