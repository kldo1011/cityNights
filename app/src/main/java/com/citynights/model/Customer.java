package com.citynights.model;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Domain class for Customer
 *
 * Variablen Anpassen
 */
public class Customer implements Parcelable {

    /**
     * Empty default constructor for Customer
     */
    public Customer() {

    }

    /**
     * Constructor for Customer
     *
     * @param firstName first name
     * @param lastName  last name
     * @param phone     phone number
     * @param mail      mail address
     * @param address   address of the Customer
     */
    public Customer(String firstName, String lastName, String phone, String mail, Address address, String password, String newsletter) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
        this.password=password;
        this.newsletter=newsletter;
    }

    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String mail;
    private Address address;
    private String password;
    private String newsletter;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(String newsletter) {
        this.newsletter = newsletter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }




    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", address=" + address +
                ", newsletter=" + newsletter +
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
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(phone);
        dest.writeString(mail);
        dest.writeParcelable(address, flags);
        dest.writeString(password);
        dest.writeString(newsletter);
    }

    /**
     * CREATOR field to generate instance of object from Parcel
     */
    public static final Parcelable.Creator<Customer> CREATOR
            = new Parcelable.Creator<Customer>() {
        /**
         * Creates new instance of Customer from Parcel
         * @param in the parcel
         * @return new instance of Customer
         */
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        /**
         * Creates new Array of Customers from Parcel
         * @param size size of the array
         * @return Array with new instances of Customers
         */
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    /**
     * Private constructor for Customer used by CREATOR to deserialize Parcel
     *
     * @param in Parcel to deserialize
     */
    private Customer(Parcel in) {
        id = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
        phone = in.readString();
        mail = in.readString();
        password = in.readString();
        newsletter = in.readString();
        address = in.readParcelable(Address.class.getClassLoader());
    }
}
