package com.example.learningaspects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CzasownikAdapter extends RecyclerView.Adapter<CzasownikAdapter.CzasownikViewHolder> implements Filterable {
    private ArrayList<Czasownik> mCzasownikList;
    private ArrayList<Czasownik> mCzasownikListFull;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class CzasownikViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public CzasownikViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public CzasownikAdapter(ArrayList<Czasownik> czasownikList) {
        mCzasownikList = czasownikList;
        mCzasownikListFull = new ArrayList<>(mCzasownikList);   //nowa KOPIA!!! listy do użycia przy wyszukiwaniu
    }
    @NonNull
    @Override
    public CzasownikViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.czasownik, parent, false);
        CzasownikViewHolder cvh = new CzasownikViewHolder(v, mListener);
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

    @Override
    public Filter getFilter() {
        return czasownikFilter;
    }

    private Filter czasownikFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Czasownik> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mCzasownikListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Czasownik item : mCzasownikListFull) {
                    if (item.getText1().toLowerCase().contains(filterPattern) || item.getText2().toLowerCase().contains(filterPattern)) { //zamiast contains() może być startsWith()
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mCzasownikList.clear();
            mCzasownikList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
