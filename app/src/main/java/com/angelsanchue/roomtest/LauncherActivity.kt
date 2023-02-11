package com.angelsanchue.roomtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class LauncherActivity : AppCompatActivity() {

    private val authUser : FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        goTo()

    }

    private fun goTo(){
        if (authUser.currentUser != null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        else{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }


}