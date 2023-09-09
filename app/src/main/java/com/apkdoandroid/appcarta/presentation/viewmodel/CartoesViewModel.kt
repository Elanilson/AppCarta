package com.apkdoandroid.appcarta.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apkdoandroid.appcarta.data.model.Carta
import com.apkdoandroid.appcarta.data.repository.CartaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartoesViewModel @Inject constructor(
    private val cartaRepository: CartaRepository
) : ViewModel() {

    private val _listaCartas = MutableLiveData<List<Carta>>()
    val listaCartas: LiveData<List<Carta>>
        get() = _listaCartas

    fun recuperarCartoes(){

        viewModelScope.launch {
            val lista = cartaRepository.recuperarCartas()
            _listaCartas.postValue( lista )
        }

    }

}