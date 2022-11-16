package com.example.eliza.models

data class Money(
    var assets: Float = 0f,
    var passives: Float = 0f,
    var patrimony: Float = 0f
) {
    fun setPatrimony() {
        patrimony = assets - passives
    }
}
