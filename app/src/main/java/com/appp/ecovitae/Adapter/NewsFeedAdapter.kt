 package com.appp.ecovitae.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.net.toUri
import com.appp.ecovitae.CheckBoxList
import com.appp.ecovitae.DataModel.NewsFeed.NewsFeed
import com.appp.ecovitae.GlideApp
import com.appp.ecovitae.R
import com.google.firebase.storage.FirebaseStorage

class NewsFeedAdapter(private val context: Context, modelArrayList: ArrayList<NewsFeed>) :
    BaseAdapter() {

    private var modelArrayList: ArrayList<NewsFeed>

    init {
        this.modelArrayList = modelArrayList
    }

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return modelArrayList.size
    }

    override fun getItem(position: Int): Any {
        return modelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        Log.i("opa? ", "opa")
        if (convertView == null) {


            holder = ViewHolder()
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.newsfeed_item, null, true)



            holder.tags = convertView!!.findViewById(R.id.tags) as TextView
            holder.title = convertView.findViewById(R.id.title) as TextView
            holder.text = convertView.findViewById(R.id.text) as TextView
            holder.image = convertView.findViewById(R.id.image) as ImageView

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }
        val storage = FirebaseStorage.getInstance()
// Create a reference to a file from a Google Cloud Storage URI


        //var myurl =
        val gsReference =
            storage.getReferenceFromUrl(modelArrayList[position].image!!.toUri().toString())
        GlideApp.with(context)
            .load(gsReference)
            .into(holder.image!!)

        holder.tags!!.text = "tags: + "+modelArrayList[position].tags!!.take(10)
        holder.title!!.text = modelArrayList[position].title
        holder.text!!.text = modelArrayList[position].text!!.take(80)


        /* holder.checkBox!!.setOnClickListener {
             val tempview = holder.checkBox!!.getTag(R.integer.btnplusview) as View
             val pos = holder.checkBox!!.tag as Int
             Log.i("change", "click")


             if (modelArrayList[pos].getSelecteds()) {
                 modelArrayList[pos].setSelecteds(false)
                 public_modelArrayList = modelArrayList
             } else {
                 modelArrayList[pos].setSelecteds(true)
                 public_modelArrayList = modelArrayList
             }
         }*/

        return convertView
    }

    private inner class ViewHolder {

        var tags: TextView? = null
        var image: ImageView? = null
        var title: TextView? = null
        var text: TextView? = null
    }

    companion object {
        lateinit var public_modelArrayList: ArrayList<CheckBoxList>
    }

}