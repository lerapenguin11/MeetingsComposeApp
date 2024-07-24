package com.example.domain.repository

import com.example.domain.model.Profile
import com.example.domain.model.ShortInfoUser

interface UserRepository {

    fun getShortInfoUser() : ShortInfoUser

    fun getInfoUserProfile() : Profile
}