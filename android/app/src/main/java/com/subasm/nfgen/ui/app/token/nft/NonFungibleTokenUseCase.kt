package com.subasm.nfgen.ui.app.token.nft

import com.subasm.nfgen.domain.nft.NFTRepository
import com.subasm.nfgen.ui.domainError
import com.subasm.nfgen.ui.start
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NonFungibleTokenUseCase @Inject constructor(
    private val nftRepository: NFTRepository
) {

    fun getAccount(): Flow<NonFungibleTokenResult> {
        return nftRepository.getAccountNFTs().map { accountNft ->
            if (accountNft.items.isEmpty()) NonFungibleTokenResult.EmptyNFTCollection
            else NonFungibleTokenResult.EmptyNFTCollection
        }.domainError {
            NonFungibleTokenResult.OnError(it)
        }.start(NonFungibleTokenResult.OnProgress)
    }
}
