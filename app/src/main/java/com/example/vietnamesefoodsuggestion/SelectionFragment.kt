package com.example.vietnamesefoodsuggestion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.vietnamesefoodsuggestion.databinding.FragmentSelectionBinding

class SelectionFragment : Fragment() {
    private lateinit var binding: FragmentSelectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Populate Spinners
        binding.flavorSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf("Thanh", "Ngọt", "Mặn", "Chua", "Cay", "Béo")
        )
        binding.typeSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf("Ăn nhẹ", "Món chính", "Tráng miệng")
        )
        binding.methodSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf("Chiên", "Cuốn", "Hấp", "Hầm", "Làm Lạnh", "Nấu", "Nướng", "Sống", "Trộn")
        )
        binding.dietSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf("Chay", "Mặn")
        )
        binding.regionSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf("Bắc", "Trung", "Nam")
        )

        binding.submitButton.setOnClickListener {
            val action = SelectionFragmentDirections.actionSelectionFragmentToResultFragment()
            findNavController().navigate(action)
        }
    }
}