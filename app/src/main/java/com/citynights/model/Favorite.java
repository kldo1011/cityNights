package com.citynights.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dominik on 02.12.2015.
 */
public class Favorite implements Parcelable {

    public Favorite(){

    }

    private Long id;
    private Customer customer;
    private Proposal proposal;

    public Favorite(Long id, Customer customer, Proposal proposal){

        this.customer=customer;
        this.proposal=proposal;
    }


    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", proposal'" + proposal+ '\'' +
                ", customer='" + customer + '\'' +
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
    }

    /**
     * CREATOR field to generate instance of object from Parcel
     */
    public static final Parcelable.Creator<Favorite> CREATOR
            = new Parcelable.Creator<Favorite>() {
        /**
         * Creates new instance of Customer from Parcel
         * @param in the parcel
         * @return new instance of Customer
         */
        public Favorite createFromParcel(Parcel in) {
            return new Favorite(in);
        }

        /**
         * Creates new Array of Customers from Parcel
         * @param size size of the array
         * @return Array with new instances of Customers
         */
        public Favorite[] newArray(int size) {
            return new Favorite [size];
        }
    };

    /**
     * Private constructor for Customer used by CREATOR to deserialize Parcel
     *
     * @param in Parcel to deserialize
     */
    private Favorite (Parcel in) {
        id = in.readLong();
        customer = in.readParcelable(Customer.class.getClassLoader());
        proposal = in.readParcelable(Proposal.class.getClassLoader());

    }
}






