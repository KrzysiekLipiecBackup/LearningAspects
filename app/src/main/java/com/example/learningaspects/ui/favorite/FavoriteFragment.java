package com.example.learningaspects.ui.favorite;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

import com.example.learningaspects.Czasownik;
import com.example.learningaspects.CzasownikAdapter;
import com.example.learningaspects.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static android.content.Context.MODE_PRIVATE;

public class FavoriteFragment extends Fragment {
    private ArrayList<Czasownik> mCzasownikList;
    private ArrayList<Czasownik> mCzasownikListFav;
    private RecyclerView mRecyclerView;
    private CzasownikAdapter mAdapter;  /* pozwala na załadowanie tylko tyle co trzeba a nie wszystkich*/
    private RecyclerView.LayoutManager mLayoutManager;

    private FavoriteViewModel favoriteViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoriteViewModel =
                ViewModelProviders.of(this).get(FavoriteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerView);

        createCzasownikList();
        buildRecyclerView();

        return root;
    }

    public void insertItem(int position) {
        mCzasownikListFav.add(position, new Czasownik(true, "Nowy item", "oryginalna pozycja - " + position));
        mAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mCzasownikListFav.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void changeImage(int position, boolean image) {
        mCzasownikList.get(position).changeImage(image);
        mAdapter.notifyItemChanged(position);
    }

    public void createCzasownikList() {
        loadData();
        mCzasownikListFav = new ArrayList<>();

        for(int i = 0; i<mCzasownikList.size(); i++) {
            if(mCzasownikList.get(i).getImageResource() == true) mCzasownikListFav.add(mCzasownikList.get(i));
        }

    }

    public void buildRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new CzasownikAdapter(mCzasownikListFav);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CzasownikAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                for(int i = 0; i<mCzasownikList.size() - 1; i++) {
                    if(mCzasownikList.get(i).getText1() == mCzasownikListFav.get(position).getText1()) {
                        changeImage(i,false);
                        break;
                    }
                }
                removeItem(position);
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
        Type type = new TypeToken<ArrayList<Czasownik>>() {
        }.getType();
        mCzasownikList = gson.fromJson(json, type);
        if (mCzasownikList == null) {
            mCzasownikList = new ArrayList<>();
        }
    }
}