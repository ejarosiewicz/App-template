package ejarosiewicz.com.apptemplate.requester.view.adapter

import android.content.Context
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ejarosiewicz.com.android.imageloader.ImageLoader
import ejarosiewicz.com.apptemplate.requester.usecase.data.DataToShow

class RequestAdapter(context: Context, imageLoader: ImageLoader) : ListDelegationAdapter<List<DataToShow>>(
    HeaderAdapterDelegate(context, imageLoader),
    NormalContentAdapterDelegate(context, imageLoader),
    TextOnlyAdapterDelegate(context)
)