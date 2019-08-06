package edu.stevens.cs522.bookstore.activities;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Parcel;
import android.os.Parcelable;

import edu.stevens.cs522.bookstore.entities.Book;
import edu.stevens.cs522.bookstore.R;
import edu.stevens.cs522.bookstore.util.BooksAdapter;

public class MainActivity extends AppCompatActivity {
	
	// Use this when logging errors and warnings.
	private static final String TAG = MainActivity.class.getCanonicalName();
	
	// These are request codes for subactivity request calls
	static final private int ADD_REQUEST = 1;
	
	static final private int CHECKOUT_REQUEST = ADD_REQUEST + 1;

	// There is a reason this must be an ArrayList instead of a List.
	private ArrayList<Book> shoppingCart = new ArrayList<Book>();
	BooksAdapter Adapter;
	// craet ListView
	private ListView StoreListView;
	static final private String cart = "List";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// TODO check if there is saved UI state, and if so, restore it (i.e. the cart contents)
		if(savedInstanceState != null){
			shoppingCart = savedInstanceState.getParcelableArrayList("List");
		}
		shoppingCart = new ArrayList<Book>();
		// TODO Set the layout (use cart.xml layout)
		setContentView(R.layout.cart);

		// TODO use an array adapter to display the cart contents.
		Adapter = new BooksAdapter(this, shoppingCart);
		StoreListView = (ListView)findViewById(android.R.id.list);
		StoreListView.setAdapter(Adapter);

		// For Contextual Action Menu
		StoreListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		StoreListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
			@Override
			public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
				mode.setTitle(StoreListView.getCheckedItemCount() + " selected Items");
			}

			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				Log.i(TAG, "creating action mode");
				mode.getMenuInflater().inflate(R.menu.context_menu, menu);
				return true;
			}

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				switch (item.getItemId()) {
					case R.id.menu_delete:
						Log.i(TAG, "action item delete clicked");
						doSomethingWithActionOneItems();
						mode.finish();
						return true;
					default:
						return false;
				}
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {
				Log.i(TAG, "destroying action mode");
			}
		});

		StoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
				Intent intent = new Intent(MainActivity.this,ViewBookActivity.class);
				intent.putParcelableArrayListExtra(ViewBookActivity.BOOK_KEY,shoppingCart);
				startActivity(intent);
			}
		});
	}

	private void doSomethingWithActionOneItems(){

		Log.i(TAG, "getting actionone items delete");
		SparseBooleanArray checked = StoreListView.getCheckedItemPositions();

		Book [] books = new Book[checked.size()];

		for(int i =0; i < checked.size(); i++){
			if(checked.valueAt(i) == true){
				Book theSelectedBook = (Book) StoreListView.getItemAtPosition(checked.keyAt(i));
				Log.i(TAG, "selected Book title: " + theSelectedBook.title);

				books[i] = theSelectedBook;
				//bAdapter.remove(theSelectedBook);
				//bAdapter.notifyDataSetChanged();
			}else{
				Log.i(TAG, "Nothing selected");
			}
		}

		for(Book b : books){
			shoppingCart.remove(b);
		}
		Adapter.notifyDataSetChanged();
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// TODO inflate a menu with ADD and CHECKOUT options
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.bookstore_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
        switch(item.getItemId()) {

            // TODO ADD provide the UI for adding a book
            case R.id.add:
                  Intent addIntent = new Intent(this, AddBookActivity.class);
                 startActivityForResult(addIntent, ADD_REQUEST);
                break;
			// delete the book
			case R.id.details:
				// DELETE delete the currently selected book
				if(shoppingCart.size() <= 0){
					Toast.makeText(this, "Cart is Empty! Nothing to Display!", Toast.LENGTH_LONG).show();
				}else{
					Intent viewIntent = new Intent(this, ViewBookActivity.class);
					Book book = shoppingCart.get(0);
					viewIntent.putExtra(ViewBookActivity.BOOK_KEY, book);
					startActivity(viewIntent);
				}
				break;
            // TODO CHECKOUT provide the UI for checking out
            case R.id.checkout:
            	Intent checkoutInent = new Intent(this, CheckoutActivity.class);
				startActivityForResult(checkoutInent, CHECKOUT_REQUEST);
                break;

            default:
        }
        return false;
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		// TODO Handle results from the Search and Checkout activities.

        // Use ADD_REQUEST and CHECKOUT_REQUEST codes to distinguish the cases.
        switch(requestCode) {
            case ADD_REQUEST:
                // ADD: add the book that is returned to the shopping cart.
				if(resultCode == RESULT_OK) {
					Book result = intent.getParcelableExtra(AddBookActivity.BOOK_RESULT_KEY);
					shoppingCart.add(result);
					Adapter.notifyDataSetChanged();
				}
				else if (resultCode == RESULT_CANCELED){
					Toast.makeText(this,"Add Book Cancelled",Toast.LENGTH_LONG).show();
				}

                break;
            case CHECKOUT_REQUEST:
                // CHECKOUT: empty the shopping cart.
				if(resultCode == RESULT_OK){
					Toast.makeText(this,"You have been checked out",Toast.LENGTH_LONG).show();
					shoppingCart.clear();
					Adapter.notifyDataSetChanged();


				}else if(resultCode == RESULT_CANCELED){
					Toast.makeText(this,"Checkout Cancelled",Toast.LENGTH_LONG).show();
				}

                break;
        }
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// TODO save the shopping cart contents (which should be a list of parcelables).
		savedInstanceState.putParcelableArrayList(cart, shoppingCart);
	}
	
}