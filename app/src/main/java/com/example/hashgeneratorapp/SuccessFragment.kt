package com.example.hashgeneratorapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.hashgeneratorapp.databinding.FragmentSuccessBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SuccessFragment : Fragment() {

    // Data binding
    private var _binding: FragmentSuccessBinding? = null
    private val binding get() = _binding!!

    // Nav-args
    private val args: SuccessFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSuccessBinding.inflate(inflater, container, false)
        binding.apply {

            // Setup up hash text in textview
            hashTextView.text = args.hash

            // Copy btn click listener
            copyButton.setOnClickListener {
                lifecycleScope.launch {
                    onCopyClipboard(args.hash)
                    applyAnimation()
                }
            }
        }
        return binding.root
    }

    // Copy text to clipboard
    private fun onCopyClipboard(hash: String) {
        val clipboardManager =
            requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Encrypted Text", hash)
        clipboardManager.setPrimaryClip(clipData)
    }

    // Applying copy animation
    private suspend fun applyAnimation() {
        binding.includeView.apply {
            messageBackground.animate().translationY(80f).duration = 200L
            messageTextview.animate().translationY(80f).duration = 200L

            delay(2000L)

            messageBackground.animate().translationY(-80f).duration = 500L
            messageTextview.animate().translationY(-80f).duration = 500L
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}