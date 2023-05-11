package org.minutodedios.roperos

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.minutodedios.roperos.model.Item
import org.minutodedios.roperos.repositories.Result
import org.minutodedios.roperos.repositories.shirtRepository
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel
@Inject
constructor(
    private val shirtRepository: shirtRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _state: MutableState<ItemListState> = mutableStateOf(ItemListState())
    val state: State<ItemListState> = _state

    private val _stateItem: MutableState<ItemState> = mutableStateOf(ItemState())
    val stateItem: State<ItemState> = _stateItem


    init {
        getShirtList()
    }

    fun getShirtList() {
        shirtRepository.getShirtList().onEach { result ->
            when(result){
                is Result.Error -> {
                _state.value = ItemListState(error = result.message ?: "Error inesperado")
                }
                is Result.Loading -> {
                    _state.value = ItemListState(isLoading = true)
                }
                is Result.Success -> {
                    _state.value = ItemListState(items = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getShirt(shirtId: String){
        shirtRepository.getShirtById(shirtId).onEach {result->
            when(result){
                is Result.Error -> {
                _stateItem.value = ItemState(error = result.message ?: "Error inesperado")
            }
                is Result.Loading -> {
                _stateItem.value = ItemState(isLoading = true)
            }
                is Result.Success -> {
                _stateItem.value = ItemState(item = result.data)
            }
            }
        }.launchIn(viewModelScope)
    }

    fun updateShirt(item:Item,quantity: Int){
       val shirtEdited = item.copy(
           quantity = quantity
       )
        shirtRepository.updateShirt(item.id,shirtEdited)
    }


}
