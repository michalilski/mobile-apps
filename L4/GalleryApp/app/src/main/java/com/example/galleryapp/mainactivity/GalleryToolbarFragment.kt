package com.example.galleryapp.mainactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.galleryapp.R

class GalleryToolbarFragment : Fragment(R.layout.gallery_toolbar_fragment) {
    lateinit var starButton: ImageButton
    lateinit var cameraButton: ImageButton
    lateinit var sortButton: ImageButton
    lateinit var layout: LinearLayout
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

    private fun setup() {
        starButton = mView.findViewById(R.id.starButton)
        cameraButton = mView.findViewById(R.id.cameraButton)
        sortButton = mView.findViewById(R.id.sortButton)
        layout = mView.findViewById(R.id.toolbarLayout)
    }
}