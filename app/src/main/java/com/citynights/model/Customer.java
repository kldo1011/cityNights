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
    public Customer(String username, String password, String firstName, String lastName, String phone, String mail, Integer address,  String newsletter) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
        this.newsletter=newsletter;
    }

    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String mail;
    private Integer address;
    private String newsletter;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public String getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(String newsletter) {
        this.newsletter = newsletter;
    }




    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
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
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(phone);
        dest.writeString(mail);
        dest.writeInt(address);
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
        username = in.readString();
        password = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        phone = in.readString();
        mail = in.readString();
        address = in.readInt();
        newsletter = in.readString();

    }
}
