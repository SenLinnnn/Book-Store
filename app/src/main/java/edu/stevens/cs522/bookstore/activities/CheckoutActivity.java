package edu.stevens.cs522.bookstore.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import edu.stevens.cs522.bookstore.R;

public class CheckoutActivity extends AppCompatActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// TODO display ORDER and CANCEL options.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.checkout_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		// TODO

		switch (item.getItemId()) {

			case R.id.order:
				// ORDER: display a toast message of how many books have been ordered and return
				setResult(Activity.RESULT_OK);
				finish();
				return true;

			case R.id.cancel:
				// CANCEL: just return with REQUEST_CANCELED as the result code
				setResult(Activity.RESULT_CANCELED);
				finish();
				return true;
		}

		return false;
	}
	
}