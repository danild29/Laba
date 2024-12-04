
package com.example.laba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;

import com.example.laba.Room.AppDatabase;
import com.example.laba.code.interfaces.OnBookClickListener;
import android.content.Context;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Book[] books;
    private final OnBookClickListener listener;

    public BookAdapter(Book[] bookTitles, OnBookClickListener listener) {
        this.books = bookTitles;
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
        holder.bind(books[position], listener);
    }

    @Override
    public int getItemCount() {
        return books.length; // > 7 ? 7 : bookTitles.length; // Отображаем до 7 элементов
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private TextView authorView;

        public BookViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_book_title);
            authorView = itemView.findViewById(R.id.text_view_book_author);
        }

        public void bind(Book book, final OnBookClickListener listener) {
            textView.setText(book.name);
            authorView.setText(book.author);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onBookClick(book);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showDialog(book, itemView.getContext());
                    return true;
                }
            });
        }

        private void showDialog(final Book book, Context context) {
            AppDatabase db = AppDatabase.getDatabase(context);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Выбор");
            builder.setMessage("Вы хотите добавить '" + book.name + "' в избранное?");
            builder.setPositiveButton("В избранное", (dialog, which) -> {

                if (!db.favoriteBookDao().containsFavoriteBooks(AppDatabase.UserId, book.getId()))
                {
                    FavoriteBook fb = new FavoriteBook(AppDatabase.UserId, book.getId());
                    db.favoriteBookDao().insert(fb);
                }
                Log.d("BookAdapter", "Книга добавлена в избранное: " + book.name);
            });
            builder.setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
