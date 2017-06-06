package cc.somkiat.demoviewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;


public class BookViewModelFactory implements ViewModelProvider.Factory {
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new BookViewModel();
    }
}
