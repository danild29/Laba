package com.example.laba.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.laba.FavoriteBook;
import com.example.laba.Room.AppDatabase;
import com.example.laba.databinding.FragmentNotificationsBinding;

import java.util.List;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        AppDatabase db = AppDatabase.getDatabase(getActivity());

        List<FavoriteBook> books = db.favoriteBookDao().getFavoriteBooksForUser(AppDatabase.UserId);
        List<FavoriteBook> booksall = db.favoriteBookDao().getFavoriteBooks();
        for (FavoriteBook book: books) {
            Log.i("NotificationsFragment",
                    String.format("Id=%s userId=%s bookId=%s .", book.getId(), book.getUserId(), book.getBookId())
            );
        }

        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}