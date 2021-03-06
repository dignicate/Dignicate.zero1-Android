package com.dignicate.zero1.ui.manualdi

object MenuDefinition {

    sealed class RowState {
        class SectionRow(val data: Section): RowState()
        class ItemRow(val data: Item, val indexInSection: Int): RowState()

        val section: Section?
            get() {
                return when (this) {
                    is SectionRow -> data
                    is ItemRow -> null
                }
            }

        val indexedItem: Pair<Item, Int>?
            get() {
                return when (this) {
                    is SectionRow -> null
                    is ItemRow -> Pair(data, indexInSection)
                }
            }
    }

    object ContentStructure {
        val rowStates: List<RowState>
            get() {
                val mutable = ArrayList<RowState>()
                Section.values().forEach { section ->
                    mutable.add(RowState.SectionRow(section))
                    section.items.forEachIndexed { index, item ->
                        mutable.add(RowState.ItemRow(item, index))
                    }
                }
                return mutable
            }

        fun rowOf(sequentialIndex: Int): RowState {
            return rowStates[sequentialIndex]
        }
    }

    enum class Section(val index: Int,
                       val title: String,
                       val items: List<Item>) {
        BASIC(0, "Basic Data Interaction", listOf(
            Item.BASIC_FETCH,
            Item.FETCH_WITH_DATA_STATE,
            Item.FETCH_AND_SAVE_DATA,
            Item.POST_AND_REFRESH
        )),
        RECYCLER_VIEW(1, "Recycler View", listOf(
            Item.LIST_AND_DETAIL,
            Item.PAGINATION
        )),
        USER_INPUT(2, "User Input", listOf(
            Item.SIMPLE_VALIDATION,
            Item.VALIDATE_AND_AUTO_CORRECT,
            Item.STORE_INPUTS_OVER_SCREEN
        ))
    }

    enum class Item(val title: String, val isManualAvailable: Boolean, val isHiltAvailable: Boolean = false) {
        BASIC_FETCH("Basic fetch over HTTP", true, true),
        FETCH_WITH_DATA_STATE("Fetch with data state", true, true),
        FETCH_AND_SAVE_DATA("Save fetched data into local device", true),
        POST_AND_REFRESH("Post data and refresh view", false),
        LIST_AND_DETAIL("List and detail", false),
        PAGINATION("Pagination", false),
        SIMPLE_VALIDATION("Simple validation", false),
        VALIDATE_AND_AUTO_CORRECT("Validate and auto-correct", false),
        STORE_INPUTS_OVER_SCREEN("Store inputs over screens", false)
    }
}

