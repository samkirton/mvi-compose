package com.subasm.nfwallet.ui.app.wallet.createaddress

import androidx.lifecycle.ViewModel
import com.subasm.nfwallet.ui.redux.controller.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateAddressViewModel @Inject constructor(
    reducer: CreateAddressReducer
) : ViewModel(), Reducer<CreateAddressAction, CreateAddressResult, CreateAddressViewState> by reducer
