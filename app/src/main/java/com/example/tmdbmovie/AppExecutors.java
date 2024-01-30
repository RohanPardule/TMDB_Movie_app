package com.example.tmdbmovie;

import android.util.Log;

import com.example.tmdbmovie.models.MovieModel;
import com.example.tmdbmovie.request.Servicey;
import com.example.tmdbmovie.response.MovieSearchResponse;
import com.example.tmdbmovie.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class AppExecutors {
    // singleton pattern

    private static AppExecutors instance;
    public static AppExecutors getInstance(){
        if (instance==null)
        {
            instance=new AppExecutors();
        }
        return instance;
    }
    private final ScheduledExecutorService mNetworkIO= Executors.newScheduledThreadPool(3);
    public ScheduledExecutorService networkIO(){
        return mNetworkIO;
    }




    }

