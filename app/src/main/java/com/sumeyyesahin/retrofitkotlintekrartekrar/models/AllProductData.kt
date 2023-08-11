package com.sumeyyesahin.retrofitkotlintekrartekrar.models

data class AllProductData(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)