package com.example.myfirstapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.myfirstapp.R
import com.example.myfirstapp.databinding.FragmentSecondBinding
import com.example.myfirstapp.domain.GameState
import com.example.myfirstapp.domain.TicTacToe
import com.example.myfirstapp.observer.Observer
import com.example.myfirstapp.service.Service

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TicTacToeFragment : Fragment(), Observer {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val cellsIDs: List<Int> = arrayListOf(
        R.id.imageView0, R.id.imageView1, R.id.imageView2, R.id.imageView3,
        R.id.imageView4, R.id.imageView5, R.id.imageView6, R.id.imageView7, R.id.imageView8
    )

    private lateinit var game: TicTacToe
    private val service: Service = Service()
    private lateinit var view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_MainMenu)
        }

        service.addObserver(this)
        this.view = view
        gameSetup()
        startGame()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleCellClicked(cellID: Int, index: Int){
        if(!game.isPositionEmpty(index)) {
            return
        }

        when(game.getState()) {
            GameState.PLAYER_TURN -> {
                view.findViewById<ImageView>(cellID).setImageResource(R.drawable.x)
                println(game.getState())
                game.setPlayerPosition(index)
                service.checkTicTacToeStatus(game)
            }

            GameState.ENEMY_TURN -> {
                view.findViewById<ImageView>(cellID).setImageResource(R.drawable.o)
                println(game.getState())
                game.setEnemyPosition(index)
                service.checkTicTacToeStatus(game)
            }

            else -> {
                //ignore
            }
        }
    }

    private fun gameSetup(){
        for(cellID in cellsIDs){
            view.findViewById<ImageView>(cellID).setOnClickListener {
                val index = view.findViewById<ImageView>(cellID).contentDescription.toString().toInt()
                handleCellClicked(cellID, index)
                println(index)
            }
        }
    }

    fun startGame(){
        game = TicTacToe()

        for(cellID in cellsIDs){
            view.findViewById<ImageView>(cellID).setImageResource(R.drawable.blank)
        }
    }

    override fun update() {
        Thread.sleep(1000)
        startGame()
    }
}