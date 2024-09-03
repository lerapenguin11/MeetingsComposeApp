package com.example.domain.old.model

data class Profile(
    val userName : String,
    val userSurname : String?,
    val phoneNumber : String,
    val avatarUrl : String?,
    /*TODO????*/
    val twitterId : String,
    val instagramId : String,
    val linkedinId : String,
    val facebookId : String
)
