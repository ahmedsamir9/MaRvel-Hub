package com.example.marvelhub.presentation.SearchScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelhub.R
import com.example.marvelhub.databinding.SearchFragmentBinding
import com.example.marvelhub.presentation.SearchScreen.adapters.CharactersPagingAdapter
import com.example.marvelhub.utils.Constants
import com.example.marvelhub.utils.LoadingAdapter
import com.example.marvelhub.utils.getQueryTextChangeStateFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding:SearchFragmentBinding
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var oldQuery:String
    private var job:Job? =null
    private lateinit var charactersAdapter : CharactersPagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        charactersAdapter = CharactersPagingAdapter(object : CharactersPagingAdapter.OnClickOnCharacterItem{
            override fun onClickOnCharacter(characterId: Int) {
                val navigateToDetailsFragmentAction = SearchFragmentDirections.actionSearchFragmentToCharacterDetailsFragment(characterId)
                findNavController().navigate(navigateToDetailsFragmentAction)
            }
        })
    }
    override fun onStart() {
        super.onStart()
        setUpRecyclerView()
        handleCharactersListState()

    }
    override fun onResume() {
        super.onResume()
        getCharacters()
        binding.cancelTxt.setOnClickListener {
            closeSearchViewState()
        }
    }
    private fun setUpRecyclerView(){
        binding.searchRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter= charactersAdapter.withLoadStateFooter(LoadingAdapter{charactersAdapter.retry()})
        }
    }


    private fun getCharacters(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
               return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!=null&&newText.isNotEmpty()){
                    job?.cancel()
                  job = lifecycleScope.launch {
                        viewModel.getCharactersByName(newText).debounce(2000).flowOn(Dispatchers.IO).collectLatest {
                            charactersAdapter.submitData(it)
                        }
                    }
                }
                return true
            }

        })


    }
    private fun closeSearchViewState(){
        binding.searchView.clearFocus()
        binding.searchView.setQuery("",false)
        charactersAdapter.submitData(lifecycle, PagingData.empty())
        job?.cancel()
    }
    private fun handleCharactersListState(){
        charactersAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading)
                if( charactersAdapter.itemCount <= 0)
                    binding.searchProgressBar.visibility = android.view.View.VISIBLE

            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }
            if(errorState == null||charactersAdapter.itemCount > 0){
                binding.searchRetryBtn.visibility = android.view.View.GONE
            }
            else
            {
                binding.searchProgressBar.visibility = android.view.View.GONE
                binding.searchRetryBtn.visibility = android.view.View.VISIBLE
                binding.searchRetryBtn.setOnClickListener {
                    charactersAdapter.retry()
                }
            }
            if (charactersAdapter.itemCount >0) binding.searchProgressBar.visibility = android.view.View.GONE
        }
    }
}