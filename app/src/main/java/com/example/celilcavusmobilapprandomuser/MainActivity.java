package com.example.celilcavusmobilapprandomuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.celilcavusmobilapprandomuser.Adapter.UniversityAdapter;
import com.example.celilcavusmobilapprandomuser.Entity.University;
import com.example.celilcavusmobilapprandomuser.Interfaces.IUniversityServices;
import com.example.celilcavusmobilapprandomuser.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivityMainBinding activityMainBinding;
    private  List<University> universitiesList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());



        Spinner spinner = activityMainBinding.SpinnerUniversityName;
        ArrayAdapter<CharSequence> charSequenceArrayAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        charSequenceArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(charSequenceArrayAdapter);
        spinner.setOnItemSelectedListener(this);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object i = parent.getItemAtPosition(position);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://universities.hipolabs.com/").addConverterFactory(GsonConverterFactory.create()).build();

        IUniversityServices services = retrofit.create(IUniversityServices.class);
        if (i == null)
        {
            return;
        }
        else
        {
            universitiesList.clear();
           try {
               activityMainBinding.UniversityRecylerView.setLayoutManager(new LinearLayoutManager(this));
               UniversityAdapter universityAdapter = new UniversityAdapter(universitiesList);
               activityMainBinding.UniversityRecylerView.setAdapter(universityAdapter);

               Call<List<University>> call = services.GetUniversity(i.toString());
               call.enqueue(new Callback<List<University>>() {
                   @Override
                   public void onResponse(Call<List<University>> call, Response<List<University>> response) {
                       if (response.isSuccessful())
                       {
                           List<University>  universities = response.body();
                           universities.forEach(x->{
                               University university = new University();
                               university.setName(x.getName());
                               university.setCountry(x.getCountry());
                               university.setDomains(x.getDomains());
                               universitiesList.add(university);
                           });
                           universityAdapter.notifyDataSetChanged();


                       }

                   }

                   @Override
                   public void onFailure(Call<List<University>> call, Throwable t) {
                       System.out.println("=== ERROR ====");
                       System.out.println(t.getMessage());
                   }
               });
           }
           catch (Exception ex)
           {
               System.out.println("===== TRY CATCH =====");
               System.out.println(ex.getMessage());
           }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        System.out.println("on nothin selected"+parent.getSelectedItem());
    }
}