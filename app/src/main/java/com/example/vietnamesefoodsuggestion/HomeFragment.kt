package com.example.vietnamesefoodsuggestion;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.navigation.fragment.findNavController;
import com.example.vietnamesefoodsuggestion.databinding.FragmentHomeBinding;
import java.util.Timer;
import java.util.TimerTask;
import androidx.viewpager2.widget.ViewPager2;
import java.util.*;
import java.util.concurrent.TimeUnit;
import android.widget.ImageView;

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var binding: FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuSpinner = view.findViewById<android.widget.Spinner>(R.id.menu_spinner)
        val menuItems = arrayOf("Thoát")
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, menuItems)
        menuSpinner.adapter = adapter

        menuSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = menuItems[position]
                if (selectedItem == "Thoát") {
                    //findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        val homeButtonFooter = view.findViewById<android.widget.Button>(R.id.home_button_footer)
        homeButtonFooter?.setOnClickListener {
        }

        val profileButtonFooter =
            view.findViewById<android.widget.Button>(R.id.profile_button_footer)
        profileButtonFooter?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        binding.suggestFoodButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_selectionFragment)
        }

        val memberImages = listOf(
            R.drawable.ic_ms_ha,
            R.drawable.ic_mr_thinh,
            R.drawable.ic_mr_nghiem,
            R.drawable.ic_mr_danh
        )

        binding.memberImageView1.setImageResource(memberImages[0])
        binding.memberImageView2.setImageResource(memberImages[1])
        binding.memberImageView3.setImageResource(memberImages[2])
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}