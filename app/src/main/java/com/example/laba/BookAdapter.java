
package com.example.laba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laba.code.interfaces.OnBookClickListener;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private String[] bookTitles;
    private final OnBookClickListener listener;

    public BookAdapter(String[] bookTitles, OnBookClickListener listener) {
        this.bookTitles = bookTitles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.bind(bookTitles[position], listener);
    }

    @Override
    public int getItemCount() {
        return bookTitles.length; // > 7 ? 7 : bookTitles.length; // Отображаем до 7 элементов
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public BookViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_book_title);
        }

        public void bind(String title, final OnBookClickListener listener) {
            textView.setText(title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onBookClick(title);
                }
            });
        }
    }
}
