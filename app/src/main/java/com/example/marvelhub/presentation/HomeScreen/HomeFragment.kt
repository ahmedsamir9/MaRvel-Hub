package com.example.marvelhub.presentation.HomeScreen

import android.opengl.Visibility
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelhub.R
import com.example.marvelhub.databinding.HomeFragmentBinding
import com.example.marvelhub.presentation.HomeScreen.adapters.CharacterAdapter

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView
import com.example.marvelhub.utils.Status


@AndroidEntryPoint
class HomeFragment : Fragment(){
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding :HomeFragmentBinding
    private var isLoadOnce = false
    private lateinit var charactersAdapter: CharacterAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
      binding = HomeFragmentBinding.inflate(inflater,container,false);
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCharacters()
    }
    override fun onStart() {
        super.onStart()
        charactersAdapter= CharacterAdapter(ArrayList())
        setUpCharactersRecyclerView()
    }

    override fun onResume() {
        super.onResume()
setOnClickOnItem()
        subscribeOnLiveData()
    }
    private fun subscribeOnLiveData(){
        viewModel.character.observe(viewLifecycleOwner, Observer{ dataState->
            when(dataState.status){
                Status.SUCCESS->{
                    dataState.data?.let {
                        charactersAdapter.submitList(it)
                        viewVisibilityInErrorState(View.GONE)
                        viewVisibilityInLoadingState(View.GONE)
                        isLoadOnce = true
                    }
                }
                Status.LOADING->{
                    viewVisibilityInLoadingState(View.VISIBLE)
                }
                Status.ERROR->{
                    viewVisibilityInLoadingState(View.GONE)
                    viewVisibilityInErrorState(View.VISIBLE)
                }
            }
        })
    }
    private fun setUpCharactersRecyclerView(){

        binding.charectersRv.apply {
            layoutManager =LinearLayoutManager(context)
            adapter= charactersAdapter
        }
        binding.charectersRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.getCharacters()
                }
            }
        })
    }
    private fun viewVisibilityInErrorState(visibility:Int){
        if (isLoadOnce)
        binding.loadMoreRetryBtn.visibility =visibility
        else
            binding.mainRetryBtn.visibility =visibility
    }

    private fun viewVisibilityInLoadingState(visibility:Int){
        if (isLoadOnce)
            binding.loadMoreProgressBar.visibility =visibility
        else
            binding.mainprogressBar.visibility =visibility
    }
    private fun setOnClickOnItem(){
        binding.mainRetryBtn.setOnClickListener {
            viewModel.getCharacters()
        }
        binding.loadMoreRetryBtn.setOnClickListener {
            viewModel.getCharacters()
        }

    }
}