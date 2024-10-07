package com.example.data.mappers

import android.text.TextUtils
import com.example.domain.model.interest.Interest
import com.example.domain.model.signUp.UserParam
import com.example.network.responseModel.auth.AuthParam
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

class SingUpMapper {

    fun userParamToAuthParam(userParam: UserParam): AuthParam {
        return AuthParam(
            name = userParam.name,
            interests = userInterestToInterestId(userParam.userInterests),
            phone = userParam.phoneNumber
        )
    }

    private fun typeConvectorListIdToUriId(ids: List<Int>): String? {
        return try {
            URLEncoder.encode(TextUtils.join(",", ids), "utf-8")
        } catch (e: UnsupportedEncodingException) {
            null
        }
    }

    private fun userInterestToInterestId(userInterests: List<Interest>?): String? {
        return if (userInterests != null) {
            typeConvectorListIdToUriId(ids = userInterests.map { it.id })
        } else null
    }
}