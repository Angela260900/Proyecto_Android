package com.example.eliza.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eliza.interfaces.ReadyToFinishActivity
import com.example.eliza.models.Money
import com.example.eliza.models.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class MakeSellViewModel : ViewModel() {
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
                    val product = document.toObject<Product>()
                    if (product?.quantity!! > 0)
                        currentProducts.add(product)
                }
                _products.postValue(currentProducts)
            }
        }
    }

    fun updateProduct(item: Product, context: Context, readyToFinish: ReadyToFinishActivity) {
        db.collection("products").document(item.name).set(item)
            .addOnSuccessListener {
                Toast.makeText(
                    context,
                    "Productos agregados con exito!",
                    Toast.LENGTH_SHORT
                ).show()
                updateMoney(context, item, readyToFinish)
            }.addOnCanceledListener {
            Toast.makeText(
                context,
                "Hubo un problema, intente de nuevo mas tarde",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun updateMoney(
        context: Context,
        product: Product,
        readyToFinish: ReadyToFinishActivity
    ) {
        viewModelScope.launch {
            db.collection("money").document("contabilidad").get().addOnSuccessListener {
                val money = it.toObject<Money>()
                money?.assets = money?.assets?.plus((product.price * 1.4f) * product.extraQ)!!
                money.setPatrimony()
                db.collection("money").document("contabilidad").set(money).addOnSuccessListener {
                    Toast.makeText(
                        context,
                        "Cuentas actualizadas con exito!",
                        Toast.LENGTH_SHORT
                    ).show()
                    readyToFinish.readyToFinishActivity()
                }.addOnCanceledListener {
                    Toast.makeText(
                        context,
                        "Hubo un problema, intente de nuevo mas tarde",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }.addOnCanceledListener {
                Toast.makeText(
                    context,
                    "Hubo un problema, intente de nuevo mas tarde",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}