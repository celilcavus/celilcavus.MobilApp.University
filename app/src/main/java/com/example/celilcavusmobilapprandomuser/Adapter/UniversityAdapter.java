package com.example.celilcavusmobilapprandomuser.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celilcavusmobilapprandomuser.Adapter.ViewHolder.UniversityViewHolder;
import com.example.celilcavusmobilapprandomuser.Entity.University;
import com.example.celilcavusmobilapprandomuser.databinding.UniversityrowBinding;

import java.util.List;

public class UniversityAdapter extends RecyclerView.Adapter<UniversityViewHolder> {
    private List<University> universities;

    public UniversityAdapter(List<University> universities) {
        this.universities = universities;
    }


    @NonNull
    @Override
    public UniversityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UniversityrowBinding universityrowBinding = UniversityrowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new UniversityViewHolder(universityrowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UniversityViewHolder holder, int position) {
        holder.universityrowBinding.UniversityName.setText(universities.get(position).getName());
        holder.universityrowBinding.UniversityCountry.setText(universities.get(position).getCountry());
        holder.universityrowBinding.UniversityDomain.setText(universities.get(position).getDomains()[0]);
    }

    @Override
    public int getItemCount() {
        return universities.size();
    }
}
