package cc.somkiat.demoviewmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import cc.somkiat.demoviewmodel.model.Book;

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Book book = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView author = (TextView) convertView.findViewById(R.id.author);

        title.setText(book.getVolumeInfo().getTitle());
        if (book.getVolumeInfo().getAuthor() != null) {
            StringBuilder builder = new StringBuilder();
            for (String authorName : book.getVolumeInfo().getAuthor()) {
                if (builder.length() > 0) {
                    builder.append(", ");
                }
                builder.append(authorName);
            }
            author.setText(builder.toString());
        }

        return convertView;
    }
}
