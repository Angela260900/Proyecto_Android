package com.example.eliza.ui.views

import android.text.Editable
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.eliza.R
import com.example.eliza.databinding.ProductDetailItemBinding
import com.example.eliza.interfaces.OnItemClick
import com.example.eliza.models.Product
import com.xwray.groupie.viewbinding.BindableItem

class ProductDetailItem(
    private val product: Product?,
    private val onItemClick: OnItemClick
) : BindableItem<ProductDetailItemBinding>() {

    var quantity = 0

    override fun bind(viewBinding: ProductDetailItemBinding, position: Int) {
        viewBinding.textViewProductName.text = product?.name
        viewBinding.textViewProductPrice.text = product?.price.toString()

        viewBinding.buttonPlus.setOnClickListener {
            quantity++
            setEditText(viewBinding.editTextNumber)
        }

        viewBinding.buttonMinus.setOnClickListener {
            if (quantity > 0) {
                quantity--
                setEditText(viewBinding.editTextNumber)
            }
        }

        viewBinding.editTextNumber.addTextChangedListener {
            quantity = it.toString().toInt()
        }

        viewBinding.buttonAddProducts.setOnClickListener {
            if (quantity > 0) {
                product?.extraQ = quantity
                product?.quantity = product?.quantity?.plus(product.extraQ)!!
                onItemClick.onItemClick(product)
            }
        }
    }

    override fun getLayout(): Int = R.layout.product_detail_item

    override fun initializeViewBinding(view: View): ProductDetailItemBinding =
        ProductDetailItemBinding.bind(view)

    private fun setEditText(editText: EditText) {
        editText.text = Editable.Factory().newEditable(quantity.toString())
    }
}