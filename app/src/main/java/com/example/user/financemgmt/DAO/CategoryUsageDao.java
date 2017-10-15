package com.example.user.financemgmt.DAO;

import com.example.user.financemgmt.DataModel.Usage;

import java.util.ArrayList;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Palibin
 */

public interface CategoryUsageDao {

    @Headers({"Authorization: Basic NTlkY2M1YzVhNGJlMjA1ODZkMjYwZTgzOjE2NWM2MjNhNDI=",
            "X-Api-Factory-Application-Id: 59dcc5c5a4be20586d260e83",
            "Content-Type: application/json)"})
    @GET ("db/Usage")
    ArrayList<Usage> getCategoryUsageList();

    @Headers({"Authorization: Basic NTlkY2M1YzVhNGJlMjA1ODZkMjYwZTgzOjE2NWM2MjNhNDI=",
            "X-Api-Factory-Application-Id: 59dcc5c5a4be20586d260e83",
            "Content-Type: application/json)"})
    @POST ("db/Usage")
    void addUsageInList(@Body Usage usage);
}
