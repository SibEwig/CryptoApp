package ru.pavel.zubko.apps.cryptoapp2.data.network.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoJsonContainerDto(
    @SerializedName("RAW")
    @Expose
    val json: JsonObject? = null
)
