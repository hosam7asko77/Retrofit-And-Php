package com.example.retrofitandphp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.retrofitandphp.R;
import com.example.retrofitandphp.Storage.SharedPrefManager;

public class HomeFragment extends Fragment {
    private TextView textEmail,textName;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textEmail=view.findViewById(R.id.tv_email);
        textName=view.findViewById(R.id.tv_Name);
        textEmail.setText(SharedPrefManager.getInstance(getActivity()).getUser().getEmail());
        textName.setText(SharedPrefManager.getInstance(getActivity()).getUser().getName());
    }
}
