package com.example.galleryapp

import android.app.Application
import com.example.galleryapp.gallery.GalleryController

class GalleryApp : Application() {
    override fun onCreate() {
        super.onCreate()
        GalleryController.dataSet = Utils.mockDataSet(this)
    }
}