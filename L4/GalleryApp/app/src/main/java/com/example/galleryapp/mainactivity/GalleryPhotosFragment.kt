package com.example.galleryapp.mainactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.R
import com.example.galleryapp.gallery.GalleryAdapter

class GalleryPhotosFragment : Fragment(R.layout.gallery_photos_fragment) {
    private lateinit var galleryRecyclerView: RecyclerView
    private lateinit var galleryAdapter: GalleryAdapter
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
//        retainInstance = true
        galleryRecyclerView = this.mView.findViewById(R.id.galleryPhotosRecyclerView)
        galleryAdapter = GalleryAdapter(this.context!!)
        galleryRecyclerView.layoutManager = GridLayoutManager(view?.context, 3)
        galleryRecyclerView.adapter = galleryAdapter
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