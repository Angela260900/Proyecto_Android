package com.example.eliza.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eliza.models.Money
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class MoneyViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _money: MutableLiveData<Money> by lazy { MutableLiveData<Money>() }
    val money: LiveData<Money> = _money

    fun getMoney(context: Context) {
        viewModelScope.launch {
            db.collection("money").document("contabilidad").get().addOnCanceledListener {
                Toast.makeText(context, "Ha ocurrido un error", Toast.LENGTH_LONG).show()
            }.addOnSuccessListener {
                _money.postValue(it.toObject<Money>())
            }
        }
    }
}