package com.example.nicholaspark.movieobserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.nicholaspark.movieobserver.Clients.ApiClient;
import com.example.nicholaspark.movieobserver.Models.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity{

    private static final String USERNAME = "nicholaspark09";
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        indexRepos();


    }

    private void indexRepos(){
        ApiClient.getClient()
                .getRepositories(USERNAME)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG,"Task completed");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        for(Repo repo: repos){
                            Log.d(TAG,"New repo: "+repo.getFullName());
                        }
                    }
                });
    }



}
