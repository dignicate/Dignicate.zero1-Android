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

    fun rowStateOf(position: Int): RowState {
        return ContentStructure.rowOf(position)
    }

    sealed class RowState {
        class SectionRow(section: Section): RowState()
        class ItemRow(item: Item): RowState()
    }

    object ContentStructure {
        val rowStates: List<RowState>
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

        val numberOfRows: Int
            get() = rowStates.size

        fun rowOf(sequentialIndex: Int): RowState {
            return rowStates[sequentialIndex]
        }
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