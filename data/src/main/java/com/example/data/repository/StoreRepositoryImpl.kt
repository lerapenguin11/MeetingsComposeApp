package com.example.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
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
        getEncryptedSharedPreferences(context = context).edit().putString(AUTH_TOKEN, token).apply()
    }

    override fun readeAuthToken(): Flow<String?> {
        return flow {
            emit(
                value = getEncryptedSharedPreferences(context = context).getString(AUTH_TOKEN, null)
            )
        }
    }

    override suspend fun deleteAuthToken() {
        getEncryptedSharedPreferences(context = context).edit().remove(AUTH_TOKEN).apply()
    }

    override suspend fun saveIsShowMyCommunities(isOn: Boolean) {
        getSharedPreferences(context = context, name = PREF_APP_SETTINGS).edit {
            putBoolean(IS_SHOW_MY_COMMUNITIES, isOn)
        }
    }

    override fun readIsShowMyCommunities(): Flow<Boolean> {
        return flow {
            emit(
                value = getSharedPreferences(
                    context = context,
                    name = PREF_APP_SETTINGS
                ).getBoolean(
                    IS_SHOW_MY_COMMUNITIES, DEFAULT_IS_SHOW
                )
            )
        }
    }

    override suspend fun saveIsShowMyEvents(isOn: Boolean) {
        getSharedPreferences(context = context, name = PREF_APP_SETTINGS).edit {
            putBoolean(IS_SHOW_MY_EVENTS, isOn)
        }
    }

    override fun readIsShowMyEvents(): Flow<Boolean> {
        return flow {
            emit(
                value = getSharedPreferences(
                    context = context,
                    name = PREF_APP_SETTINGS
                ).getBoolean(
                    IS_SHOW_MY_EVENTS, DEFAULT_IS_SHOW
                )
            )
        }
    }

    override suspend fun saveIsEnableNotifications(isOn: Boolean) {
        getSharedPreferences(context = context, name = PREF_APP_SETTINGS).edit {
            putBoolean(IS_ENABLE_NOTIFICATION, isOn)
        }
    }

    override fun readIsEnableNotifications(): Flow<Boolean> {
        return flow {
            emit(
                value = getSharedPreferences(
                    context = context,
                    name = PREF_APP_SETTINGS
                ).getBoolean(
                    IS_ENABLE_NOTIFICATION, DEFAULT_IS_SHOW
                )
            )
        }
    }

    companion object {
        private fun getSharedPreferences(context: Context, name: String): SharedPreferences {
            return context.getSharedPreferences(name, Context.MODE_PRIVATE)
        }

        private fun getEncryptedSharedPreferences(context: Context): SharedPreferences {
            val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
            return EncryptedSharedPreferences.create(
                ENCRYPTED_PREF,
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }

        private const val IS_ONBOARDING_INTEREST_SHOW_KEY = "is_onboarding_show"
        private const val PREF_APP_SETTINGS = "pref_app_settings"
        private const val USER_CITY = "user_city"
        private const val DEFAULT_CITY = "Москва"
        private const val AUTH_TOKEN = "auth_token"
        private const val ENCRYPTED_PREF = "encrypted_pref"
        private const val DEFAULT_IS_SHOW = true
        private const val IS_SHOW_MY_COMMUNITIES = "is_show_my_communities"
        private const val IS_SHOW_MY_EVENTS = "is_show_my_events"
        private const val IS_ENABLE_NOTIFICATION = "is_enable_notification"
    }
}