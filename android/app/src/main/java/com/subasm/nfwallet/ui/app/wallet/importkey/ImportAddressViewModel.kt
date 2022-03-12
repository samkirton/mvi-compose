package com.subasm.nfwallet.ui.app.wallet.importkey

import androidx.lifecycle.ViewModel
import com.subasm.nfwallet.ui.redux.controller.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImportAddressViewModel @Inject constructor(
    reducer: ImportAddressReducer
) : ViewModel(), Reducer<ImportAddressAction, ImportAddressResult, ImportAddressViewState> by reducer
