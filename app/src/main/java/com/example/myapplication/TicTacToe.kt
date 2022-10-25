package com.example.myapplication

import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

class TicTacToe(view: View) {

    private val viewCurrent: View = view
    private val buttons = getButtons(viewCurrent)

    private val blank = "-"
    private var currentTurn = "X"


    private fun getButtons(view: View) : ArrayList<Button> {
        val buttonArray : ArrayList<Button> = ArrayList<Button>(9)
        for(index in 1..9){
            val resID: Int = view.context.resources.getIdentifier("button$index",
                    "id", view.context.packageName)
            val button: Button = view.findViewById(resID)
            buttonArray.add(button)
        }

        return buttonArray
    }


    fun buttonSetClickHandler() {
        for(button: Button in buttons){
            button.setOnClickListener {
                if(button.text == blank){
                    button.text = currentTurn
                    currentTurn = changeTurn(currentTurn)
                }
                if(checkGameOver()){
                    checkScore()
                }
            }
        }
    }

    fun resetSetClickHandler(){
        val button: Button = viewCurrent.findViewById(R.id.reset)
        button.setOnClickListener{
            for (button: Button in buttons){
                button.text = "-"
            }
            val score = viewCurrent.findViewById<TextView>(R.id.game_name_text)
            score.text = "TIC-TAC-TOE"
        }
    }


    private fun checkGameOver(): Boolean{
        for(button: Button in buttons){
            if (button.text == blank){
                return false
            }
        }
        return true
    }

    private fun changeTurn(turn: String): String{
        return if (turn == "X"){
            "O"
        }
        else{
            "X"
        }
    }

    private fun checkScore(){
        var Xpoints = 0
        var Opoints = 0
        val winGameHorizontal = arrayOf(0, 3, 6)
        val winGameVertical = arrayOf(0, 1, 2)
        val winGameRightDiagonalStart = 0
        val winGameLeftDiagonalStart = 2

        for (index: Int in winGameHorizontal){
            if (buttons[index].text == buttons[index + 1].text && buttons[index + 1].text  == buttons[index + 2].text){
                if (buttons[index].text == "X"){
                    Xpoints++
                }
                else{
                    Opoints++
                }
            }
        }

        for (index: Int in winGameVertical){
            if (buttons[index].text == buttons[index + 3].text && buttons[index + 3].text  == buttons[index + 6].text){
                if (buttons[index].text == "X"){
                    Xpoints++
                }
                else{
                    Opoints++
                }
            }
        }

        if (buttons[winGameRightDiagonalStart].text == buttons[winGameRightDiagonalStart + 4].text && buttons[winGameRightDiagonalStart + 4].text  == buttons[winGameRightDiagonalStart + 8].text){
            if (buttons[winGameRightDiagonalStart].text == "X"){
                Xpoints++
            }
            else{
                Opoints++
            }
        }

        if (buttons[winGameLeftDiagonalStart].text == buttons[winGameLeftDiagonalStart + 2].text && buttons[winGameLeftDiagonalStart + 2].text  == buttons[winGameLeftDiagonalStart + 4].text){
            if (buttons[winGameLeftDiagonalStart].text == "X"){
                Xpoints++
            }
            else{
                Opoints++
            }
        }

        val score = viewCurrent.findViewById<TextView>(R.id.game_name_text)
        score.text = "X-$Xpoints points and O-$Opoints points."
    }
}