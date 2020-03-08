package com.example.testrx.ui.main

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testrx.R
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.main_fragment.view.*


class MainFragment : Fragment() {

    lateinit var carouselView: CarouselView

    lateinit var img : ImageView

    var sampleImages = ArrayList<ImageS>()

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        var view = inflater.inflate(R.layout.main_fragment, container, false)

        carouselView = view.findViewById(R.id.carouselView);

        viewModelRegister()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun viewModelRegister (){
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getImages(listOf("1","2","3"))
        viewModel.image.observe(viewLifecycleOwner, Observer {ims ->
            Log.e("result is", ims.size.toString())
            carouselView.setViewListener {
                val view1 = layoutInflater.inflate(R.layout.custom_carousel, null)
                val imageView = view1.findViewById<ImageView>(R.id.image_slider)
                imageView.setImageBitmap(ims[it].images)
                view1
            }
            carouselView.pageCount = 3

        })
    }



}
