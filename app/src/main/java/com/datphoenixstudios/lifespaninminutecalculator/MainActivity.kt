package com.datphoenixstudios.lifespaninminutecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDatePicker: Button = findViewById(R.id.buttonDate)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        buttonDatePicker.setOnClickListener {
            clickDatePicker()
        }

    }

    private fun clickDatePicker() {

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, month, dayOfMonth ->
                Toast.makeText(
                    this,
                    "Year was $year months was ${month + 1} and day was $dayOfMonth",
                    Toast.LENGTH_LONG
                ).show()

                val selectedDate = "$dayOfMonth/${month + 1}/$year"

                tvSelectedDate?.text = selectedDate

                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.GERMAN)

                val theDate = simpleDateFormat.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate =
                        simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        tvAgeInMinutes?.text = differenceInMinutes.toString()
                    }
                }
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
        datePickerDialog.show()


    }

}