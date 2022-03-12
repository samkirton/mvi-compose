package com.subasm.nfgen.domain.nft

data class NFTItems(
    val items: List<NFTItem>
)

data class NFTItem(
    val tokenId: String,
    val uri: String
)
