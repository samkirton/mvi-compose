package com.subasm.nfgen.ui.app.wallet.createaddress

import com.subasm.nfgen.ui.redux.controller.Reducer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CreateAddressReducer @Inject constructor(
    private val createAddressUseCase: CreateAddressUseCase
) : Reducer<CreateAddressAction, CreateAddressResult, CreateAddressViewState> {

    override val actionSharedFlow: MutableSharedFlow<CreateAddressAction> = createMutableFlow()

    override fun dispatcher(action: CreateAddressAction): Flow<CreateAddressResult> = when (action) {
        CreateAddressAction.Idle -> flowOf(CreateAddressResult.Idle)
        CreateAddressAction.Start -> createAddressUseCase.generateAddress()
        CreateAddressAction.Back -> flowOf(CreateAddressResult.Back)
        CreateAddressAction.GoToWallet -> flowOf(CreateAddressResult.GoToWallet)
    }

    override fun reducer(
        previousState: CreateAddressViewState,
        result: CreateAddressResult
    ): CreateAddressViewState = when (result) {
        CreateAddressResult.Idle -> previousState.copy(
            navigate = CreateAddressViewState.Navigate.Idle
        )
        CreateAddressResult.OnProgress -> previousState.copy(
            view = CreateAddressViewState.View.OnProgress
        )
        is CreateAddressResult.AddressCreated -> previousState.copy(
            view = CreateAddressViewState.View.AddressCreated(
                result.address,
                result.seed
            )
        )
        is CreateAddressResult.OnError -> previousState.copy(
            view = CreateAddressViewState.View.OnError(result.error)
        )
        CreateAddressResult.Back -> previousState.copy(
            navigate = CreateAddressViewState.Navigate.Back
        )
        CreateAddressResult.GoToWallet -> previousState.copy(
            navigate = CreateAddressViewState.Navigate.GoToWallet
        )
    }
}
