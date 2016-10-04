package com.example.nicholaspark.movieobserver.Clients;

import com.example.nicholaspark.movieobserver.Models.Repo;
import com.google.gson.GsonBuilder;


import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by nicholaspark on 10/4/16.
 */

public class ApiClient {

    private static final String ENDPOINT = "https://api.github.com";

    public interface GithubClient{

        @GET("/users/{user}/repos")
        //Call<List<Repo>> listRepos(@Path("user") String user);
        Observable<List<Repo>> getRepositories(@Path("user") String user);
    }

    private static GithubClient client;

    private ApiClient(){}

    private static Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl(ENDPOINT)
            .build();

    public static synchronized  GithubClient getClient(){
        if(client == null){
            client = retrofit.create(GithubClient.class);
        }
        return client;
    }


}
