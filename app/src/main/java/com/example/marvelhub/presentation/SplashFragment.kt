package com.example.marvelhub.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.marvelhub.R
import com.example.marvelhub.databinding.FragmentSplashBinding
import com.example.marvelhub.utils.setBlurImageWithGlide
import eightbitlab.com.blurview.RenderScriptBlur

import android.graphics.drawable.Drawable
import android.view.*
import androidx.navigation.fragment.findNavController
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit


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
        startBlurTheImage()
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
    private fun startBlurTheImage(){
        val radius = 20f
        val decorView: View = requireActivity().window.decorView ;
        val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
        val windowBackground = decorView.background
        binding.blurView.setupWith(rootView)
            .setFrameClearDrawable(windowBackground)
            .setBlurAlgorithm(RenderScriptBlur(context))
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(true) // Or false if it's in a scrolling container or might be animated

    }

}