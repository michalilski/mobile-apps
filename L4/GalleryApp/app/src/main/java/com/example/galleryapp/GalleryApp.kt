package com.example.galleryapp

import android.app.Application
import com.example.galleryapp.gallery.GalleryController

class GalleryApp : Application() {
    override fun onCreate() {
        super.onCreate()
//        Utils.clearGallery(this)
//        Utils.mockDataSet(this)
        GalleryController.dataSet = Utils.loadThumbnails(this)
    }
}