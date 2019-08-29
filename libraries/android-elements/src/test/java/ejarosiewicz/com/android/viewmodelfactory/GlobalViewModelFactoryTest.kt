package ejarosiewicz.com.android.viewmodelfactory

import androidx.lifecycle.ViewModel
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import javax.inject.Provider

class GlobalViewModelFactoryTest {

    class DummyViewModel: ViewModel()

    val dummyViewModel = DummyViewModel()
    val dummyViewModelProvider = Provider<ViewModel> { dummyViewModel }

    @Test
    fun `Return existing view model if has class type`() {
        val dummyMap = mapOf<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>(
            GlobalViewModelFactoryTest.DummyViewModel::class.java to dummyViewModelProvider
        )
        val systemUnderTest = GlobalViewModelFactory(dummyMap)

        val expectedViewModel = systemUnderTest.create(DummyViewModel::class.java)

        assertk.assertThat(expectedViewModel).isEqualTo(dummyViewModel)
    }


    @Test
    fun `Throw exception if view model not found`() {
        assertThrows<IllegalArgumentException> {
            val systemUnderTest = GlobalViewModelFactory(emptyMap())

            systemUnderTest.create(DummyViewModel::class.java)
        }
    }
}