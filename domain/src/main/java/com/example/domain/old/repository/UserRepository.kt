package com.example.domain.old.repository

import com.example.domain.old.model.Profile
import com.example.domain.old.model.ShortInfoUser

interface UserRepository {

    fun getShortInfoUser() : ShortInfoUser

    fun getInfoUserProfile() : Profile
}