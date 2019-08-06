package edu.stevens.cs522.bookstore.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Author implements Parcelable{
	
	// TODO Modify this to implement the Parcelable interface.

	// NOTE: middleInitial may be NULL!
	
	public String firstName;
	
	public String middleInitial;
	
	public String lastName;

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(firstName);
		dest.writeString(middleInitial);
		dest.writeString(lastName);
	}

	public static final Parcelable.Creator<Author> CREATOR
			= new Parcelable.Creator<Author>(){
		@Override
		public Author createFromParcel(Parcel in){
				return new Author(in);
			}

		@Override
		public Author[] newArray(int size) {
			return new Author[size];
		}
	};

	public Author() {
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
	}
	public Author(String firstName) {
		this.firstName = firstName;
	}

	public Author(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Author(String firstName, String middleInitial, String lastName) {
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
	}
	public Author(Parcel in) {
		firstName = in.readString();
		middleInitial = in.readString();
		lastName = in.readString();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (firstName != null && !"".equals(firstName)) {
			sb.append(firstName);
			sb.append(' ');
		}
		if (middleInitial != null && !"".equals(middleInitial)) {
			sb.append(middleInitial);
			sb.append(' ');
		}
		if (lastName != null && !"".equals(lastName)) {
			sb.append(lastName);
		}
		return sb.toString();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getName(){
		String ret = firstName+"  "+middleInitial+"  "+lastName;
		return ret;

	}

}
