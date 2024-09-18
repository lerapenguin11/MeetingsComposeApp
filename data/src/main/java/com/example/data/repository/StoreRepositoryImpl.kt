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

    override suspend fun saveUserCity(city: String?) {
        if (city != null) {
            getSharedPreferences(context = context, name = PREF_APP_SETTINGS).edit {
                putString(USER_CITY, city)
            }
        }
    }

    override fun readUserCity(): Flow<String?> {
        return flow {
            emit(
                value = getSharedPreferences(
                    context = context,
                    name = PREF_APP_SETTINGS
                ).getString(
                    USER_CITY, DEFAULT_CITY
                )
            )
        }
    }

    override suspend fun saveAuthToken(token: String) {
        getSharedPreferences(context = context, name = PREF_APP_SETTINGS).edit {
            putString(AUTH_TOKEN, token)
        }
    }

    override fun readeAuthToken(): Flow<String?> {
        return flow {
            emit(
                value = getSharedPreferences(
                    context = context,
                    name = PREF_APP_SETTINGS
                ).getString(
                    AUTH_TOKEN, null
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
        private const val USER_CITY = "user_city"
        private const val DEFAULT_CITY = "Москва"
        private const val AUTH_TOKEN = "auth_token"
    }
}