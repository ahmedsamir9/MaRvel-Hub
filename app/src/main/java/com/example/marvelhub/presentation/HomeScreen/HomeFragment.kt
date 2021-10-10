package com.example.marvelhub.presentation.HomeScreen

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelhub.databinding.HomeFragmentBinding

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import androidx.paging.LoadState

import androidx.recyclerview.widget.RecyclerView
import com.example.marvelhub.presentation.HomeScreen.adapters.CharacterPagingAdapter
import com.example.marvelhub.utils.LoadingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(){
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding :HomeFragmentBinding
    private lateinit var characterPagingAdapter: CharacterPagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
      binding = HomeFragmentBinding.inflate(inflater,container,false);
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        characterPagingAdapter = CharacterPagingAdapter(object :CharacterPagingAdapter.OnClickListenerOnCharacterItem{
            override fun onClickOnCharacter(characterId: Int) {
                val navigationActionToDetailScreen = HomeFragmentDirections.actionHomeFragmentToCharacterDetailsFragment(characterId)
                findNavController().navigate(navigationActionToDetailScreen)
            }

        })
        setUpCharactersRecyclerView()
        collectPagingData()
    }

    override fun onResume() {
        super.onResume()
        setOnClickOnItem()
        handleCharacterListState()

        binding.searchIc.setOnClickListener {
            val navigateToSearchScreenAction = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(navigateToSearchScreenAction)
        }
        binding.marvelLogo.setOnClickListener {
            binding.charectersRv.smoothScrollToPosition(0)
        }

    }
    private fun collectPagingData(){
        lifecycleScope.launch{
            viewModel.getCharacters().flowOn(Dispatchers.IO).collectLatest { pagingData->
               characterPagingAdapter.submitData(pagingData)
            }
        }
    }
    private fun setUpCharactersRecyclerView(){

        binding.charectersRv.apply {
            layoutManager =LinearLayoutManager(context)
            adapter= characterPagingAdapter.withLoadStateFooter(footer = LoadingAdapter{characterPagingAdapter.retry()})
        }
        binding.charectersRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                   toolBarDisappearingAnimation()
                } else if(dy < 0) {
                    toolBarAppearingAnimation()

                }
            }
        })
    }

    private fun setOnClickOnItem(){
        binding.mainRetryBtn.setOnClickListener {
            characterPagingAdapter.retry()
        }
    }
    private fun  toolBarDisappearingAnimation(){
        binding.toolbarCard.animate()
            .alpha(0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    binding.toolbarCard.visibility =View.GONE
                }
            })
        }
    private fun toolBarAppearingAnimation() {
        binding.toolbarCard.apply {
            animate()
                .alpha(1f)
                .setListener(null)
            visibility =View.VISIBLE

        }
    }
    private fun handleCharacterListState(){
        characterPagingAdapter.addLoadStateListener { loadState ->
        if (loadState.refresh is LoadState.Loading ||
            loadState.append is LoadState.Loading)
        // Show ProgressBar
            if( characterPagingAdapter.itemCount <= 0)
                binding.mainprogressBar.visibility = android.view.View.VISIBLE

                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                if(errorState == null||characterPagingAdapter.itemCount > 0){
                    binding.mainRetryBtn.visibility = android.view.View.GONE
                }
                else
                {
                    binding.mainprogressBar.visibility = android.view.View.GONE
                    binding.mainRetryBtn.visibility = android.view.View.VISIBLE
                    binding.mainRetryBtn.setOnClickListener {
                        characterPagingAdapter.retry()
                    }
                }


        if (characterPagingAdapter.itemCount >0) binding.mainprogressBar.visibility = android.view.View.GONE
    }
    }
}
