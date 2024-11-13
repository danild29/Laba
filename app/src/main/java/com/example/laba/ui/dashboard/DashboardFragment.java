package com.example.laba.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.laba.Book;
import com.example.laba.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        Bundle args = getArguments();
        Book book = new Book();
        book.name = "Книга не выбрана";
        if (args != null) {
            book.name = args.getString("book_name");
            book.author = args.getString("book_author");
            book.publicationDate = args.getString("book_publicationDate");
            book.genre = args.getString("book_genre");
            //book.genre = (int)(args.getString("book_rating"));

            /*
            bundle.putString("book_name", book.name);
                bundle.putString("book_genre", book.genre);
                bundle.putString("book_author", book.author);
                bundle.putString("book_author", book.publicationDate);
                bundle.putString("book_rating", String.valueOf(book.rating));
             */
        }

        DashboardViewModel dashboardViewModel = new DashboardViewModel(book);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        final TextView name = binding.textDashboard..findViewById(R.id.text_view_book_title);
        name.setText(book.name);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}