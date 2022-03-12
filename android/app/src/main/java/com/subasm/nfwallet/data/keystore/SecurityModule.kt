package com.subasm.nfwallet.data.keystore

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SecurityModule {

    @Provides
    fun keyStoreWrapper(): KeyStoreWrapper = KeyStoreWrapper()

    @Provides
    fun cipherWrapper(): CipherWrapper = CipherWrapper()

    @Provides
    fun secureKeyStore(
        keyStoreWrapper: KeyStoreWrapper,
        cipherWrapper: CipherWrapper,
        sharedPreferences: SharedPreferences
    ): SecureKeyStore {
        return SecureKeyStore(
            keyStoreWrapper,
            cipherWrapper,
            sharedPreferences
        )
    }
}
