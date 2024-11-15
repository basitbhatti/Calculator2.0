package com.portfolioapps.calculator2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.portfolioapps.calculator2.utils.CalculatorAction
import com.portfolioapps.calculator2.utils.CalculatorOperation
import com.portfolioapps.calculator2.utils.CalculatorState

class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Delete -> deleteNumber()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Calculate -> performCalculation()
            is CalculatorAction.Operation -> enterOperation(action.operation)
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (state.number1.isNotBlank()) {
            state = state.copy(
                operation = operation
            )
        }
    }

    private fun performCalculation() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number1.toDoubleOrNull()
        if (number1 != null && number2 != null){
            state = state.copy( result = when (state.operation){
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> number1 / number2
                null -> return
            })
        }
    }

    private fun enterDecimal() {
        if (state.operation == null && !state.number1.contains(".") && state.number1.isNotBlank()){
            state = state.copy(
                number1 = state.number1+"."
            )
            return
        }

        if (!state.number2.contains(".") && state.number2.isNotBlank()){
            state = state.copy(
                number2 = state.number2+"."
            )
        }
    }

    private fun deleteNumber() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )

            state.operation != null -> state = state.copy(
                operation = null
            )

            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null){
            if (state.number1.length >= MAX_LENGTH){
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
        } else {
            if (state.number2.length >= MAX_LENGTH){
                return
            }
            state = state.copy(
                number2 = state.number2 + number
            )
        }
    }


    companion object {
        private val MAX_LENGTH = 8
    }


}