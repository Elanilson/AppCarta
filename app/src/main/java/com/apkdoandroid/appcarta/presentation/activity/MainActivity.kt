package com.apkdoandroid.appcarta.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.apkdoandroid.appcarta.R
import com.apkdoandroid.appcarta.data.model.Carta
import com.apkdoandroid.appcarta.databinding.ActivityMainBinding
import com.apkdoandroid.appcarta.presentation.CartaoAdapter
import com.apkdoandroid.appcarta.presentation.viewmodel.CartoesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val cartoesViewModel: CartoesViewModel by viewModels()
    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private lateinit var cartaoAdapter: CartaoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )
        inicializarRecyclerView()
        inicializarObservables()
    }

    private fun inicializarRecyclerView() {

        cartaoAdapter = CartaoAdapter()
        binding.rvCartas.adapter = cartaoAdapter
        binding.rvCartas.layoutManager = GridLayoutManager(this, 3)

    }

    private fun inicializarObservables() {

        cartoesViewModel.listaCartas.observe(this){ listaCartas ->
            /*var lista = ""
            listaCartas.forEach { carta ->
                lista += " ${carta.cardId} \n"
            }
            Log.i("mensagem_api_cartas", lista )*/

            val listaCartasNova = mutableListOf<Carta>()
            listaCartas.forEach { carta ->
                if ( carta.img != null )
                    listaCartasNova.add(carta)
            }

            if( listaCartasNova.isNotEmpty() ){
                cartaoAdapter.atualizarLista( listaCartasNova )
            }

        }

    }

    override fun onStart() {
        super.onStart()
        cartoesViewModel.recuperarCartoes()
    }
}