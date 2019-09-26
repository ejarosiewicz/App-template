package ejarosiewicz.com.apptemplate.requester.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import ejarosiewicz.com.apptemplate.requester.usecase.data.DataToShow
import ejarosiewicz.com.apptemplate.requester.usecase.data.TextOnly
import ejarosiewicz.com.requester.R

class TextOnlyAdapterDelegate(private val context: Context) : AdapterDelegate<List<DataToShow>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val layoutView = inflater.inflate(R.layout.view_text, parent, false)
        return TextOnlyViewHolder(layoutView)
    }

    override fun isForViewType(items: List<DataToShow>, position: Int) =
        items[position] is TextOnly

    override fun onBindViewHolder(
        items: List<DataToShow>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val textOnlyToBind = items[position] as TextOnly
        (holder as TextOnlyViewHolder).text.text = textOnlyToBind.text
    }

    class TextOnlyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.textcontainer)
    }

}