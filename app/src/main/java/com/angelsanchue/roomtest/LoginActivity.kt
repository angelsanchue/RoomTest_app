package com.angelsanchue.roomtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth



class LoginActivity : AppCompatActivity() {

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnSignInGoogle : com.google.android.gms.common.SignInButton = findViewById(R.id.btnGoogle)



        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build())

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setIsSmartLockEnabled(false)
            .build()

        btnSignInGoogle.setOnClickListener { signInLauncher.launch(signInIntent) }


    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser

            Toast.makeText(this,"Bienvenido: ${user!!.displayName}",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()

        } else {
            Toast.makeText(this,"Ha ocurrido un error",Toast.LENGTH_LONG).show()
        }
    }
}