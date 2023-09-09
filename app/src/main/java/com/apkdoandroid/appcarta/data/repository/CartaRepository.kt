package com.apkdoandroid.appcarta.data.repository

import com.apkdoandroid.appcarta.data.model.Carta

interface CartaRepository {
    suspend fun recuperarCartas() : List<Carta>
}