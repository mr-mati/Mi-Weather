package com.mati.miweather.util

import android.os.Build
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.chrono.HijrahDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

object DataTime {

    fun getCurrentTime(): String {
        val currentTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("HH:mm")
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        return currentTime.format(formatter)
    }

    fun getHour(): Int {
        val currentTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("HH:mm")
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        return currentTime.hour
    }

    fun getDay(): String {
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

    fun getMonth(): String {
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

    fun getYear(): String {
        val currentDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy")
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        return currentDate.format(formatter)
    }

    fun getCurrentData(): String {
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

    fun convertGregorianToHijri(gregorianDate: String): String {
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val gregorianLocalDate = LocalDate.parse(gregorianDate, formatter)
        val hijriDate = HijrahDate.from(gregorianLocalDate)
        val hijriFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale("fa", "IR"))
        return hijriFormatter.format(hijriDate)
    }


    fun convertDayHijri(gregorianDate: String): String {
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val gregorianLocalDate = LocalDate.parse(gregorianDate, formatter)
        val hijriDate = HijrahDate.from(gregorianLocalDate)
        val dayOfWeek = DayOfWeek.from(hijriDate)
        return dayOfWeek.getDisplayName(
            TextStyle.FULL, Locale("fa", "IR")
        )
    }


    fun getGregorianMonthName(): String? {
        return when (getMonth()) {
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

    fun getDayName(data: String): DayOfWeek {
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd", java.util.Locale.ENGLISH)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val date = LocalDate.parse(data, formatter)
        return date.dayOfWeek
    }

}