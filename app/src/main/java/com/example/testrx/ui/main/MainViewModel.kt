package com.example.testrx.ui.main

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testrx.ui.main.Service.Service
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody



class MainViewModel : ViewModel() {

    var image = MutableLiveData<ArrayList<ImageS>>()

    @SuppressLint("CheckResult")
    fun getImages(id: List<String>) {

        Observable.fromIterable(id)
            .flatMap {
                Observable.zip(
                Observable.just(it),
                Service.serviceApi.getImage(
                    it
                ),
                BiFunction<String, ResponseBody, Pair<String, ResponseBody>> { t1, t2 ->
                    Pair(t1, t2)
                }).onErrorResumeNext(Observable.empty())
        }.toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                var data = arrayListOf<ImageS>()
                data.add(ImageS(1,null))
                data.add(ImageS(2,null))
                data.add(ImageS(3,null))
                it.forEach { res->
                    Log.e("error fisrt",res.first)
                    var imageId = res.first
                    var bitmap = BitmapFactory.decodeStream (res.second.byteStream())
                    Log.e("error data",data?.size.toString())
                    data?.filter { res1 -> res1.content.toString() == imageId }?.forEach {
                        it.images = bitmap
                    }
                    Log.e("error after",res.first)
                }
                image.value = data
            }, {
                Log.e("error",it.message!!)
            })
    }
}
