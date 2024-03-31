package com.ammarptn.whatsnew

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.ammarptn.whatsnew.api.model.Data
import com.google.android.material.bottomsheet.BottomSheetBehavior

class CustomAdapter (private val data: List<Data>,
                     private val theContext: Context) : PagerAdapter() {

    private lateinit var standardBottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var standardBottomSheet: LinearLayout
    private lateinit var description: TextView

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return data.size
    }
    override fun instantiateItem(container: ViewGroup,
                                 position: Int): Any
    {
        val inflater = LayoutInflater.from(theContext)

        val layout = inflater.inflate(R.layout.item_layout, container,
            false) as ViewGroup
        standardBottomSheet  = layout.findViewById(R.id.standardBottomSheet)
        description  = layout.findViewById(R.id.textView)
        description.text = data[position].description
        setupStandardBottomSheet()
        standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        DownloadImageFromInternet(layout.findViewById(R.id.image)).execute(data[position].image_location)
        container.addView(layout)
        return layout
    }
    override fun destroyItem(container: ViewGroup,
                             position: Int,
                             view: Any)
    {
        container.removeView(view as View)
    }

    @SuppressLint("StaticFieldLeak")
    @Suppress("DEPRECATION")
    private inner class DownloadImageFromInternet(var imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
        override fun doInBackground(vararg urls: String): Bitmap? {
            val imageURL = urls[0]
            var image: Bitmap? = null
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
            }
            catch (e: Exception) {
                Log.e("Error Message", e.message.toString())
                e.printStackTrace()
            }
            return image
        }
        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
        }
    }

    private fun setupStandardBottomSheet() {
        standardBottomSheetBehavior = BottomSheetBehavior.from(standardBottomSheet)
        val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                BottomSheetBehavior.STATE_HIDDEN
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                val fraction = (slideOffset + 1f) / 2f
            }
        }
        standardBottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)
        standardBottomSheetBehavior.saveFlags = BottomSheetBehavior.SAVE_ALL
    }
    /*private fun animateStandardBottomSheetStates() {
        standardBottomSheet.postDelayed({
            standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }, 1000L)
        standardBottomSheet.postDelayed({
            standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }, 2000L)
        standardBottomSheet.postDelayed({
            standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        }, 3000L)
        standardBottomSheet.postDelayed({
            standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }, 4000L)
        standardBottomSheet.postDelayed({
            standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }, 5000L)
    }*/
}