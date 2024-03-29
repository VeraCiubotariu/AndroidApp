package com.example.myfirstapp.domain

import kotlin.collections.ArrayList

class TicTacToe {
    private var positions: MutableList<Int> = arrayListOf(0, 0, 0,
                                                   0, 0, 0,
                                                   0, 0, 0);
    private var state: GameState = GameState.PLAYER_TURN;

    /**
     * returns the player's marked positions, if player = 1
     *         the enemy's marked positions, if player = 2
     *         the empty positions, if player = 0
     */
    private fun getPositions(player: Int): List<Int> {
        var res: MutableList<Int> = ArrayList<Int>();

        for (i in 0..8){
            if(positions[i] == player){
                res.add(i);
            }
        }

        return res;
    }

    public fun getPlayerPositions(): List<Int> {
        return getPositions(1);
    }

    public fun getEnemyPositions(): List<Int> {
        return getPositions(2);
    }

    public fun getEmptyPositions(): List<Int> {
        return getPositions(0);
    }

    private fun setPosition(index: Int, player: Int){
        positions[index] = player;
    }

    /**
     * returns false, if the positions was previously set
     *         true, otherwise
     */
    fun isPositionEmpty(index: Int): Boolean {
        if(positions[index] == 0){
            return true
        }

        return false
    }

    fun setPlayerPosition(index: Int) {
        setPosition(index, 1)
    }

    fun setEnemyPosition(index: Int){
        setPosition(index, 2)
    }

    public fun getState(): GameState {
        return state;
    }

    public fun setState(newState: GameState){
        state = newState;
    }
}