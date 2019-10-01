package ejarosiewicz.com.apptemplate.databaser.view.adapter

import android.content.Context
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ejarosiewicz.com.android.imageloader.ImageLoader
import ejarosiewicz.com.apptemplate.databaser.usecase.data.DataToShow

class DatabaserAdapter(context: Context, imageLoader: ImageLoader) : ListDelegationAdapter<List<DataToShow>>(
    TextAdapterDelegate(context)
)