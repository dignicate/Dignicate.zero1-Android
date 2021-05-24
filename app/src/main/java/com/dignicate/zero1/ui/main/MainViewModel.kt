package com.dignicate.zero1.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    sealed class RowState {
        class SectionRow(section: Section): RowState()
        class ItemRow(item: Item): RowState()
    }

    object ContentStructure {

        private val listOfRowState: List<RowState>
            get() {
                val mutable = ArrayList<RowState>()
                Section.values().forEach { section ->
                    mutable.add(RowState.SectionRow(section))
                    section.items.forEach { item ->
                        mutable.add(RowState.ItemRow(item))
                    }
                }
                return mutable
            }

        val numberOfSections: Int
            get() = Section.values().size
    }

    enum class Section(val index: Int,
                       val title: String,
                       val items: List<Item>) {
        BASIC(0, "Basic Data Interaction", listOf(Item.BASIC_FETCH)),
        RECYCLER_VIEW(1, "Recycler View", emptyList()),
        USER_INPUT(2, "User Input", emptyList());

        val numberOfItems: Int
            get() = items.size
    }

    enum class Item(private val title: String, private val isAvailable: Boolean) {
        BASIC_FETCH("Basic fetch over HTTP", true);
    }
}