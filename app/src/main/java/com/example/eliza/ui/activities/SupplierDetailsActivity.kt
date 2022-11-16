package com.example.eliza.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eliza.databinding.ActivitySupplierDetailsBinding
import com.example.eliza.interfaces.OnItemClick
import com.example.eliza.interfaces.ReadyToFinishActivity
import com.example.eliza.models.Product
import com.example.eliza.ui.views.ProductDetailItem
import com.example.eliza.viewmodels.SuppliersDetailViewModel
import com.xwray.groupie.GroupieAdapter

class SupplierDetailsActivity : AppCompatActivity(), OnItemClick, ReadyToFinishActivity {
    private lateinit var binding: ActivitySupplierDetailsBinding

    private val viewModel: SuppliersDetailViewModel by viewModels()

    private lateinit var products: List<String>

    private val recyclerAdapter by lazy { GroupieAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupplierDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        products = intent.extras?.getStringArray("products")?.toList().orEmpty()
        subscribeToObservers()
        setupRecycler()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(item: Any?, position: Int) {
        item as Product
        viewModel.updateProduct(item, products[position], this, this)
        binding.root.clearFocus()
    }

    override fun readyToFinishActivity() {
        finish()
    }

    private fun subscribeToObservers() {
        viewModel.setProductsList(products)

        viewModel.products.observe(this) {
            updateRecycler(it)
        }

        viewModel.getProducts(this)
    }

    private fun setupRecycler() {
        val manager = LinearLayoutManager(this)
        binding.recyclerViewAddProducts.apply {
            layoutManager = manager
            adapter = recyclerAdapter
        }
    }

    private fun updateRecycler(productsToAdd: List<Product?>) {
        recyclerAdapter.clear()
        productsToAdd.map {
            recyclerAdapter.add(ProductDetailItem(it, this))
        }
    }
}