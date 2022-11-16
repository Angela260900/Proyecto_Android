package com.example.eliza.ui.activities

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.eliza.R
import com.example.eliza.databinding.ActivitySellBinding
import com.example.eliza.interfaces.ReadyToFinishActivity
import com.example.eliza.models.Product
import com.example.eliza.viewmodels.MakeSellViewModel

class SellActivity : AppCompatActivity(), ReadyToFinishActivity {

    private lateinit var binding: ActivitySellBinding

    private var products: List<Product?> = emptyList()
    private var productSelected: Product? = null

    private var suggestions: MutableList<String> = mutableListOf()

    private val viewModel: MakeSellViewModel by viewModels()

    private var quantity = 0

    private var positionSelected = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViewModel()
        initViews()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun readyToFinishActivity() {
        finish()
    }

    private fun initViewModel() {
        viewModel.getProducts(this)

        viewModel.products.observe(this) {
            products = it
            it.map { product ->
                suggestions.add(product?.name!!)
            }
            if (products.isEmpty())
                emptyProducts()
            else
                binding.autoCompleteTextSearch.setAdapter(
                    ArrayAdapter(
                        this,
                        android.R.layout.simple_dropdown_item_1line,
                        suggestions
                    )
                )
        }
    }

    private fun initViews() {
        binding.autoCompleteTextSearch.setOnItemClickListener { parent, _, position, _ ->
            hideSoftKeyboard()
            binding.root.clearFocus()
            productSelected = products.first {
                it?.name == parent.getItemAtPosition(position).toString()
            }
            initAddProduct()
        }

        binding.buttonPlus.setOnClickListener {
            quantity++
            setEditText(binding.editTextNumber)
        }

        binding.buttonMinus.setOnClickListener {
            if (quantity > 0) {
                quantity--
                setEditText(binding.editTextNumber)
            }
        }

        binding.editTextNumber.addTextChangedListener {
            quantity = it.toString().toInt()
        }

        binding.buttonAddProducts.setOnClickListener {
            if (quantity <= productSelected?.quantity!!) {
                productSelected?.extraQ = quantity
                productSelected?.quantity =
                    productSelected?.quantity?.minus(productSelected!!.extraQ)!!
                viewModel.updateProduct(productSelected!!, this, this)
            } else {
                Toast.makeText(this, "cantidad no disponible", Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonCancelProducts.setOnClickListener {
            initCancelAdd()
        }
    }

    private fun initCancelAdd() {
        binding.apply {
            cardViewAddProducts.visibility = View.GONE
            buttonCancelProducts.visibility = View.GONE
            textViewProductName.text = ""
            textViewProductPrice.text = ""
            productSelected = null
        }
    }

    private fun initAddProduct() {
        binding.apply {
            cardViewAddProducts.visibility = View.VISIBLE
            buttonCancelProducts.visibility = View.VISIBLE
            textViewProductName.text =
                getString(R.string.quantity_value, productSelected?.quantity.toString())
            textViewProductPrice.text = "${productSelected?.price?.times(1.4f)}"
        }
    }

    private fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    private fun emptyProducts() {
        binding.autoCompleteTextSearch.visibility = View.GONE
        binding.textViewNoProducts.visibility = View.VISIBLE
    }

    private fun setEditText(editText: EditText) {
        editText.text = Editable.Factory().newEditable(quantity.toString())
    }

}