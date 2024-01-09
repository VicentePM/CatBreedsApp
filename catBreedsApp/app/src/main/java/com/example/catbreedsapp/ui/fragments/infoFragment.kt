package com.example.catbreedsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.catbreedsapp.databinding.FragmentInfoBinding
import com.example.catbreedsapp.ui.MyViewModel

class infoFragment : Fragment() {

    lateinit var binding: FragmentInfoBinding
    private val myViewModel: MyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.selectedBreed.observe(viewLifecycleOwner) {
            with(binding){
                tvWeight.text = it.weight?.metric
                tvTemperament.text = it.temperament
                tvOrigin.text = it.origin
                tvLife.text = it.lifeSpan
                tvDescription.text = it.description
            }
        }
    }
}