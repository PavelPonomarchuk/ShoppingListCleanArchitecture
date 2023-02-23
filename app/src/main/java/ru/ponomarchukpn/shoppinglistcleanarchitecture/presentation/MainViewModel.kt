package ru.ponomarchukpn.shoppinglistcleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import ru.ponomarchukpn.shoppinglistcleanarchitecture.data.ShopListRepositoryImpl
import ru.ponomarchukpn.shoppinglistcleanarchitecture.domain.DeleteShopItemUseCase
import ru.ponomarchukpn.shoppinglistcleanarchitecture.domain.EditShopItemUseCase
import ru.ponomarchukpn.shoppinglistcleanarchitecture.domain.GetShopListUseCase
import ru.ponomarchukpn.shoppinglistcleanarchitecture.domain.ShopItem

class MainViewModel: ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}