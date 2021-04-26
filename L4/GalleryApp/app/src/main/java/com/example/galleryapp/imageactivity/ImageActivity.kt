package com.example.galleryapp.imageactivity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.galleryapp.R
import com.example.galleryapp.Utils
import com.example.galleryapp.gallery.GalleryController
import com.example.galleryapp.mainactivity.MainActivity

class ImageActivity : AppCompatActivity() {
    private lateinit var fullImageView : ImageView
    private lateinit var descriptionTextView : TextView
    private lateinit var ratingBar: RatingBar
    private var imageId = -1
    private var stars = -1.0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        actionBar?.hide()
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.image_activity)
        else
            setContentView(R.layout.image_activity_landscape)

        setup()
    }

    private fun setup() {
        fullImageView = findViewById(R.id.fullImageView)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        ratingBar = findViewById(R.id.ratingBar)
        ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, rating, _ ->
            this.stars = rating
        }
        imageId = intent.getIntExtra("imageId", -1)
        if (imageId != -1){
            val image = GalleryController.dataSet[imageId]
            stars = image.stars
            fullImageView.setImageBitmap(Utils.loadSourceContent(image))
            descriptionTextView.text = image.description
            ratingBar.rating = stars
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("imageId", imageId)
        intent.putExtra("stars", stars)
        startActivityIfNeeded(intent, 0)
        finish()
    }
}