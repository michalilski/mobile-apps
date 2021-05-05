package com.example.irc

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.irc.chat.ChatMessage
import com.example.irc.user.Config
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var userProviders : ArrayList<AuthUI.IdpConfig>
    private lateinit var chatView : RecyclerView
    private lateinit var messageInput : EditText
    private lateinit var sendMessageButton : Button
    private lateinit var dbReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userProviders = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())
        val db = Firebase.database
        dbReference = db.getReference("chat-content")
        setupUI()
//        startLoginActivity()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Config.SIGN_IN_CODE) {
            IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                Config.currentUser = FirebaseAuth.getInstance().currentUser!!
                setupSuccessUI()
            } else {
                Toast.makeText(this, "Login failed.", Toast.LENGTH_SHORT).show()
                setupFailUI()
            }
        }
    }

    private fun startLoginActivity() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false)
                .setAvailableProviders(userProviders)
                .build(),
            Config.SIGN_IN_CODE)
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        startLoginActivity()
//    }

    private fun setupUI() {
        chatView = findViewById(R.id.chatView)
        messageInput = findViewById(R.id.messageInput)
        sendMessageButton = findViewById(R.id.sendMessageButton)
        sendMessageButton.setOnClickListener {
            sendMessage()
        }
    }

    private fun setupFailUI() {
        messageInput.focusable = EditText.NOT_FOCUSABLE
        sendMessageButton.focusable = Button.NOT_FOCUSABLE
    }
    private fun setupSuccessUI() {
        messageInput.focusable = EditText.FOCUSABLE
        sendMessageButton.focusable = Button.FOCUSABLE
    }
    private fun sendMessage() {
        val messageContent = messageInput.text.toString()
        if(messageContent.replace("\\s".toRegex(), "") != "") {
            dbReference
                .push()
                .setValue(ChatMessage(
                    messageContent,
                    "mail",//Config.currentUser.email!!,
                    Date().time
                ))
            messageInput.text.clear()
        }
    }
}