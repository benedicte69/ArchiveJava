package com.example.bened.androidnews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

class NewsAdapter extends ArrayAdapter<News> {
    /**
     * Constructor
     *
     * @param context The current context.
     * @param news    The objects to represent in the ListView.
     */
    public NewsAdapter(@NonNull Context context, @NonNull List<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Find the news at the given position in the list of news
        News currentNews = getItem(position);

        //Display the news information in the adequate view
        TextView titleView = listItemView.findViewById(R.id.title_view);
        String headline = null;
        if (currentNews != null) {
            headline = currentNews.getHeadline();
        }
        titleView.setText(headline);

        TextView sectionView = listItemView.findViewById(R.id.section_view);
        String section = null;
        if (currentNews != null) {
            section = currentNews.getSection();
        }
        sectionView.setText(section);

        TextView authorView = listItemView.findViewById(R.id.author_view);
        String author = null;
        if (currentNews != null) {
            author = currentNews.getAuthor();
        }
        authorView.setText(author);

        TextView dateView = listItemView.findViewById(R.id.date_view);
        String datePublication = null;
        if (currentNews != null) {
            datePublication = currentNews.getDatePublication();
        }
        dateView.setText(datePublication);

        return listItemView;
    }
}
