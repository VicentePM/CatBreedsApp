package com.example.catbreedsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.catbreedsapp.databinding.FragmentStatsBinding
import com.example.catbreedsapp.ui.MyViewModel

class statsFragment : Fragment() {

    lateinit var binding: FragmentStatsBinding
    private val myViewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStatsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.selectedBreed.observe(viewLifecycleOwner) {
            with(binding) {
                tvIndoor.text = it.indoor.toString()
                tvAdaptability.text = it.adaptability.toString()
                tvAffection.text = it.affectionLevel.toString()
                tvChild.text = it.childFriendly.toString()
                tvDog.text = it.dogFriendly.toString()
                tvEnergy.text = it.energyLevel.toString()
                tvGrooming.text = it.grooming.toString()
                tvHealth.text = it.healthIssues.toString()
                tvIntelligence.text = it.intelligence.toString()
            }
        }
    }

}