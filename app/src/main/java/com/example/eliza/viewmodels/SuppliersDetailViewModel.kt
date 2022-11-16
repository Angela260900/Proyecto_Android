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

class SuppliersDetailViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private var productsStringList = mutableListOf<String>()

    private val _products: MutableLiveData<List<Product?>> by lazy { MutableLiveData<List<Product?>>() }
    val products: LiveData<List<Product?>> = _products

    fun getProducts(context: Context) {
        viewModelScope.launch {
            val currentSupplierProducts = mutableListOf<Product?>()
            productsStringList.map {
                db.collection("products").document(it).get().addOnSuccessListener { document ->
                    currentSupplierProducts.add(document.toObject())
                    _products.postValue(currentSupplierProducts)
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

    fun setProductsList(productsList: List<String>) {
        productsStringList.clear()
        productsStringList.addAll(productsList)
    }

    fun updateProduct(item: Product, docName: String, context: Context, readyToFinishActivity: ReadyToFinishActivity) {
        db.collection("products").document(docName).set(item).addOnSuccessListener {
            Toast.makeText(
                context,
                "Productos agregados con exito!",
                Toast.LENGTH_SHORT
            ).show()
            updateMoney(context, item, readyToFinishActivity)
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
        readyToFinishActivity: ReadyToFinishActivity
    ) {
        viewModelScope.launch {
            db.collection("money").document("contabilidad").get().addOnSuccessListener {
                val money = it.toObject<Money>()
                money?.passives = money?.passives?.plus(product.price * product.extraQ)!!
                money.setPatrimony()
                db.collection("money").document("contabilidad").set(money).addOnSuccessListener {
                    Toast.makeText(
                        context,
                        "Cuentas actualizadas con exito!",
                        Toast.LENGTH_SHORT
                    ).show()
                    readyToFinishActivity.readyToFinishActivity()
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