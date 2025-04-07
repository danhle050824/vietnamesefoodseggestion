package com.example.vietnamesefoodsuggestion

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vietnamesefoodsuggestion.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private val args: ResultFragmentArgs by navArgs()
    private val defaultFoodList = listOf("Phở", "Bún bò", "Bánh mì", "Cơm tấm")
    private var currentFoodList: List<String> = defaultFoodList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recommendedFood = args.recommendedFood
        Log.d("ResultFragment", "Recommended Food: $recommendedFood")

        if (!recommendedFood.isNullOrEmpty()) {
            currentFoodList = listOf(recommendedFood)
        } else {
            Toast.makeText(context, "Không có món ăn được gợi ý", Toast.LENGTH_SHORT).show()
            currentFoodList = defaultFoodList
        }

        binding.foodList.layoutManager = LinearLayoutManager(context)
        binding.foodList.adapter = FoodAdapter(currentFoodList) { food ->
            Toast.makeText(context, "$food đã được lưu", Toast.LENGTH_SHORT).show()
        }

        binding.searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filteredList =
                    currentFoodList.filter { it.contains(s.toString(), ignoreCase = true) }
                binding.foodList.adapter = FoodAdapter(filteredList) { food ->
                    Toast.makeText(context, "$food đã được lưu", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}

class FoodAdapter(private val foods: List<String>, private val onFavoriteClick: (String) -> Unit) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.food_name)
        val favoriteButton: ImageButton = itemView.findViewById(R.id.favorite_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foods[position]
        holder.name.text = food
        holder.favoriteButton.setOnClickListener { onFavoriteClick(food) }
    }

    override fun getItemCount() = foods.size
}