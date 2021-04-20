package com.example.galleryapp.mainactivity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.galleryapp.R.layout
import com.example.galleryapp.gallery.GalleryController
import com.example.galleryapp.gallery.GalleryImage

class MainActivity : AppCompatActivity() {
    private lateinit var galleryPhotosFragment: GalleryPhotosFragment
    private lateinit var galleryToolbarFragment: GalleryToolbarFragment
    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        actionBar?.hide()

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(layout.activity_main)
        } else {
            setContentView(layout.activity_main_landscape)
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is GalleryPhotosFragment) {
            galleryPhotosFragment = fragment
        } else if (fragment is GalleryToolbarFragment) {
            galleryToolbarFragment = fragment
        }
    }

    override fun onResume() {

        galleryToolbarFragment.starButton.setOnClickListener {
            galleryPhotosFragment.changeRatingVisibility()
        }

        galleryToolbarFragment.sortButton.setOnClickListener {
            galleryPhotosFragment.sortPhotos()
        }

        galleryToolbarFragment.cameraButton.setOnClickListener {
            dispatchTakePictureIntent()
        }

        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            galleryToolbarFragment.layout.orientation = LinearLayout.HORIZONTAL
        else
            galleryToolbarFragment.layout.orientation = LinearLayout.VERTICAL

        val imageId = intent.getIntExtra("imageId", -1)
        val stars = intent.getFloatExtra("stars", -1.0f)
        if (imageId != -1) {
            val image = GalleryController.dataSet[imageId]
            image.stars = stars
            galleryPhotosFragment.updateRecycler()
        }

        galleryPhotosFragment.sortPhotosDescending()

        super.onResume()
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            Log.e("Camera Error", e.stackTrace.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val image = GalleryImage(
                imageBitmap,
                "Default photo description",
                2.5f
            )
            GalleryController.dataSet.add(image)
            galleryPhotosFragment.sortPhotosDescending()
        }
    }
}