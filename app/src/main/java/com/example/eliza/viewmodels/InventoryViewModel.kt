package com.example.eliza.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eliza.models.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class InventoryViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _products: MutableLiveData<List<Product?>> by lazy { MutableLiveData<List<Product?>>() }
    val products: LiveData<List<Product?>> = _products

    fun getProducts(context: Context) {
        viewModelScope.launch {
            db.collection("products").get().addOnCanceledListener {
                Toast.makeText(
                    context,
                    "Hubo un problema, intente de nuevo mas tarde",
                    Toast.LENGTH_LONG
                ).show()
            }.addOnSuccessListener {
                val currentProducts = mutableListOf<Product?>()
                it.documents.map { document ->
                    currentProducts.add(document.toObject<Product>())
                }
                _products.postValue(currentProducts)
            }
        }
    }

}