package com.example.gallerytab.gallery

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.gallerytab.FragmentAdapter
import com.example.gallerytab.R
import com.example.gallerytab.Utils
import com.example.gallerytab.firstpage.FirstPageFragment
import com.example.gallerytab.gallery.GalleryController.Companion.dataSet

class GalleryAdapter(private val context: Context) :
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    var showRating = false
    private var sortDirection = 1
    lateinit var parent : FirstPageFragment

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.galleryImageView)
        val ratingView: TextView = view.findViewById(R.id.ratingTextView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.gallery_image, viewGroup, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.imageView.setImageBitmap(Utils.loadThumbnailContent(dataSet[position]))
        viewHolder.imageView.setOnClickListener {
            GalleryController.currentThumbnail = dataSet[position]
        }
        viewHolder.imageView.setOnLongClickListener {
            val b = AlertDialog.Builder(context)
            b.setTitle("Set image description:")
            val input = EditText(context)
            input.inputType = InputType.TYPE_CLASS_TEXT
            b.setView(input)
            b.setPositiveButton("OK") { _, _ ->
                dataSet[position].description = input.text.toString()
            }
            b.setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            b.show()
            true
        }
        if (showRating) {
            viewHolder.ratingView.visibility = View.VISIBLE
            viewHolder.ratingView.text = "Rate: ${dataSet[position].stars}"
        } else {
            viewHolder.ratingView.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount() = dataSet.size

    fun sort() {
        sortDirection *= -1
        dataSet.sortWith(nullsLast(compareBy { it.stars * sortDirection }))
        notifyDataSetChanged()
    }

    fun sortDescending() {
        dataSet.sortWith(nullsLast(compareBy { -it.stars }))
        notifyDataSetChanged()
    }


}