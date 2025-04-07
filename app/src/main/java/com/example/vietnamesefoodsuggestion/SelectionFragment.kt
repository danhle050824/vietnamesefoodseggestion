package com.example.vietnamesefoodsuggestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.vietnamesefoodsuggestion.data.PredictRequest
import com.example.vietnamesefoodsuggestion.data.PredictResponse
import com.example.vietnamesefoodsuggestion.databinding.FragmentSelectionBinding
import com.example.vietnamesefoodsuggestion.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        binding.typeSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf(
                "com",
                "pho",
                "bun",
                "banh",
                "nui",
                "xoi",
                "che",
                "spaghetti",
                "mi",
                "ramen",
                "hu tieu",
                "banh_mi",
                "lau",
                "thit",
                "ca"
            )
        )
        binding.flavorSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf("thanh", "ngot", "man", "chua", "cay", "beo")
        )
        binding.mealSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf("an_nhe", "mon_chinh", "trang_mieng")
        )
        binding.processSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf("chien", "cuon", "hap", "ham", "lamlanh", "nau", "nuong", "song", "tron", "xao")
        )
        binding.dietSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf("0", "1")
        )

        binding.submitButton.setOnClickListener {
            val selectedType = binding.typeSpinner.selectedItem.toString()
            val selectedFlavour = binding.flavorSpinner.selectedItem.toString()
            val selectedMeal = binding.mealSpinner.selectedItem.toString()
            val selectedProcess = binding.processSpinner.selectedItem.toString()
            val selectedDiet = binding.dietSpinner.selectedItem.toString()

            val request = PredictRequest(
                type = selectedType,
                flavour = selectedFlavour,
                meal = selectedMeal,
                process = selectedProcess,
                vegetarian = selectedDiet.toInt().toString()
            )

            RetrofitClient.instance.predictFood(request)
                .enqueue(object : Callback<PredictResponse> {
                    override fun onResponse(
                        call: Call<PredictResponse>,
                        response: Response<PredictResponse>
                    ) {
                        if (response.isSuccessful) {
                            val predictionResponse = response.body()
                            if (predictionResponse != null) {
                                val recommendedFood = predictionResponse.recommendation
                                val action =
                                    SelectionFragmentDirections.actionSelectionFragmentToResultFragment()
                                action.recommendedFood = recommendedFood
                                findNavController().navigate(action)
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Không nhận được dữ liệu dự đoán",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Lỗi khi gọi API: ${response.errorBody()?.string()}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
                        Toast.makeText(
                            requireContext(),
                            "Lỗi mạng: ${t.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }
    }
}