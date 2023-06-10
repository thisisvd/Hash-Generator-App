package com.example.hashgeneratorapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hashgeneratorapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    // Data binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.apply {

            // Menu setup
            setUpHomeMenu()

            // Auto text view adapter setup
            val hashAlgorithms = resources.getStringArray(R.array.hash_algorithms)
            val arrayAdapter =
                ArrayAdapter(requireContext(), R.layout.drop_down_item, hashAlgorithms)
            autoCompleteTextView.setAdapter(arrayAdapter)

            // Animation with click listener setup
            generateButton.setOnClickListener {
                lifecycleScope.launch {
                    applyAnimation()
                    navigateToSuccess()
                }
            }

        }
        return binding.root
    }

    // Animations setup
    private suspend fun applyAnimation() {
        binding.apply {

            // Making generate button un-clickable
            generateButton.isClickable = false

            // Disappearing views with  animation
            titleTextview.animate().alpha(0f).duration = 400L
            generateButton.animate().alpha(0f).duration = 400L
            textInputLayout.animate()
                .alpha(0f)
                .translationXBy(1200f)
                .duration = 400L
            plainText.animate()
                .alpha(0f)
                .translationXBy(-1200f)
                .duration = 400L

            delay(300L)

            // Appearing views with animation
            successBackground.animate().apply {
                alpha(1f).duration = 600L
                rotationBy(720f).duration = 600L
                scaleXBy(900f).duration = 800L
                scaleYBy(900f).duration = 800L
            }

            delay(500L)

            successImageView.animate().alpha(1f).duration = 1000L

            delay(1500L)
        }
    }

    // Navigate to success fragment
    private fun navigateToSuccess() {
        findNavController().navigate(R.id.action_homeFragment_to_successFragment)
    }

    // Menu setup method
    private fun setUpHomeMenu() {
        binding.apply {

            val menuHost: MenuHost = requireActivity()
            menuHost.addMenuProvider(object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    // Add menu items here
                    menuInflater.inflate(R.menu.home_menu, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    // Handle the menu selection
                    return when (menuItem.itemId) {
                        R.id.clear_menu -> {
                            Toast.makeText(requireContext(), "Clear", Toast.LENGTH_SHORT).show()
                            true
                        }

                        else -> false
                    }
                }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}