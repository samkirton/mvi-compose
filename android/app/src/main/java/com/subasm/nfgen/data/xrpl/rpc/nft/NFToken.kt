package com.subasm.nfgen.data.xrpl.rpc.nft

import com.google.common.primitives.UnsignedInteger
import org.xrpl.xrpl4j.model.client.XrplRequestParams
import org.xrpl.xrpl4j.model.client.XrplResult
import org.xrpl.xrpl4j.model.client.common.LedgerIndex
import org.xrpl.xrpl4j.model.transactions.Address
import org.xrpl.xrpl4j.model.transactions.Marker
import java.util.Optional

data class NfTokenObject(
    val TokenID: String,
    val URI: String?
)

data class AccountNftsResult(
    val account: Address,
    val account_nfts: List<NfTokenObject>,
    val ledgerIndex: LedgerIndex?,
    val ledgerCurrentIndex: LedgerIndex?,
    val validated: Boolean,
    val limit: UnsignedInteger?,
    val marker: Marker?,
    val status: String
) : XrplResult {
    override fun status(): Optional<String> {
        return Optional.of(status)
    }
}

data class AccountNftsRequestParams(
    val account: Address,
    val limit: UnsignedInteger? = null,
    val marker: Marker? = null
) : XrplRequestParams
