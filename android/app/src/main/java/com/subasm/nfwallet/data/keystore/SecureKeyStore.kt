package com.subasm.nfwallet.data.keystore

import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SecureKeyStore constructor(
    private val keyStoreWrapper: KeyStoreWrapper,
    private val cipherWrapper: CipherWrapper,
    private val sharedPreferences: SharedPreferences
) {

    fun get(key: String): Flow<SecureItem> = flow {
        emit(SecureItem(key, getSecureItemBytes(key)))
    }

    fun put(secureItem: SecureItem): Flow<SecureItem> = flow {
        keyStoreWrapper.createAndroidKeyStoreAsymmetricKey(secureItem.key)
        val keyPair = keyStoreWrapper.getAndroidKeyStoreAsymmetricKeyPair(secureItem.key)
        val encryptedData = cipherWrapper.encrypt(secureItem.value, keyPair?.public)
        sharedPreferences
            .edit()
            .putString(secureItem.key, encryptedData)
            .apply()
        emit(secureItem)
    }

    fun exists(key: String): Boolean {
        return sharedPreferences.getString(key, null) != null
    }

    fun keys(): List<String> {
        return sharedPreferences.all.entries.map { it.key }
    }

    fun values(): Flow<List<SecureItem>> = flow {
        val entries = sharedPreferences.all.entries
        emit(
            entries.map {
                SecureItem(it.key, getSecureItemBytes(it.key))
            }
        )
    }

    fun removeAll(): Flow<Unit> = flow {
        sharedPreferences.all.entries.map { alias ->
            keyStoreWrapper.deleteEntry(alias.key)
        }
        emit(Unit)
    }

    private fun getSecureItemBytes(key: String): ByteArray {
        val encryptedValue = sharedPreferences.getString(key, null)
        if (encryptedValue != null) {
            val keyPair = keyStoreWrapper.getAndroidKeyStoreAsymmetricKeyPair(key)
            return cipherWrapper.decrypt(encryptedValue, keyPair?.private)
        } else {
            throw NoSuchElementException()
        }
    }
}
