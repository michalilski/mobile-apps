package com.example.galleryapp

import android.content.Context
import android.graphics.BitmapFactory
import com.example.galleryapp.gallery.GalleryImage

class Utils {
    companion object{
        fun mockDataSet(appContext : Context) : ArrayList<GalleryImage>{
            val bitmaps = arrayOf(
                BitmapFactory.decodeResource(appContext.resources, R.drawable.sample_image),
                BitmapFactory.decodeResource(appContext.resources, R.drawable.sample_image2),
                BitmapFactory.decodeResource(appContext.resources, R.drawable.sample_image3)
            )
            val dataSet = arrayListOf<GalleryImage>()
            for (i in 0..2) {
                dataSet.add(
                    GalleryImage(
                        bitmaps[i%3],
                        "Default photo description",
                        2.5f
                    )
                )
            }
            return dataSet
        }
    }
}