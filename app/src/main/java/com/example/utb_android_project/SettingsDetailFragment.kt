package com.example.utb_android_project

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.utb_android_project.databinding.DetailSettingsFragmentBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

 data class Settings(var name:String?, var rounds:Number?, var breath:Number?, var secondsToHold:Number?)



class SettingsDetailFragment : Fragment() {

    private var _binding: DetailSettingsFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DetailSettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val initialSettings = Settings(null,null,null,null)

        binding.numberOfBreaths.addTextChangedListener(object: TextWatcher {
            var original:CharSequence? = null
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        original = if (s?.isNotEmpty() == true){s}else{null}
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    Log.d("textChange",s.toString())
                    val asInt = s.toString().toInt()
                        initialSettings.breath = asInt
                }
                catch (e: java.lang.NumberFormatException) {
                    Log.e("NumberFormat",e.message?:"")
                    initialSettings.breath = original.toString().toInt()
                    binding.numberOfBreaths.setText(original)
                }
            }
            override fun afterTextChanged(s: Editable?) {}

        })

        binding.numberOfRounds.addTextChangedListener(object: TextWatcher {
            var original:Number? = null
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s != null) {
                    original = if (s.isNotEmpty()){s.toString().toInt()}else{null}
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    val asInt = s.toString().toInt()
                    initialSettings.rounds = asInt
                    binding.numberOfRounds.setText(asInt.toString())
                }
                catch (e: java.lang.NumberFormatException) {
                    Log.e("NumberFormat",e.message?:"")
                    initialSettings.rounds = original
                    binding.numberOfRounds.setText(original.toString())
                }
            }
            override fun afterTextChanged(s: Editable?) {}

        })

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }


        binding.buttonSave.setOnClickListener {
            Log.d("Save","Saved")
        }


        binding.buttonStart.setOnClickListener{
            findNavController().navigate(R.id.action_SecondFragment_to_appActiveFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}