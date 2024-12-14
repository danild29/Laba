package com.example.laba.ui.notifications;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laba.Book;
import com.example.laba.R;
import com.example.laba.Room.AppDatabase;
import com.example.laba.code.interfaces.OnBookClickListener;

public class FavouriteBookAdapter extends RecyclerView.Adapter<FavouriteBookAdapter.FavouriteBookViewHolder> {

    private Book[] books;
    private final OnBookClickListener listener;

    public FavouriteBookAdapter(Book[] bookTitles, OnBookClickListener listener) {
        this.books = bookTitles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FavouriteBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new FavouriteBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteBookViewHolder holder, int position) {
        holder.bind(books[position], listener);
    }

    @Override
    public int getItemCount() {
        return books.length; // > 7 ? 7 : bookTitles.length; // Отображаем до 7 элементов
    }

    static class FavouriteBookViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private TextView authorView;

        public FavouriteBookViewHolder(View itemView) {
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
            //getBindingAdapter().;

            AppDatabase db = AppDatabase.getDatabase(context);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Выбор");
            builder.setMessage("Вы хотите удалить книгу '" + book.name + "' ?");
            builder.setPositiveButton("Удалить", (dialog, which) -> {

                db.favoriteBookDao().deleteFavoriteBook(AppDatabase.UserId, book.getId());

                Log.d("BookAdapter", "Книга удалена: " + book.name);
            });
            builder.setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
