package com.example.mathsolver

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mathsolver.rest.RestService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView : TextView
    private lateinit var formulaInput : EditText
    private lateinit var restService: RestService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newton.now.sh/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        resultTextView = findViewById(R.id.solvedFormulaTextView)
        formulaInput = findViewById(R.id.formulaInput)
        restService = retrofit.create(RestService::class.java)
    }

    private fun getSolution(call : Call<Solution>){
        Thread {
            try {
                val response = call.execute()
                resultTextView.text = response.body().toString()
            } catch (e : Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    fun simplify(v : View) {
        getSolution(restService.getResult("simplify", formulaInput.text.toString()))
    }
    fun derive(v : View) {
        getSolution(restService.getResult("derive", formulaInput.text.toString()))
    }
    fun integrate(v : View) {
        getSolution(restService.getResult("integrate", formulaInput.text.toString()))
    }
    fun cos(v : View) {
        getSolution(restService.getResult("cos", formulaInput.text.toString()))
    }
    fun sin(v : View) {
        getSolution(restService.getResult("sin", formulaInput.text.toString()))
    }
    fun log(v : View) {
        getSolution(restService.getResult("log", formulaInput.text.toString()))
    }
}