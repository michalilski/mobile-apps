package com.example.gallerytab

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import com.example.gallerytab.gallery.Thumbnail
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class Utils {
    companion object {
        fun dirThumbnails() {

        }
        fun mockDataSet(context: Context) {
            val bitmaps = arrayOf(
                BitmapFactory.decodeResource(context.resources, R.drawable.sample_image),
                BitmapFactory.decodeResource(context.resources, R.drawable.sample_image2),
                BitmapFactory.decodeResource(context.resources, R.drawable.sample_image3)
            )
            var iter = 1
            bitmaps.forEach {
                val fos = FileOutputStream(getSourcePath(context) + "/img${iter}.png")
                it.compress(Bitmap.CompressFormat.PNG, 100, fos)
                iter++
            }
        }

        fun clearGallery(context: Context) {
            File(getSourcePath(context)).listFiles().forEach {
                it.delete()
            }
            File(getThumbnailPath(context)).listFiles().forEach {
                it.delete()
            }
        }

        fun loadThumbnails(context: Context): ArrayList<Thumbnail> {
            val thumbnails = ArrayList<Thumbnail>()
            val sourcePath = getSourcePath(context)
            File(sourcePath).listFiles().forEach {
                val thumbnailName = kotlin.math.abs(it.hashCode()).toString() + it.name
                val thumbnailPath = getThumbnailPath(context) + "/${thumbnailName}"
                var thumbnailFile = File(thumbnailPath)
                if (!thumbnailFile.exists()) {
                    val fis = FileInputStream(it)
                    val sourceImage = BitmapFactory.decodeStream(fis)
                    val thumbnailImage = Bitmap.createScaledBitmap(sourceImage, 112, 112, false)
                    val fos = FileOutputStream(thumbnailPath)
                    thumbnailImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
                }
                thumbnails.add(
                    Thumbnail(
                        it.absolutePath,
                        thumbnailPath,
                        "No description",
                        2.5f
                    )
                )
            }
            return thumbnails
        }

        fun loadThumbnailContent(thumbnail: Thumbnail): Bitmap {
            val fis = FileInputStream(thumbnail.path)
            return BitmapFactory.decodeStream(fis)
        }

        fun loadSourceContent(thumbnail: Thumbnail): Bitmap {
            val fis = FileInputStream(thumbnail.sourcePath)
            return BitmapFactory.decodeStream(fis)
        }

        fun saveImage(imageBitmap: Bitmap, context: Context): Thumbnail {
            val sourceName = System.currentTimeMillis()
            val sourcePath = getSourcePath(context) + "/${sourceName}.png"
            var fos = FileOutputStream(sourcePath)
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            val thumbnailImage = Bitmap.createScaledBitmap(imageBitmap, 112, 112, false)
            val thumbnailPath =
                getThumbnailPath(context) + "/${kotlin.math.abs(imageBitmap.hashCode())}${sourceName}.png"
            fos = FileOutputStream(thumbnailPath)
            thumbnailImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
            return Thumbnail(
                sourcePath,
                thumbnailPath,
                "No description",
                2.5f
            )
        }

        fun getSourcePath(context: Context): String {
            return context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString()
        }

        fun getThumbnailPath(context: Context): String {
            return context.getExternalFilesDir(null).toString() + "/Thumbnails"
        }
    }
}