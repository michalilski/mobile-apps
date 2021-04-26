package com.example.gallerytab.firstpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gallerytab.MainActivity
import com.example.gallerytab.R
import com.example.gallerytab.gallery.GalleryAdapter

class FirstPageFragment : Fragment(R.layout.first_page_fragment) {
    private lateinit var galleryRecyclerView: RecyclerView
    private lateinit var galleryAdapter: GalleryAdapter
    lateinit var starButton: ImageButton
    lateinit var cameraButton: ImageButton
    lateinit var sortButton: ImageButton
    lateinit var layout: LinearLayout
    private lateinit var mView: View
    lateinit var parent: MainActivity

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
        galleryRecyclerView = this.mView.findViewById(R.id.galleryPhotosRecyclerView)
        galleryAdapter = GalleryAdapter(this.context!!)
        galleryAdapter.parent = this
        galleryRecyclerView.layoutManager = GridLayoutManager(view?.context, 3)
        galleryRecyclerView.adapter = galleryAdapter

        starButton = mView.findViewById(R.id.starButton)
        cameraButton = mView.findViewById(R.id.cameraButton)
        sortButton = mView.findViewById(R.id.sortButton)

        starButton.setOnClickListener {
            changeRatingVisibility()
        }

        cameraButton.setOnClickListener {
            parent.dispatchTakePictureIntent()
        }

        sortButton.setOnClickListener {
            sortPhotos()
        }
    }

    fun update(){
        sortPhotosDescending()
        changeRatingVisibility()
        changeRatingVisibility()
    }

    fun changeRatingVisibility(){
        galleryAdapter.showRating = !galleryAdapter.showRating
        galleryAdapter.notifyDataSetChanged()
    }

    fun sortPhotos(){
        galleryAdapter.sort()
    }

    fun sortPhotosDescending(){
        galleryAdapter.sortDescending()
    }

    fun updateRecycler() {
        galleryAdapter.notifyDataSetChanged()
    }

}