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
import ejarosiewicz.com.apptemplate.requester.usecase.data.NormalContent
import ejarosiewicz.com.requester.R

class NormalContentAdapterDelegate(private val context: Context,
                                   private val imageLoader: ImageLoader) : AdapterDelegate<List<DataToShow>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val layoutView = inflater.inflate(R.layout.view_content, parent, false)
        return NormalContentViewHolder(layoutView)
    }

    override fun isForViewType(items: List<DataToShow>, position: Int) =
        items[position] is NormalContent

    override fun onBindViewHolder(
        items: List<DataToShow>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val normalContentToBind = items[position] as NormalContent
        (holder as NormalContentViewHolder).text.text = normalContentToBind.text
        imageLoader.load(normalContentToBind.image, holder.image)
    }

    class NormalContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.text)
        val image: ImageView = view.findViewById(R.id.image)
    }

}