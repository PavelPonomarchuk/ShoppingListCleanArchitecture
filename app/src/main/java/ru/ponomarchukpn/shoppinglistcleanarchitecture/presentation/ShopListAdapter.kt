package ru.ponomarchukpn.shoppinglistcleanarchitecture.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.ponomarchukpn.shoppinglistcleanarchitecture.R
import ru.ponomarchukpn.shoppinglistcleanarchitecture.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder> (
    ShopItemDiffCallback()
) {
    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            TYPE_ENABLED -> R.layout.item_shop_enabled
            TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        viewHolder.tvName.text = shopItem.name
        viewHolder.tvCount.text = shopItem.count.toString()

        viewHolder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        viewHolder.view.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = getItem(position)
        return if (shopItem.enabled) TYPE_ENABLED else TYPE_DISABLED
    }

    companion object {
        const val TYPE_ENABLED = 0
        const val TYPE_DISABLED = 1
        const val MAX_POOL_SIZE = 15
    }
}