package com.example.youtube.api;

import com.example.youtube.model.Resultado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jamiltondamasceno
 */

public interface YoutubeService {

    /*

    https://www.googleapis.com/youtube/v3/
    search
    ?part=snippet
    &order=date
    &maxResults=20
    &key=AIzaSyB4a8iuFR3LOuqVbfm7edH9kBKyNX4eoBQ
    &channelId=UCVHFbqXqoYvEWM1Ddxl0QDg

    * */

    @GET("search")
    Call<Resultado> recuperarVideos(
                    @Query("part") String part,
                    @Query("order") String order,
                    @Query("maxResults") String maxResults,
                    @Query("key") String key,
                    @Query("channelId") String channelId
    );

}
