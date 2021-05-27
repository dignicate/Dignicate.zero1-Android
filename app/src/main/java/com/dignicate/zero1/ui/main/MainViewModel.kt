package com.dignicate.zero1.ui.main

import androidx.lifecycle.ViewModel
import com.dignicate.zero1.rx.DisposeBag
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class MainViewModel : ViewModel() {

    private val disposeBag = DisposeBag()

    private val rowStatesSubject = PublishSubject.create<List<RowState>>()

    val rowStates: Observable<List<RowState>>
        get() = rowStatesSubject

    fun onActivityCreated() {
        rowStatesSubject.onNext(ContentStructure.rowStates)
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }

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
            Item.FETCH_DATA_AND_SAVE_CACHE
        )),
        RECYCLER_VIEW(1, "Recycler View", emptyList()),
        USER_INPUT(2, "User Input", emptyList());
    }

    enum class Item(val title: String, val isAvailable: Boolean) {
        BASIC_FETCH("Basic fetch over HTTP", true),
        FETCH_WITH_DATA_STATE("Fetch with data state", false),
        FETCH_DATA_AND_SAVE_CACHE("Save fetched data into local device", false);
    }
}