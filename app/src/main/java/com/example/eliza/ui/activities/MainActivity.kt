package com.example.eliza.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eliza.R
import com.example.eliza.databinding.ActivityMainBinding
import com.example.eliza.models.Product
import com.example.eliza.ui.views.ProductItem
import com.example.eliza.viewmodels.InventoryViewModel
import com.google.firebase.FirebaseApp
import com.xwray.groupie.GroupieAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val recyclerAdapter by lazy { GroupieAdapter() }

    private val viewModel: InventoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        subscribeToObservers()
        setupRecycler()
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.icons_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_suppliers -> {
                val intent = Intent(this, SuppliersActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_money -> {
                val intent = Intent(this, MoneyActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getProducts(this)
    }

    private fun initViews() {
        binding.buttonMakeSell.setOnClickListener {
            val intent = Intent(this, SellActivity::class.java)
            startActivity(intent)
        }
    }

    private fun subscribeToObservers() {
        viewModel.products.observe(this) {
            updateRecycler(it)
        }
    }

    private fun setupRecycler() {
        val manager = LinearLayoutManager(this)
        binding.recyclerViewInventory.apply {
            layoutManager = manager
            adapter = recyclerAdapter
        }
        viewModel.getProducts(this)
    }

    private fun updateRecycler(products: List<Product?>) {
        recyclerAdapter.clear()
        products.map {
            if (it?.quantity!! > 0)
                recyclerAdapter.add(ProductItem(it))
        }
    }
}