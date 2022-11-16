package com.example.eliza.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.eliza.R
import com.example.eliza.databinding.ActivityMoneyBinding
import com.example.eliza.models.Money
import com.example.eliza.viewmodels.MoneyViewModel

class MoneyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoneyBinding

    private val viewModel: MoneyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        subscribeToObserver()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun subscribeToObserver() {
        viewModel.money.observe(this) {
            updateViews(it)
        }

        viewModel.getMoney(this)
    }

    private fun updateViews(money: Money) {
        binding.textViewAssetsValue.text = money.assets.toString()

        when {
            money.patrimony > 0 -> {
                binding.textViewPatrimonyValue.setTextColor(getColor(R.color.green))
                binding.textViewPatrimony.setTextColor(getColor(R.color.green))
            }
            money.patrimony < 0 -> {
                binding.textViewPatrimonyValue.setTextColor(getColor(R.color.red))
                binding.textViewPatrimony.setTextColor(getColor(R.color.red))
            }
            else -> {
                binding.textViewPatrimonyValue.setTextColor(getColor(R.color.black))
                binding.textViewPatrimony.setTextColor(getColor(R.color.black))
            }
        }

        binding.textViewPatrimonyValue.text = money.patrimony.toString()

        binding.textViewPassivesValue.text = money.passives.toString()
    }
}