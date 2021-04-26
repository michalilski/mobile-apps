package com.example.gallerytab

import android.app.Application
import com.example.gallerytab.gallery.GalleryController

class App : Application() {
    override fun onCreate() {
        super.onCreate()
//        Utils.clearGallery(this)
//        Utils.mockDataSet(this)
        GalleryController.dataSet = Utils.loadThumbnails(this)
        if(GalleryController.dataSet.size > 0) {
            GalleryController.currentThumbnail = GalleryController.dataSet[0]
        }
    }
}