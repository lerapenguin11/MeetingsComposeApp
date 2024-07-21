package com.example.composeprotject.domain.repository

import com.example.composeprotject.domain.model.Profile
import com.example.composeprotject.domain.model.nav.ShortInfoUser

interface UserRepository {

    fun getShortInfoUser() : ShortInfoUser

    fun getInfoUserProfile() : Profile
}