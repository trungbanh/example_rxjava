package com.example.testrx.ui.main.Service



object Service {
    private const val url = "http://192.168.1.71:8000"

    val serviceApi: ApiRequest = RetrofitConfig.getRetrofit(
        url
    ).create(ApiRequest::class.java)
}
