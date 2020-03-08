package com.example.testrx.ui.main.Service

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiRequest {

    @GET("image/{id}")
    fun getImage(
        @Path ("id") id: String
    ): Observable<ResponseBody>
}