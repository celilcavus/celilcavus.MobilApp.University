package com.example.celilcavusmobilapprandomuser.Adapter.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celilcavusmobilapprandomuser.databinding.UniversityrowBinding;

public class UniversityViewHolder extends RecyclerView.ViewHolder {

    public UniversityrowBinding universityrowBinding;

    public UniversityViewHolder(UniversityrowBinding universityrowBinding) {
        super(universityrowBinding.getRoot());
        this.universityrowBinding = universityrowBinding;
    }
}
