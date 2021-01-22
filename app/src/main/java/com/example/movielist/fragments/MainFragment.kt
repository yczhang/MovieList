package com.example.movielist.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.R
import com.example.movielist.databinding.MainFragmentBinding
import com.example.movielist.viewmodels.MainViewModel
import com.example.movielist.views.MovieListAdapter

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var binding : MainFragmentBinding

    private var keyword = "naruto"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container,false)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setupObservers()

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this


        binding.svKeyword.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.loadMovieList(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })

        binding.svKeyword.isIconified = false // Expand it
        binding.svKeyword.setQuery(keyword, false) // true if you want to submit, otherwise false
        binding.svKeyword.clearFocus()

        viewModel.loadMovieList(keyword)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun setupObservers()
    {
        viewModel.isReady.observe(this.viewLifecycleOwner, Observer {
            if (it)
            {
                binding.rvList.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter =
                        MovieListAdapter(
                            viewModel.getItems()
                        )
                }
            }
            else
            {

            }
        })
    }

}