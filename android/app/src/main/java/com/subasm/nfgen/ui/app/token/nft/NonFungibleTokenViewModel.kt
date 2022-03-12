package com.subasm.nfgen.ui.app.token.nft

import androidx.lifecycle.ViewModel
import com.subasm.nfgen.ui.redux.controller.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NonFungibleTokenViewModel @Inject constructor(
    reducer: NonFungibleTokenReducer
) : ViewModel(), Reducer<NonFungibleTokenAction, NonFungibleTokenResult, NonFungibleTokenViewState> by reducer
