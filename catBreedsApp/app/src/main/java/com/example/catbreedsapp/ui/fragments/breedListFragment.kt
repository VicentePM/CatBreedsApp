package com.example.catbreedsapp.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.catbreedsapp.R
import com.example.catbreedsapp.data.adapters.breedsAdapter
import com.example.catbreedsapp.data.models.breedList.Breed
import com.example.catbreedsapp.databinding.FragmentBreedListBinding
import com.example.catbreedsapp.ui.MainActivity
import com.example.catbreedsapp.ui.MyViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class breedListFragment : Fragment() {

    private var _binding: FragmentBreedListBinding? = null
    private lateinit var adapter: breedsAdapter
    private val myViewModel by activityViewModels<MyViewModel> {
        MyViewModel.MyViewModelFactory(requireContext())
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBreedListBinding.inflate(inflater, container, false)

        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (requireActivity() as MainActivity).supportActionBar?.title = "Cat Breeds"
        binding.toolbar.setTitleTextColor(Color.WHITE)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listAdapter = breedsAdapter(object : breedsAdapter.OnItemClickListener{
            override fun onItemClick(breed: Breed) {
                myViewModel.selectedBreed.value = breed
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        })

        val recyclerView = binding.recyclerview
        adapter = listAdapter
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        binding.swipe.setSize(SwipeRefreshLayout.LARGE)
        binding.swipe.setOnRefreshListener {
            myViewModel.getBreeds()
        }

        myViewModel.breedsLiveData.observe(viewLifecycleOwner) {
            binding.swipe.isRefreshing = true

            binding.swipe.setOnRefreshListener {
                if (it != null) {
                    listAdapter.update(it)
                    binding.swipe.isRefreshing = false
                }
            }

            if (it != null) {
                listAdapter.update(it)
                binding.swipe.isRefreshing = false
            }
        }

        myViewModel.getBreeds()

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)

                val menuItem = menu.findItem(R.id.app_bar_search)
                val searchView = menuItem.actionView as SearchView

                searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        adapter.filter.filter(p0)
                        return true
                    }

                    override fun onQueryTextChange(p0: String?): Boolean {
                        adapter.filter.filter(p0)
                        return true
                    }
                })
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menuUp -> {
                        adapter.shortList(true)
                        true
                    }

                    R.id.menuDown -> {
                        adapter.shortList(false)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_votesFragment)
        }
   }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}