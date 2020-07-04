package com.example.learningaspects.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.learningaspects.Czasownik;
import com.example.learningaspects.CzasownikAdapter;
import com.example.learningaspects.R;

public class DashboardFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;  /* pozwala na załadowanie tylko tyle co trzeba a nie wszystkich*/
    private RecyclerView.LayoutManager mLayoutManager;

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ArrayList<Czasownik> czasownikList = new ArrayList<>();
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "robić", "zrobić"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_black_24dp, "oglądać", "obejrzeć"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_black_24dp, "pić", "wypić"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "robić", "zrobić"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "oglądać", "obejrzeć"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "pić", "wypić"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "robić", "zrobić"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "oglądać", "obejrzeć"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "pić", "wypić"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "robić", "zrobić"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "oglądać", "obejrzeć"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "pić", "wypić"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "robić", "zrobić"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "oglądać", "obejrzeć"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "pić", "wypić"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "robić", "zrobić"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "oglądać", "obejrzeć"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "pić", "wypić"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "robić", "zrobić"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "oglądać", "obejrzeć"));
        czasownikList.add(new Czasownik(R.drawable.ic_star_border_black_24dp, "pić", "wypić"));

        mRecyclerView = root.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new CzasownikAdapter(czasownikList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return root;
    }
}
