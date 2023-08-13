package br.com.davidcastro.features.screens.listscreen.model

data class ListScreenArgs(
    val type: ListScreenType,
    val search: String = ""
)
