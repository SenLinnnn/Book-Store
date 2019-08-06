package edu.stevens.cs522.bookstore.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.BadParcelableException;
import android.util.Log;
import android.os.Parcelable.ClassLoaderCreator;
import edu.stevens.cs522.bookstore.entities.Author;

public class Book implements Parcelable {
	
	// TODO Modify this to implement the Parcelable interface.

	//public int id;
	
	public String title;
	
	public Author[] authors;
	
	public String isbn;
	
	//public String price;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        //dest.writeInt(id);
        dest.writeString(title);
        dest.writeTypedArray(authors,flags);
        dest.writeString(isbn);
       // dest.writeString(price);
    }

    public static final Parcelable.Creator<Book> CREATOR
            = new Parcelable.Creator<Book>() {

        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    private Book(Parcel source) {
       // id = source.readInt();
        title = source.readString();
        int length = source.readInt();
        authors = source.createTypedArray(Author.CREATOR);
       /*
        authors = new Author[length];
        for (int j = 0; j < length; j++) {
            try {
                authors[j] = source.readParcelable(Author.class.getClassLoader());
            }
            catch (BadParcelableException e) {
                Log.e(Book.class.getCanonicalName(), "BadParcelableException", e);
            }
        }
        */
        isbn = source.readString();
       // price = source.readString();
    }

    public Book( ) {

	}
    public Book(String title, Author[] author, String isbn) {

        this.title = title;
        this.authors = author;
        this.isbn = isbn;

    }

	public String getFirstAuthor() {
		if (authors != null && authors.length > 0) {
			return authors[0].toString();

		} else {
			return " ";
		}
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


}