package com.dignicate.zero1.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    data class IndexPath(
        val section: Int,
        val row: Int
    )

    object ContentStructure {

        fun toIndexPath(sequencialIndex: Int): IndexPath {
            return IndexPath(0, 0)
        }
    }

    enum class Section(private val index: Int,
                       private val title: String,
                       private val items: List<Item>) {
        BASIC(0, "Basic Data Interaction", listOf(Item.BASIC_FETCH)),
        RECYCLER_VIEW(1, "Recycler View", emptyList()),
        USER_INPUT(2, "User Input", emptyList());
    }

    enum class Item(private val title: String, private val isAvailable: Boolean) {
        BASIC_FETCH("Basic fetch over HTTP", true);
    }
}