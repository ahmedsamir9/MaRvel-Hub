package com.example.marvelhub.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.marvelhub.R
import com.example.marvelhub.databinding.FragmentSplashBinding

import eightbitlab.com.blurview.RenderScriptBlur

import android.graphics.drawable.Drawable
import android.view.*
import androidx.navigation.fragment.findNavController
import com.example.marvelhub.utils.startBlur
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.BlurView
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        //binding.mainBackground.setBlurImageWithGlide(R.drawable.mcu_background)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.blurView.startBlur(requireActivity())
        navigateToMainScreen()
    }
    private fun navigateToMainScreen(){
        val backgroundExecutor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
        backgroundExecutor.schedule({
            activity?.runOnUiThread {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }

        }, 3, TimeUnit.SECONDS)

    }
}