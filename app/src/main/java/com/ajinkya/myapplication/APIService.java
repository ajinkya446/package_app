package com.ajinkya.myapplication;

import com.ajinkya.myapplication.model.ChatResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface APIService {
    @FormUrlEncoded
    @POST("v1/inference/text/web")
    Call<ChatResponse> getChatResponse(@Field("language") String language,@Field("question") String question);

}
