package com.example.hashgeneratorapp

import androidx.lifecycle.ViewModel
import java.security.MessageDigest

class HomeViewModel : ViewModel() {

    // Converting or defining hash function
    fun getHash(plainText: String, algorithm: String): String {
        val bytes = MessageDigest.getInstance(algorithm).digest(plainText.toByteArray())
        return toHex(bytes)
    }

    // Converting byte-array to formatted text
    private fun toHex(byteArray: ByteArray): String {
        return byteArray.joinToString("") { "%02x".format(it) }
    }
}
