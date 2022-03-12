package com.subasm.nfgen.ui.app.wallet.importkey

import com.subasm.nfgen.ui.redux.controller.Reducer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ImportAddressReducer @Inject constructor(
    private val importAddressUseCase: ImportAddressUseCase
) : Reducer<ImportAddressAction, ImportAddressResult, ImportAddressViewState> {

    override val actionSharedFlow: MutableSharedFlow<ImportAddressAction> = createMutableFlow()

    override fun dispatcher(action: ImportAddressAction): Flow<ImportAddressResult> = when (action) {
        ImportAddressAction.Idle -> flowOf(ImportAddressResult.Idle)
        ImportAddressAction.Back -> flowOf(ImportAddressResult.Back)
        is ImportAddressAction.ImportSeed -> importAddressUseCase.importSeed(action.seed)
    }

    override fun reducer(
        previousState: ImportAddressViewState,
        result: ImportAddressResult
    ): ImportAddressViewState = when (result) {
        ImportAddressResult.Idle -> previousState.copy(
            navigate = ImportAddressViewState.Navigate.Idle
        )
        ImportAddressResult.OnProgress -> previousState.copy(
            view = ImportAddressViewState.View.OnProgress
        )
        is ImportAddressResult.OnError -> previousState.copy(
            view = ImportAddressViewState.View.DeviceNotSupported
        )
        ImportAddressResult.InvalidSeed -> previousState.copy(
            view = ImportAddressViewState.View.InvalidSeed
        )
        ImportAddressResult.SeedExists -> previousState.copy(
            view = ImportAddressViewState.View.SeedExists
        )
        ImportAddressResult.Back -> previousState.copy(
            navigate = ImportAddressViewState.Navigate.Back
        )
        ImportAddressResult.SeedImported -> previousState.copy(
            navigate = ImportAddressViewState.Navigate.GoToWallet
        )
    }
}
