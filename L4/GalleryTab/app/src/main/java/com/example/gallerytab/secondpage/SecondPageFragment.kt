package com.example.gallerytab.secondpage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.gallerytab.MainActivity
import com.example.gallerytab.R
import com.example.gallerytab.Utils
import com.example.gallerytab.gallery.GalleryController

class SecondPageFragment : Fragment(R.layout.second_page_fragment) {
    private lateinit var fullImageView : ImageView
    private lateinit var descriptionTextView : TextView
    private lateinit var ratingBar: RatingBar
    lateinit var parent : MainActivity
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = super.onCreateView(inflater, container, savedInstanceState)!!
        setup()
        return mView
    }

    fun update() {
        setCurrentImage()
    }

    private fun setup() {
        fullImageView = mView.findViewById(R.id.fullImageView)
        descriptionTextView = mView.findViewById(R.id.descriptionTextView)
        ratingBar = mView.findViewById(R.id.ratingBar)
        ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, rating, _ ->
            GalleryController.currentThumbnail?.stars = rating
        }
        setCurrentImage()
    }

    private fun setCurrentImage(){
        if(GalleryController.currentThumbnail != null) {
            fullImageView.setImageBitmap(Utils.loadSourceContent(GalleryController.currentThumbnail!!))
            ratingBar.rating = GalleryController.currentThumbnail!!.stars
            descriptionTextView.text = GalleryController.currentThumbnail!!.description
        }
    }
}
