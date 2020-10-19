package com.example.tvshowtracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowtracker.R
import com.example.tvshowtracker.databinding.ItemContainerSliderImageBinding

class ImageSliderAdapter() :RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {

    private lateinit var sliderImages:ArrayList<String>

    private var layoutInflater: LayoutInflater? = null

    constructor(sliderImages: ArrayList<String>) : this() {
        this.sliderImages = sliderImages
    }


    inner class ImageSliderViewHolder:RecyclerView.ViewHolder {

        var itemContainerSliderImageBinding: ItemContainerSliderImageBinding
        constructor(a:ItemContainerSliderImageBinding):super(a.root){
            this.itemContainerSliderImageBinding = a
        }

        fun bindingSliderImage(imageURL: String){
            itemContainerSliderImageBinding.imageURL = imageURL
        }

    }

    override fun onCreateViewHolder(@NonNull parent: ViewGroup, @NonNull viewType: Int): ImageSliderViewHolder {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val sliderImageBinding:ItemContainerSliderImageBinding = DataBindingUtil.inflate(layoutInflater!!, R.layout.item_container_slider_image,parent,false)
        return ImageSliderViewHolder(sliderImageBinding)
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
        holder.bindingSliderImage(sliderImages.get(position))
    }

    override fun getItemCount(): Int {
        return  sliderImages.size
    }


}