package com.portfolioapps.calculator2.utils

data class CalculatorState (
    val number1 : String = "",
    val number2 : String = "",
    val operation : CalculatorOperation? = null,
    val result : Double = 0.0
)