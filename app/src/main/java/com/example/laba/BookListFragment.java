package com.example.laba;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.*;

import com.example.laba.code.interfaces.FetchBooksTask;
import com.example.laba.code.interfaces.OnBookClickListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookListFragment extends Fragment implements OnBookClickListener {


    private BookAdapter bookAdapter;
    private List<String> books;

    @Override
    public void onBookClick(Book book) {

        Bundle bundle = new Bundle();
        bundle.putString("book_name", book.name);
        bundle.putString("book_genre", book.genre);
        bundle.putString("book_author", book.author);
        bundle.putString("book_publicationDate", book.publicationDate);
        bundle.putString("book_rating", String.valueOf(book.rating));
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_navig);

        navController.popBackStack();
        navController.navigate(R.id.navigation_dashboard, bundle);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_books);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        OnBookClickListener context = this;

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {

                //Background work here
                Book[] books = new FetchBooksTask().GetBookInfo();

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        //String[] bookTitles = getResources().getStringArray(R.array.book_titles);

                        //Arrays.sort(bookTitles);
                        BookAdapter adapter = new BookAdapter(books, context);
                        recyclerView.setAdapter(adapter);
                        //UI Thread work here
                    }
                });
            }
        });





        return view;
    }
}
