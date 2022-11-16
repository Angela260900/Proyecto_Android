package com.example.eliza.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eliza.databinding.ActivitySuppliersBinding
import com.example.eliza.interfaces.OnItemClick
import com.example.eliza.models.Supplier
import com.example.eliza.ui.views.SupplierItem
import com.example.eliza.viewmodels.SuppliersViewModel
import com.xwray.groupie.GroupieAdapter

class SuppliersActivity : AppCompatActivity(), OnItemClick {
    private lateinit var binding: ActivitySuppliersBinding

    private val viewModel: SuppliersViewModel by viewModels()

    private val recyclerAdapter by lazy { GroupieAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuppliersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupRecycler()
        subscribeToObservers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(item: Any?, position: Int) {
        item as Supplier
        goToSupplierDetails(item)
    }

    private fun goToSupplierDetails(item: Supplier) {
        val intent = Intent(this, SupplierDetailsActivity::class.java)
        intent.putExtra("products", item.products.toTypedArray())
        startActivity(intent)
    }

    private fun subscribeToObservers() {
        viewModel.suppliers.observe(this) {
            updateRecycler(it)
        }

        viewModel.getSuppliers(this)
    }

    private fun setupRecycler() {
        val manager = LinearLayoutManager(this)
        binding.recyclerViewSuppliers.apply {
            layoutManager = manager
            adapter = recyclerAdapter
        }
    }

    private fun updateRecycler(suppliers: List<Supplier?>) {
        recyclerAdapter.clear()
        suppliers.map {
            recyclerAdapter.add(SupplierItem(it, this))
        }
    }

}