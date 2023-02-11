package com.angelsanchue.roomtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import androidx.room.RoomDatabase
import com.angelsanchue.roomtest.db.*
import com.facebook.stetho.Stetho
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var room : AppDataBase
    lateinit var etname : EditText
    lateinit var etLastName : EditText
    lateinit var btnGuardar : Button
    lateinit var spnnrContries : Spinner
    var dataCountries : List<String> = listOf()

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Stetho.initializeWithDefaults(this)
        setContentView(R.layout.activity_main)




        room = Room.databaseBuilder(this, AppDataBase::class.java, "AppDataBase").build()


        initSpinner()

        runBlocking {
            val settings = room.getSettingDAO().getAllSettings()
            if (settings.isEmpty()){
                initSettings(dataSettings())
            }
            else{
                println("No se agregaron las configuraciones")
            }
        }


        //val country1 = Country(0,"México")
        val sett = Settings(null, "date_shot", true.toString())
        //println(getNamesCountries())
        //agregarPais(country1)
       //addSetting(sett)
        //addSetting("transition",1.toString())
        //addSetting("save_microSD",true.toString())

            etname = findViewById(R.id.etName)
            etLastName = findViewById(R.id.etLastName)
            btnGuardar = findViewById(R.id.btnSave)

            btnGuardar.setOnClickListener {
                if (etname.text.isNotBlank() && etLastName.text.isNotBlank()) {
                    guardarPersona(Person(0, etname.text.toString(), etLastName.text.toString(), 1))

                    Log.e("ETName", "Se agregó ${etname.text} correctamente")
                } else {
                    Log.e("ETName", "Else")
                }
                imprimirPersonas()
        }

        val tvLogOut : TextView = findViewById(R.id.tvLogOut)

        tvLogOut.setOnClickListener{
            logOut()
        }
    }

    fun agregarPais(country: Country){
        lifecycleScope.launch{
            room.getCountryDAO().insertCountry(country)
        }
    }

    fun obtenerPaises() : List<Country> {
        lateinit var listCountries : List<Country>
        lifecycleScope.launch {
         listCountries = room.getCountryDAO().getAllCountries()
        }

        return listCountries

    }

    fun guardarPersona(persona : Person){

        lifecycleScope.launch {
            room.getPersonDAO().insertPerson(persona)
        }
        limpiarCampos()

    }

    fun imprimirPersonas(){
        lateinit var personas : List<Person>
        lifecycleScope.launch {
            personas = room.getPersonDAO().getAllPersons()
            for (value in personas){
                println(value)
            }
        }
    }

    fun limpiarCampos(){
        etname.text.clear()
        etLastName.text.clear()
    }

    fun addSetting(setting : Settings){
        lifecycleScope.launch{
            room.getSettingDAO().insertSetting(setting)
        }
    }

    fun initSettings(listSettings : List<Settings>){
            lifecycleScope.launch {
                for (value in listSettings){
                    room.getSettingDAO().insertSetting(value)
                }

            }
    }

   /* fun getNamesCountries() : List<String>{
        lifecycleScope.launch {
            room.getCountryDAO().getAllNameCountries()
        }
    }*/

    fun initSpinner(){


        spnnrContries = findViewById(R.id.spnnrCountry)

        val job = lifecycleScope.launch {
            dataCountries = room.getCountryDAO().getAllNameCountries()
            println("Dentro $dataCountries")

            val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item,dataCountries)

            spnnrContries.adapter = adapter
        }

        println("Fuera: $dataCountries")





    }

    fun dataSettings() : List<Settings> {


        val listSett : MutableList<Settings>  = mutableListOf()
        listSett.add(Settings(null,"Usa Android","false"))
        listSett.add(Settings(null,"Usa iOS","false"))
        listSett.add(Settings(null,"Usa WindowsPhone","false"))

        return listSett
    }

    fun logOut(){
        Firebase.auth.signOut()
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }

}