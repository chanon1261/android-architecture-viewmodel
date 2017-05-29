package cc.somkiat.demoviewmodel;

import cc.somkiat.demoviewmodel.model.BookSearchResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookService {

    @GET("books/v1/volumes")
    Call<BookSearchResult> search(@Query("q") String keyword);

}
