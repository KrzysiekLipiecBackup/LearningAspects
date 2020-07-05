package com.example.learningaspects.ui.allVerbs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.example.learningaspects.Czasownik;
import com.example.learningaspects.CzasownikAdapter;
import com.example.learningaspects.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static android.content.Context.MODE_PRIVATE;

public class AllVerbsFragment extends Fragment {
    private ArrayList<Czasownik> mCzasownikList;
    private RecyclerView mRecyclerView;
    private CzasownikAdapter mAdapter;  /* pozwala na załadowanie tylko tyle co trzeba a nie wszystkich*/
    private RecyclerView.LayoutManager mLayoutManager;

    private AllVerbsViewModel allVerbsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        allVerbsViewModel =
                ViewModelProviders.of(this).get(AllVerbsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_all_verbs, container, false);
        setHasOptionsMenu(true);
        mRecyclerView = root.findViewById(R.id.recyclerView);

        loadData();
        buildRecyclerView();

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());

        return root;
    }

    public void changeImage(int position, boolean image) {
        mCzasownikList.get(position).changeImage(image);
        mAdapter.notifyItemChanged(position);
    }


    public ArrayList<Czasownik> getFullList() {
        return mCzasownikList;
    }

    public void buildRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new CzasownikAdapter(mCzasownikList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CzasownikAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (mCzasownikList.get(position).getImageResource() == false) changeImage(position, true);
                else changeImage(position, false);
                saveData();
            }
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mCzasownikList);
        editor.putString("task list", json);
        editor.apply();
    }
    private void loadData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Czasownik>>() {}.getType();
        mCzasownikList = gson.fromJson(json, type);
        if (mCzasownikList == null) {
            mCzasownikList = new ArrayList<>();
            mCzasownikList.add(new Czasownik(true, "robić", "zrobić"));
            mCzasownikList.add(new Czasownik(false, "oglądać", "obejrzeć"));
            mCzasownikList.add(new Czasownik(false, "pić", "wypić"));
            mCzasownikList.add(new Czasownik(false, "robić", "zrobić"));
            mCzasownikList.add(new Czasownik(true, "oglądać", "obejrzeć"));
            mCzasownikList.add(new Czasownik(false, "pić", "wypić"));
            mCzasownikList.add(new Czasownik(false, "robić", "zrobić"));
            mCzasownikList.add(new Czasownik(false, "oglądać", "obejrzeć"));
            mCzasownikList.add(new Czasownik(false, "pić", "wypić"));
            mCzasownikList.add(new Czasownik(false, "robić", "zrobić"));
            mCzasownikList.add(new Czasownik(false, "oglądać", "obejrzeć"));
            mCzasownikList.add(new Czasownik(false, "pić", "wypić"));
            mCzasownikList.add(new Czasownik(false, "robić", "zrobić"));
            mCzasownikList.add(new Czasownik(false, "oglądać", "obejrzeć"));
            mCzasownikList.add(new Czasownik(false, "pić", "wypić"));
            mCzasownikList.add(new Czasownik(false, "robić", "zrobić"));
            mCzasownikList.add(new Czasownik(false, "oglądać", "obejrzeć"));
            mCzasownikList.add(new Czasownik(false, "pić", "wypić"));
            mCzasownikList.add(new Czasownik(false, "robić", "zrobić"));
            mCzasownikList.add(new Czasownik(false, "oglądać", "obejrzeć"));
            mCzasownikList.add(new Czasownik(false, "pić", "wypić"));
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}