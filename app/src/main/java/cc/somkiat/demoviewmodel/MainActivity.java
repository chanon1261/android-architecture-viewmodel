package cc.somkiat.demoviewmodel;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cc.somkiat.demoviewmodel.model.Book;

public class MainActivity extends LifecycleActivity {

    ListView listView;
    BookAdapter bookAdapter;
    BookViewModel bookViewModel;

    TextView dataNotFound;
    ImageButton searchButton;
    EditText searchKeyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataNotFound = (TextView) findViewById(R.id.text_data_not_found);
        searchButton = (ImageButton) findViewById(R.id.searchButton);
        searchKeyword = (EditText) findViewById(R.id.searchKeyword);

        bookAdapter = new BookAdapter(this, -1);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(bookAdapter);

        //Original way
//        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);

        //
        bookViewModel = createViewModel();

        bookViewModel.getBooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable List<Book> books) {
                updateView(books);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookViewModel.searchBook(getKeyword());
            }
        });

    }

    private BookViewModel createViewModel() {
        return ViewModelProviders.of(this, new BookViewModelFactory()).get(BookViewModel.class);
    }

    @NonNull
    private String getKeyword() {
        return searchKeyword.getText().toString().trim().replaceAll("\\s+", "+");
    }

    private void updateView(List<Book> books) {
        if(books.isEmpty()) {
            dataNotFound.setVisibility(View.VISIBLE);
        } else {
            dataNotFound.setVisibility(View.GONE);
        }
        bookAdapter.clear();
        bookAdapter.addAll(books);
    }

}
