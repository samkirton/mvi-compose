package com.subasm.nfgen.ui.app.token.fungible

import androidx.lifecycle.ViewModel
import com.subasm.nfgen.ui.redux.controller.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FungibleTokenViewModel @Inject constructor(
    reducer: FungibleTokenReducer
) : ViewModel(), Reducer<FungibleTokenAction, FungibleTokenResult, FungibleTokenViewState> by reducer
