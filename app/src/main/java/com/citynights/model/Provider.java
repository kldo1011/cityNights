package com.citynights.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dominik on 02.12.2015.
 */
public class Provider implements Parcelable{



    /**
     * Empty default constructor for Customer
     */
    public Provider() {

    }

    /**
     * Constructor for Customer
     *
     * @param firstname first name
     * @param lastname  last name
     * @param phone     phone number
     * @param mail      mail address
     * @param address   address of the Customer
     */
    public Provider(Address address, String mail, String password, String firstname, String lastname, String firmname, int phone) {
        this.address = address;
        this.mail = mail;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.firmname = firmname;
        this.phone = phone;


    }



    private long id;
    private Address address;
    private String mail;
    private String password;
    private String firstname;
    private String lastname;
    private String firmname;
    private int phone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirmname() {
        return firmname;
    }

    public void setFirmname(String firmname) {
        this.firmname = firmname;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firmname='" + firmname + '\'' +
                ", phone=" + phone +
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
        dest.writeParcelable(address, flags);
        dest.writeString(mail);
        dest.writeString(password);
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeString(firmname);
        dest.writeInt(phone);
    }

    /**
     * CREATOR field to generate instance of object from Parcel
     */
    public static final Parcelable.Creator<Provider> CREATOR
            = new Parcelable.Creator<Provider>() {
        /**
         * Creates new instance of Customer from Parcel
         * @param in the parcel
         * @return new instance of Customer
         */
        public Provider createFromParcel(Parcel in) {
            return new Provider(in);
        }

        /**
         * Creates new Array of Customers from Parcel
         * @param size size of the array
         * @return Array with new instances of Customers
         */
        public Provider[] newArray(int size) {
            return new Provider[size];
        }
    };

    /**
     * Private constructor for Customer used by CREATOR to deserialize Parcel
     *
     * @param in Parcel to deserialize
     */
    private Provider(Parcel in) {
        id = in.readLong();
        address = in.readParcelable(Address.class.getClassLoader());
        mail = in.readString();
        password = in.readString();
        firstname = in.readString();
        lastname= in.readString();
        firmname = in.readString();
        phone = in.readInt();
    }
}
