package com.example.myfirstapp.service

import com.example.myfirstapp.domain.GameState
import com.example.myfirstapp.domain.TicTacToe
import com.example.myfirstapp.observer.Observable
import com.example.myfirstapp.observer.Observer

class Service : Observable {
    private val observers: MutableList<Observer> = ArrayList<Observer>()

    fun checkTicTacToeStatus(game: TicTacToe){
        when(game.getState()){
            GameState.PLAYER_TURN -> {
                if(isGameWon(game.getPlayerPositions())){
                    game.setState(GameState.WIN)
                    notifyObservers()
                }

                else if(game.getEmptyPositions().isEmpty()){
                    game.setState(GameState.TIE)
                    notifyObservers()
                }

                else{
                    game.setState(GameState.ENEMY_TURN)
                }
            }

            else -> {
                if(isGameWon(game.getEnemyPositions())){
                    game.setState(GameState.LOSS)
                    notifyObservers()
                }

                else if(game.getEmptyPositions().isEmpty()){
                    game.setState(GameState.TIE)
                    notifyObservers()
                }

                else{
                    game.setState(GameState.PLAYER_TURN)
                }
            }
        }
    }

    private fun isGameWon(playerPositions: List<Int>): Boolean {
        val winningPositions: List<List<Int>> = arrayListOf(arrayListOf(0, 1, 2), arrayListOf(3, 4, 5),
            arrayListOf(6, 7, 8), arrayListOf(0, 4, 8), arrayListOf(2, 4, 6), arrayListOf(0, 3, 6),
            arrayListOf(1, 4, 7), arrayListOf(2, 5, 8)
        )
        println(playerPositions)

        for(winningPosition in winningPositions){
            if(playerPositions.containsAll(winningPosition)){
                return true
            }
        }

        return false
    }

    override fun addObserver(o: Observer) {
        observers.add(o)
    }

    override fun removeObserver(o: Observer) {
        observers.remove(o)
    }

    override fun notifyObservers() {
        observers.forEach(Observer::update)
    }
}