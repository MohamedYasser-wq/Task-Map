package com.example.myassignment.Fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myassignment.R
import com.example.myassignment.databinding.FragmentSplashBinding

class splashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding= FragmentSplashBinding.inflate(layoutInflater)
        navController = Navigation.findNavController(container!!)

        Handler(Looper.getMainLooper()).postDelayed({

            navController!!.navigate(R.id.action_splashFragment_to_searchFragment)

        }, 2000)


        return binding.root
    }


}