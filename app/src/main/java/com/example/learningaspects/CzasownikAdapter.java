package com.example.learningaspects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CzasownikAdapter extends RecyclerView.Adapter<CzasownikAdapter.CzasownikViewHolder> {
    private ArrayList<Czasownik> mCzasownikList;

    public static class CzasownikViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public CzasownikViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
        }
    }

    public CzasownikAdapter(ArrayList<Czasownik> czasownikList) {
        mCzasownikList = czasownikList;
    }
    @NonNull
    @Override
    public CzasownikViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.czasownik, parent, false);
        CzasownikViewHolder cvh = new CzasownikViewHolder(v);
        return  cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CzasownikViewHolder holder, int position) {
        Czasownik currentItem = mCzasownikList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mCzasownikList.size();
    }
}
