package com.example.eliza.ui.views

import android.view.View
import com.example.eliza.R
import com.example.eliza.databinding.SupplierItemBinding
import com.example.eliza.interfaces.OnItemClick
import com.example.eliza.models.Supplier
import com.xwray.groupie.viewbinding.BindableItem

class SupplierItem(private val supplier: Supplier?, private val onItemClick: OnItemClick) :
    BindableItem<SupplierItemBinding>() {
    override fun bind(viewBinding: SupplierItemBinding, position: Int) {
        viewBinding.textViewSupplierName.text = supplier?.name

        viewBinding.buttonArrow.setOnClickListener {
            onItemClick.onItemClick(supplier)
        }
    }

    override fun getLayout(): Int = R.layout.supplier_item

    override fun initializeViewBinding(view: View): SupplierItemBinding =
        SupplierItemBinding.bind(view)
}