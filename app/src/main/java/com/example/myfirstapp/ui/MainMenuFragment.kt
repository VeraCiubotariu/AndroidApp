package com.example.myfirstapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myfirstapp.R
import com.example.myfirstapp.databinding.FragmentFirstBinding
import com.example.myfirstapp.databinding.FragmentMainMenuBinding

class MainMenuFragment: Fragment() {
    private var _binding: FragmentMainMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      /*  view.findViewById<Button>(R.id.ticTacToeButton).setOnClickListener {
             findNavController().navigate(R.id.)
        }*/

        binding.ticTacToeButton.setOnClickListener {
             findNavController().navigate(R.id.action_MainMenu_to_SecondFragment)
        }

        binding.numberGenButton.setOnClickListener {
            findNavController().navigate(R.id.action_MainMenu_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}