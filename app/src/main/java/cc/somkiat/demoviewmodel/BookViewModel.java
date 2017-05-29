package cc.somkiat.demoviewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import cc.somkiat.demoviewmodel.model.Book;
import cc.somkiat.demoviewmodel.model.BookSearchResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookViewModel extends ViewModel {

    private BookService bookService;
    private MutableLiveData<List<Book>> booksLiveData;

    public BookViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        bookService = retrofit.create(BookService.class);
    }

    void searchBook(String keyword) {
        bookService.search(keyword).enqueue(new Callback<BookSearchResult>() {
            @Override
            public void onResponse(Call<BookSearchResult> call, Response<BookSearchResult> response) {
                booksLiveData.setValue(response.body().getBooks());
            }

            @Override
            public void onFailure(Call<BookSearchResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    LiveData<List<Book>> getBooks() {
        if(booksLiveData == null) {
            booksLiveData = new MutableLiveData<>();
        }
        return booksLiveData;
    }

}
