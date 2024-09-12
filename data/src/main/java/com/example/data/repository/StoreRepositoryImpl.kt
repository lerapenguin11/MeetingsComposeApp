package com.example.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.domain.repository.store.StoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StoreRepositoryImpl(private val context: Context) : StoreRepository {

    override suspend fun saveOnBoardingInterestState(completed: Boolean) {
        getSharedPreferences(context = context, name = PREF_APP_SETTINGS).edit {
            putBoolean(IS_ONBOARDING_INTEREST_SHOW_KEY, completed)
        }
    }

    override fun readOnBoardingInterestState(): Flow<Boolean> {
        return flow {
            emit(
                value = getSharedPreferences(
                    context = context,
                    name = PREF_APP_SETTINGS
                ).getBoolean(
                    IS_ONBOARDING_INTEREST_SHOW_KEY, false
                )
            )
        }
    }

    companion object {
        private fun getSharedPreferences(context: Context, name: String): SharedPreferences {
            return context.getSharedPreferences(name, Context.MODE_PRIVATE)
        }

        private const val IS_ONBOARDING_INTEREST_SHOW_KEY = "is_onboarding_show"
        private const val PREF_APP_SETTINGS = "pref_app_settings"
    }
}