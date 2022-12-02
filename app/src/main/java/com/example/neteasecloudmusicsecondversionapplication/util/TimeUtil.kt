package com.example.neteasecloudmusicsecondversionapplication.util

object TimeUtil {
    fun time (duration:Int) :String{
        val minute: Int = duration / 1000 / 60
        val second: Int = duration / 1000 % 60
        var stringMinute: String? = null
        var stringSecond: String? = null
        stringMinute = if (minute < 10) {
            "0$minute"
        } else {
            minute.toString() + ""
        }
        stringSecond = if (second < 10) {
            "0$second"
        } else {
            second.toString() + ""
        }
        return "$stringMinute:$stringSecond"
    }
}