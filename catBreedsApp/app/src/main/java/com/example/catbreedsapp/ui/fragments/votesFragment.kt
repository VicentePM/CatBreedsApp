package com.example.catbreedsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.catbreedsapp.data.adapters.votesAdapter
import com.example.catbreedsapp.data.models.votesList.Votes
import com.example.catbreedsapp.databinding.FragmentVotesBinding
import com.example.catbreedsapp.ui.MainActivity
import com.example.catbreedsapp.ui.MyViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class votesFragment : Fragment() {

    private var _binding: FragmentVotesBinding? = null
    private lateinit var adapter: votesAdapter
    private val myViewModel: MyViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentVotesBinding.inflate(inflater, container, false)

        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as MainActivity).title = "My Votes"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerview

        adapter = votesAdapter(object : votesAdapter.OnItemCLickListener {
            override fun onItemClick(vote: Votes) {
                myViewModel.delVote((vote.id.toString()))
            }
        })

        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        binding.swipe.setSize(SwipeRefreshLayout.LARGE)
        binding.swipe.setOnRefreshListener {
            myViewModel.getVoteList()
        }

        myViewModel.voteListLiveData.observe(viewLifecycleOwner) {
            binding.swipe.isRefreshing = true

            binding.swipe.setOnRefreshListener {
                if (it != null) {
                    adapter.update(it)
                    binding.swipe.isRefreshing = false
                }
            }

            if (it != null) {
                adapter.update(it)
                binding.swipe.isRefreshing = false
            }
        }

        myViewModel.getVoteList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}