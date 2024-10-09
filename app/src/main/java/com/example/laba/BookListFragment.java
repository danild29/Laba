package com.example.laba;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.*;

import com.example.laba.code.interfaces.OnBookClickListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class BookListFragment extends Fragment implements OnBookClickListener {


    private BookAdapter bookAdapter;
    private List<String> books;

    @Override
    public void onBookClick(String book) {

        Bundle bundle = new Bundle();
        bundle.putString("bookName", book);
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_navig);

        navController.popBackStack();
        navController.navigate(R.id.navigation_dashboard, bundle);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_books);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String[] bookTitles = getResources().getStringArray(R.array.book_titles);

        Arrays.sort(bookTitles);
        BookAdapter adapter = new BookAdapter(bookTitles, this);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
