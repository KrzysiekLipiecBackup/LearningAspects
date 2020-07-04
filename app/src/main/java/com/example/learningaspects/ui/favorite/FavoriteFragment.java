package com.example.learningaspects.ui.favorite;

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

import java.util.ArrayList;

import com.example.learningaspects.Czasownik;
import com.example.learningaspects.CzasownikAdapter;
import com.example.learningaspects.R;

public class FavoriteFragment extends Fragment {
    private ArrayList<Czasownik> mCzasownikList;
    private RecyclerView mRecyclerView;
    private CzasownikAdapter mAdapter;  /* pozwala na załadowanie tylko tyle co trzeba a nie wszystkich*/
    private RecyclerView.LayoutManager mLayoutManager;

    private Button buttonInsert;
    private Button buttonRemove;
    private EditText editTextInsert;
    private EditText editTextRemove;

    private FavoriteViewModel favoriteViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoriteViewModel =
                ViewModelProviders.of(this).get(FavoriteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerView);

        createCzasownikList();
        buildRecyclerView();

        buttonInsert = root.findViewById(R.id.button_insert);
        buttonRemove = root.findViewById(R.id.button_remove);
        editTextInsert = root.findViewById(R.id.edittext_insert);
        editTextRemove = root.findViewById(R.id.edittext_remove);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextRemove.getText().toString());
                removeItem(position);
            }
        });

        return root;
    }
    public void insertItem(int position) {
        mCzasownikList.add(position, new Czasownik(R.drawable.ic_star_black_24dp, "Nowy item","oryginalna pozycja - " + position));
        mAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mCzasownikList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void changeImage(int position, int image) {
        mCzasownikList.get(position).changeImage(image);
        mAdapter.notifyItemChanged(position);
    }

    public void createCzasownikList() {
        mCzasownikList = new ArrayList<>();
        mCzasownikList.add(new Czasownik(R.drawable.ic_star_black_24dp, "oglądać", "obejrzeć"));
        mCzasownikList.add(new Czasownik(R.drawable.ic_star_black_24dp, "pić", "wypić"));
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
                if (mCzasownikList.get(position).getImageResource() == R.drawable.ic_star_border_black_24dp) {
                    changeImage(position, R.drawable.ic_star_black_24dp);
                } else if (mCzasownikList.get(position).getImageResource() == R.drawable.ic_star_black_24dp) {
                    changeImage(position, R.drawable.ic_star_border_black_24dp);
                } else changeImage(position, R.drawable.ic_practice_black_24dp);
            }
        });
    }
}
