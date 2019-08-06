package edu.stevens.cs522.bookstore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import edu.stevens.cs522.bookstore.R;
import edu.stevens.cs522.bookstore.entities.Author;
import edu.stevens.cs522.bookstore.entities.Book;

public class AddBookActivity extends AppCompatActivity {
	
	// Use this as the key to return the book details as a Parcelable extra in the result intent.
	public static final String BOOK_RESULT_KEY = "book_result";
   // private static final String TAG = "AddBookActivity" ;
    private EditText title,author,isbn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_book);
        title = (EditText)findViewById(R.id.search_title);
        author = (EditText)findViewById(R.id.search_author);
        isbn = (EditText)findViewById(R.id.search_isbn);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// TODO provide ADD and CANCEL options
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.addactivity_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		// TODO
		switch (item.getItemId()) {
			// ADD: return the book details to the BookStore activity
			case R.id.add:
                Intent intent = new Intent();
                Book book = addBook();
                intent.putExtra(BOOK_RESULT_KEY,book);
                setResult(RESULT_OK,intent);
                finish();
				return true;

			// CANCEL: cancel the request
			case R.id.cancel:
                Intent cancel_intent = new Intent();
                setResult(RESULT_CANCELED,cancel_intent);
                finish();
				return true;
		}
		return false;
	}
	
	public Book addBook(){
		// TODO Just build a Book object with the search criteria and return that.
        Book book = new Book();
        String t = title.getText().toString();
        book.setTitle(t);
        String[] str = author.getText().toString().split(",");
        Author[] authors = new Author[str.length];
        int index = 0;
        for(String s : str){
            Author a = null;
            String[] fml = s.split("\\s+");
            if(fml.length == 1){
                a = new Author(fml[0]);
            }else if(fml.length == 2){
                a = new Author(fml[0], fml[1]);
            }else if(fml.length == 3){
                a = new Author(fml[0], fml[1], fml[2]);
            }else{
                Toast.makeText(this, "Invalid Author Name", Toast.LENGTH_LONG).show();
            }
            authors[index] = a;
            index++;
        }
        book.setAuthors(authors);
        String isb = isbn.getText().toString();
        book.setIsbn(isb);

		return book;
	}

}