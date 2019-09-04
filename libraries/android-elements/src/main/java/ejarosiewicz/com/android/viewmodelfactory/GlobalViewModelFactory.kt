package ejarosiewicz.com.android.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class GlobalViewModelFactory @Inject constructor(
    private val viewModelsMap
    : Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = viewModelsMap[modelClass]

        if (creator != null) {
            return creator.get() as T
        }

        throw IllegalArgumentException("unknown model class $modelClass")
    }
}