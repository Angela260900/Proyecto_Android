package com.example.eliza.ui.views

import android.view.View
import com.example.eliza.R
import com.example.eliza.databinding.ProductItemBinding
import com.example.eliza.models.Product
import com.xwray.groupie.viewbinding.BindableItem

class ProductItem(private val product: Product?) : BindableItem<ProductItemBinding>() {
    override fun bind(viewBinding: ProductItemBinding, position: Int) {
        viewBinding.textViewProductName.text = product?.name
        viewBinding.textViewProductQuantity.text = product?.quantity.toString()
    }

    override fun getLayout(): Int = R.layout.product_item

    override fun initializeViewBinding(view: View): ProductItemBinding =
        ProductItemBinding.bind(view)
}