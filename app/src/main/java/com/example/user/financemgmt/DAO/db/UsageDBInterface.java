package com.example.user.financemgmt.DAO.db;

import com.example.user.financemgmt.DataModel.Usage;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by user on 16.10.2017.
 */

public interface UsageDBInterface {
    @Headers({"Authorization: Basic NTlkY2M1YzVhNGJlMjA1ODZkMjYwZTgzOjE2NWM2MjNhNDI=",
            "X-Api-Factory-Application-Id: 59dcc5c5a4be20586d260e83",
            "Content-Type: application/json)"})
    @GET("db/Usage")
    Observable<BaseResponse<ArrayList<Usage>>> getUsageResponse();

    @Headers({"Authorization: Basic NTlkY2M1YzVhNGJlMjA1ODZkMjYwZTgzOjE2NWM2MjNhNDI=",
            "X-Api-Factory-Application-Id: 59dcc5c5a4be20586d260e83",
            "Content-Type: application/json)"})
    @POST("db/Usage")
    void addUsageInList(@Body Usage usage);
}
