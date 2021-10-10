package com.example.marvelhub.presentation.DeatilsScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelhub.R
import com.example.marvelhub.databinding.CharacterDetailsFragmentBinding
import com.example.marvelhub.presentation.DeatilsScreen.adapter.EventPagingAdapter
import com.example.marvelhub.utils.LoadingAdapter
import com.example.marvelhub.utils.setImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private val characterDetailsFragmentArgs :CharacterDetailsFragmentArgs by navArgs()
    private lateinit var comicsPagingAdapter:EventPagingAdapter
    private lateinit var eventsPagingAdapter:EventPagingAdapter
    private lateinit var seriesPagingAdapter:EventPagingAdapter
    private lateinit var storiesPagingAdapter:EventPagingAdapter
    private val viewModel: CharacterDetailsViewModel by viewModels()
    private lateinit var binding :CharacterDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = CharacterDetailsFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCharacterData(characterDetailsFragmentArgs.charcterId)
        comicsPagingAdapter = EventPagingAdapter()
        eventsPagingAdapter = EventPagingAdapter()
        storiesPagingAdapter = EventPagingAdapter()
        seriesPagingAdapter = EventPagingAdapter()
    }

    override fun onStart() {
        super.onStart()
        subscribeOnliveData()
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        setUpRecyclerViews(binding.comicsRv,comicsPagingAdapter)
        setUpRecyclerViews(binding.eventRv,eventsPagingAdapter)
        setUpRecyclerViews(binding.seriesRv,seriesPagingAdapter)
      setUpRecyclerViews(binding.storiesRv,storiesPagingAdapter)

        collectComicsData()
        collectEventsData()
        collectSeriesData()
    }

    override fun onResume() {
        super.onResume()

        collectStoriesData()
        handleAdapterListState(binding.comicsProgressBar,binding.comicsRetryBtn,comicsPagingAdapter)
        handleAdapterListState(binding.eventProgressBar,binding.eventRetryBtn,eventsPagingAdapter)
        handleAdapterListState(binding.seriesProgressBar,binding.seriesRetryBtn,seriesPagingAdapter)
        handleAdapterListState(binding.storiesProgressBar,binding.seriesRetryBtn,storiesPagingAdapter)
    }
    private fun subscribeOnliveData(){
        viewModel.character.observe(viewLifecycleOwner, Observer { character->
            binding.characterName.text = character.name
            binding.characterImg.setImage(character.imagePath)
            binding.characterDescription.text = character.description
        })
    }
    private fun collectComicsData(){
        lifecycleScope.launch {
            viewModel.getCharacterComicsByID(characterDetailsFragmentArgs.charcterId).flowOn(Dispatchers.IO).collectLatest {
                comicsPagingAdapter.submitData(it)
            }
        }
    }
    private fun collectEventsData(){
        lifecycleScope.launch {
            viewModel.getCharacterEventsByID(characterDetailsFragmentArgs.charcterId).flowOn(Dispatchers.IO).collectLatest {
                eventsPagingAdapter.submitData(it)
            }
        }
    }
    private fun collectSeriesData(){
        lifecycleScope.launch {
            viewModel.getCharacterSeriesByID(characterDetailsFragmentArgs.charcterId).flowOn(Dispatchers.IO).collectLatest {
                seriesPagingAdapter.submitData(it)
            }
        }
    }
    private fun collectStoriesData(){
        lifecycleScope.launch {
            viewModel.getCharacterStoriesByID(characterDetailsFragmentArgs.charcterId).flowOn(Dispatchers.IO).collectLatest {
                storiesPagingAdapter.submitData(it)
            }
        }
    }
  private fun setUpRecyclerViews(recyclerView: RecyclerView,pagingAdapter: EventPagingAdapter){
        recyclerView.apply {
            layoutManager =LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
            adapter =pagingAdapter.withLoadStateFooter(LoadingAdapter{pagingAdapter.retry()})
        }
    }
    private fun handleAdapterListState(progressBar:ProgressBar,errorBtn:Button,adapter: EventPagingAdapter){
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading)
                if( adapter.itemCount <= 0)
                    progressBar.visibility = android.view.View.VISIBLE

            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }
            if(errorState == null||adapter.itemCount > 0){
                errorBtn.visibility = android.view.View.GONE
            }
            else
            {
                progressBar.visibility = android.view.View.GONE
                errorBtn.visibility = android.view.View.VISIBLE
               errorBtn.setOnClickListener {
                    adapter.retry()
                }
            }


            if (adapter.itemCount > 0) {progressBar.visibility = android.view.View.GONE}
        }
    }



}