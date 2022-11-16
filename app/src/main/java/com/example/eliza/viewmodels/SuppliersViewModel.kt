package com.example.eliza.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eliza.models.Supplier
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class SuppliersViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _suppliers: MutableLiveData<List<Supplier?>> by lazy { MutableLiveData<List<Supplier?>>() }
    val suppliers: LiveData<List<Supplier?>> = _suppliers

    fun getSuppliers(context: Context) {
        viewModelScope.launch {
            db.collection("suppliers").get().addOnCanceledListener {
                Toast.makeText(
                    context,
                    "Hubo un problema, intente de nuevo mas tarde",
                    Toast.LENGTH_LONG
                ).show()
            }.addOnSuccessListener {
                val currentSuppliers = mutableListOf<Supplier?>()
                it.documents.map { document ->
                    currentSuppliers.add(document.toObject<Supplier>())
                }
                _suppliers.postValue(currentSuppliers)
            }
        }
    }
}