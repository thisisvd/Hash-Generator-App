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
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hashgeneratorapp.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    // Data binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // View model
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.apply {

            // Menu setup
            setUpHomeMenu()

            // on generate button clicked
            generateButton.setOnClickListener {
                onGenerateClicked()
            }
        }
        return binding.root
    }

    // Get hash data form view model
    private fun getHashData(): String {
        binding.apply {

            // getting data & returning
            val algorithm = autoCompleteTextView.text.toString()
            val plainText = plainText.text.toString()

            return homeViewModel.getHash(plainText, algorithm)
        }
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
    private fun navigateToSuccess(hash: String) {
        val directions = HomeFragmentDirections.actionHomeFragmentToSuccessFragment(hash)
        findNavController().navigate(directions)
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
                            binding.plainText.text.clear()
                            showSnackBar("Cleared")
                            true
                        }

                        else -> false
                    }
                }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        }
    }

    // On generate button click
    private fun onGenerateClicked() {
        binding.apply {

            if (plainText.text.isEmpty()) {
                showSnackBar("Field empty.")
            } else {
                // Animation and next fragment navigation  setup
                lifecycleScope.launch {
                    applyAnimation()
                    navigateToSuccess(getHashData())
                }
            }
        }
    }

    // Snack bar method
    private fun showSnackBar(message: String) {
        val snackBar = Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        )
        snackBar.setAction("Okay") {
            // do nothing
        }
        snackBar.setActionTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
        snackBar.show()
    }

    override fun onResume() {
        super.onResume()
        binding.apply {

            // Auto text view adapter setup
            val hashAlgorithms = resources.getStringArray(R.array.hash_algorithms)
            val arrayAdapter =
                ArrayAdapter(requireContext(), R.layout.drop_down_item, hashAlgorithms)
            autoCompleteTextView.setAdapter(arrayAdapter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}