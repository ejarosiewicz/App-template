package ejarosiewicz.com.apptemplate.requester.view.adapter

import android.content.Context
import android.view.LayoutInflater
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ejarosiewicz.com.apptemplate.requester.usecase.data.DataToShow

class RequestAdapter(context: Context) : ListDelegationAdapter<List<DataToShow>>(
    HeaderAdapterDelegate(context),
    NormalContentAdapterDelegate(context),
    TextOnlyAdapterDelegate(context)
)