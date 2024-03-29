package ejarosiewicz.com.apptemplate.requester.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import ejarosiewicz.com.android.imageloader.ImageLoader
import ejarosiewicz.com.apptemplate.requester.usecase.data.DataToShow
import ejarosiewicz.com.apptemplate.requester.usecase.data.Header
import ejarosiewicz.com.requester.R

class HeaderAdapterDelegate(private val context: Context,
                            private val imageLoader: ImageLoader) : AdapterDelegate<List<DataToShow>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val layoutView = inflater.inflate(R.layout.view_header, parent, false)
        return HeaderViewHolder(layoutView)
    }

    override fun isForViewType(items: List<DataToShow>, position: Int) =
        items[position] is Header

    override fun onBindViewHolder(
        items: List<DataToShow>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val headerToBind = items[position] as Header
        (holder as HeaderViewHolder).text.text = headerToBind.text
        imageLoader.load(headerToBind.image, holder.image)
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.text)
        val image: ImageView = view.findViewById(R.id.image)
    }

}