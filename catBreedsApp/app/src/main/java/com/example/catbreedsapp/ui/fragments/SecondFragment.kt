package com.example.catbreedsapp.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.catbreedsapp.data.adapters.viewPagerAdapter
import com.example.catbreedsapp.databinding.FragmentSecondBinding
import com.example.catbreedsapp.ui.MainActivity
import com.example.catbreedsapp.ui.MyViewModel
import com.google.android.material.tabs.TabLayoutMediator

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val myViewModel: MyViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar2)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar2.setTitleTextColor(Color.WHITE)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.selectedBreed.observe(viewLifecycleOwner) { breed ->
            (requireActivity() as MainActivity).supportActionBar?.title = breed.name
            Glide.with(this).load(breed.image?.url).into(binding.imageView)

            binding.floatingActionButton7.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(breed.wikipediaUrl)))
            }

            binding.floatingActionButton8.setOnClickListener {
                myViewModel.voteBreed(0, breed.image?.id.toString())
            }

            binding.floatingActionButton9.setOnClickListener {
                myViewModel.voteBreed(1, breed.image?.id.toString())
            }
        }

        with(binding) {
            val adapter = viewPagerAdapter(this@SecondFragment)
            viewpager.adapter = adapter

            TabLayoutMediator(tabLayout, viewpager) { tab, position ->
                tab.text = if(position == 1) "Stats" else "Information"
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}