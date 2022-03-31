package com.udemy.dobcalc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.udemy.dobcalc.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() {
        binding.btnSeleccionarFecha.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val datePicker = DatePickerFragment { year, month, day -> onDateSelected(year, month, day) }
        datePicker.show(supportFragmentManager, "datePicker")
    }


    private fun onDateSelected(year: Int, month: Int, day: Int) {
        val date = "$day/${month + 1}/$year"
        binding.tvFecha.text = date

        val simpleDF = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val theDate = simpleDF.parse(date)
        theDate?.let {
            val selectedDateInMinutes = theDate.time / 60000
            val currentDate = simpleDF.parse(simpleDF.format(System.currentTimeMillis()))
            currentDate?.let {
                val currentDateInMinutes = currentDate.time / 60000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                binding.tvMinutosValue.text = differenceInMinutes.toString()
            }
        }

    }
}