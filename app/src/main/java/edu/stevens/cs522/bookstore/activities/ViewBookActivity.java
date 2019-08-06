package edu.stevens.cs522.bookstore.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import edu.stevens.cs522.bookstore.R;
import edu.stevens.cs522.bookstore.entities.Author;
import edu.stevens.cs522.bookstore.entities.Book;

public class ViewBookActivity extends Activity {
	
	// Use this as the key to return the book details as a Parcelable extra in the result intent.
	public static final String BOOK_KEY = "book";
	private TextView titleTV, authorsTV, isbnTV;
	private ArrayList<Book> data = new ArrayList<Book>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_book);

		// TODO get book as parcelable intent extra and populate the UI with book details.
		titleTV = (TextView)findViewById(R.id.view_title);
		authorsTV = (TextView)findViewById(R.id.view_author);
		isbnTV = (TextView)findViewById(R.id.view_isbn);

		//Intent viewintent =getIntent();
		data = getIntent().getExtras().getParcelableArrayList(BOOK_KEY);

		titleTV.setText(data.get(0).title);

		//DIsplay Authours

		authorsTV.setText(data.get(0).getFirstAuthor());
		isbnTV.setText(data.get(0).isbn);



	}

	}

