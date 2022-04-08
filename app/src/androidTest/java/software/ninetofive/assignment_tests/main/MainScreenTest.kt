package software.ninetofive.assignment_tests.main

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import software.ninetofive.assignment_tests.utils.AppPreferences
import software.ninetofive.assignment_tests.utils.InMemorySharedPreferences

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @BindValue
    val appPreferences = AppPreferences(InMemorySharedPreferences())

    @Test
    fun defaultSelectScreenOptionValue() {
        launchMainScreen {
            checkSelectedScreenHeaderShown()
            //TODO Bug! Default preference (SCREEN_C) is recognised as B on screen
            checkSelectedScreenOptionIs(SelectedScreen.SCREEN_B)
        }
    }

    @Test
    fun selectScreenOptionInteractions() {
        appPreferences.setStartScreen(SelectedScreen.SCREEN_A)

        launchMainScreen {
            checkSelectedScreenHeaderShown()
            checkSelectedScreenOptionIs(SelectedScreen.SCREEN_A)
            tapOnScreenBOption()
            checkSelectedScreenOptionIs(SelectedScreen.SCREEN_B)
            tapOnScreenCOption()
            checkSelectedScreenOptionIs(SelectedScreen.SCREEN_C)
            tapOnScreenAOption()
            checkSelectedScreenOptionIs(SelectedScreen.SCREEN_A)
        }
    }

    @Test
    fun defaultViewingOptionValue() {
        launchMainScreen {
            checkViewingOptionsHeader()
            checkSelectedViewingOptionIs(ViewingOption.NOTHING)
        }
    }

    @Test
    fun selectViewingOptionInteractions() {
        appPreferences.setViewingOption(ViewingOption.DATE)

        launchMainScreen {
            checkViewingOptionsHeader()
            checkSelectedViewingOptionIs(ViewingOption.DATE)
            tapOnNothingOption()
            checkSelectedViewingOptionIs(ViewingOption.NOTHING)
            tapOnShowNameOption()
            checkSelectedViewingOptionIs(ViewingOption.SHOW_NAME)
            tapOnDateOption()
            checkSelectedViewingOptionIs(ViewingOption.DATE)
        }
    }

    @Test
    fun defaultShowValidDotValue() {
        launchMainScreen {
            checkShowValidDotOptionSelected(false)
        }
    }
}