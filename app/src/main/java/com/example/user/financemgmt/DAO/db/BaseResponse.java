package com.example.user.financemgmt.DAO.db;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 16.10.2017.
 */

public class BaseResponse<T> {
    @SerializedName("Data")
    protected T data;
    public T getData() { return data;}
}
