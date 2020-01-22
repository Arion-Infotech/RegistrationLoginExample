package com.arioninfotech.registrationloginexample;


import retrofit.RestAdapter;


public class Api {

    public static ApiInterface getClient(){
       RestAdapter adapter= new RestAdapter.Builder()
               .setEndpoint(CommonURL.baseurl).build();


        ApiInterface api = adapter.create(ApiInterface.class);
        return api;

    }
}
