package com.mati.miweather.ui.feature.DataTime

import android.os.Build
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object DataTime {

    fun getCurrentTime(): String {
        val currentTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val formatter =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                DateTimeFormatter.ofPattern("HH:mm")
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        return currentTime.format(formatter)
    }

    fun getBackGroundTime(): Int {
        val currentTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val formatter =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                DateTimeFormatter.ofPattern("HH:mm")
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        return currentTime.hour
    }

    fun getCurrentDate(): String {
        val currentDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("dd")
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        return currentDate.format(formatter)
    }

    fun getregorianMonthDate(): String {
        val currentDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("MM")
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        return currentDate.format(formatter)
    }

    fun getGregorianMonthName(): String? {
        return when (getregorianMonthDate()) {
            "1" -> "January"
            "2" -> "February"
            "3" -> "March"
            "4" -> "April"
            "5" -> "May"
            "6" -> "June"
            "7" -> "July"
            "8" -> "August"
            "9" -> "September"
            "10" -> "October"
            "11" -> "November"
            "12" -> "December"
            else -> null
        }
    }

    fun getDataForDay(): String {
        val currentDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        return currentDate.format(formatter)
    }

    fun getDayOfWeekFromDate(): DayOfWeek {
        val dateString = getDataForDay()
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd", java.util.Locale.ENGLISH)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val date = LocalDate.parse(dateString, formatter)
        return date.dayOfWeek
    }
}