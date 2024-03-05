package com.example.myfirstapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myfirstapp.R
import com.example.myfirstapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NumberFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleButtons(view);
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleButtons(view: View){
        var textView: TextView = view.findViewById<TextView>(R.id.textView);

        view.findViewById<Button>(R.id.toastButton).setOnClickListener {
            val myToast = Toast.makeText(context, "Yo, Mista White!", Toast.LENGTH_SHORT);
            myToast.show();
        }

        view.findViewById<Button>(R.id.countButton).setOnClickListener {
            var value = textView.text.toString().toInt();
            value += 1;
            println(value);

            textView.text = value.toString();
        }

        view.findViewById<Button>(R.id.randomButton).setOnClickListener {
            val value = kotlin.random.Random.nextInt(0, 1000);
            textView.text = value.toString();
        }
    }
}