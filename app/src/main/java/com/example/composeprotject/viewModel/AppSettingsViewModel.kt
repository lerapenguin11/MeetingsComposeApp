package com.example.composeprotject.viewModel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel

class AppSettingsViewModel(application: Application) : AndroidViewModel(application = application) {

    var sherAppSettings: SharedPreferences =
        application.getSharedPreferences(PREF_APP_SETTINGS, Context.MODE_PRIVATE)

    fun updateIsOnboardingShow(isShow: Boolean) {
        sherAppSettings.edit().apply {
            putBoolean(IS_ONBOARDING_SHOW, isShow)
            apply()
        }
    }

    companion object {
        private const val IS_ONBOARDING_SHOW = "is_onboarding_show"
        private const val PREF_APP_SETTINGS = "pref_app_settings"
    }
}