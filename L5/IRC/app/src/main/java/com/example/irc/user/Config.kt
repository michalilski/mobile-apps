package com.example.irc.user

import com.google.firebase.auth.FirebaseUser

class Config {
    companion object {
        const val SIGN_IN_CODE = 100
        lateinit var currentUser : FirebaseUser
    }
}