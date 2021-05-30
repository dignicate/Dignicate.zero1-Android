package com.dignicate.zero1.ui.main

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dignicate.zero1.R
import com.dignicate.zero1.databinding.MainFragmentBinding
import com.dignicate.zero1.rx.DisposeBag
import com.dignicate.zero1.rx.disposedBy
import com.dignicate.zero1.ui.subject01.case101.BasicFetchApiActivity
import com.dignicate.zero1.ui.subject01.case102.FetchWithDataStateActivity
import timber.log.Timber

class MainFragment : Fragment() {

    private val disposeBag = DisposeBag()

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding

    private lateinit var viewModel: MainViewModel

    private val adapter: Adapter?
        get() = binding.mainFragmentRecyclerView.adapter as? Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setupRecycleView()
        setupBinding()
        viewModel.onActivityCreated()
    }

    private fun setupBinding() {
        viewModel.rowStates
            .onErrorReturn { emptyList() }
            .subscribe {
                adapter?.setData(data = it)
            }
            .disposedBy(disposeBag)
    }

    private fun setupRecycleView() {
        binding.mainFragmentRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = Adapter {
                when (it) {
                    MainViewModel.Item.BASIC_FETCH -> {
                        startActivity(Intent(requireContext(), BasicFetchApiActivity::class.java))
                    }
                    MainViewModel.Item.FETCH_WITH_DATA_STATE -> {
                        startActivity(Intent(requireContext(), FetchWithDataStateActivity::class.java))
                    }
                    // TODO: Must be exhaustive.
                    else -> {

                    }
                }
            }
        }
    }

    class Adapter(val onClick: (MainViewModel.Item) -> Unit) : RecyclerView.Adapter<Adapter.ViewHolder>() {

        private var data: List<MainViewModel.RowState> = emptyList()

        fun setData(data: List<MainViewModel.RowState>) {
            this.data = data
            notifyDataSetChanged()
        }

        enum class ViewType(val value: Int) {
            SECTION(0),
            ITEM(1);
        }

        override fun getItemViewType(position: Int): Int {
            return when (MainViewModel.ContentStructure.rowOf(position)) {
                is MainViewModel.RowState.SectionRow -> ViewType.SECTION.value
                is MainViewModel.RowState.ItemRow -> ViewType.ITEM.value
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return when (viewType) {
                ViewType.SECTION.value -> SectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_row_section, parent, false))
                ViewType.ITEM.value -> ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_row_item, parent, false), parent.context)
                else -> throw IllegalStateException("Unexpected viewType: $viewType")
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            Timber.v("position: $position")
            when (holder) {
                is SectionViewHolder -> {
                    val section = data[position].section ?: return
                    holder.configure(section.title)
                }
                is ItemViewHolder -> {
                    val indexedItem = data[position].indexedItem ?: return
                    holder.configure(
                        number = indexedItem.second + 1,
                        item = indexedItem.first,
                        onClick = { item ->
                            onClick.invoke(item)
                        }
                    )
                }
            }
        }

        override fun getItemCount(): Int {
            return data.size
        }

        abstract class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

        class SectionViewHolder(view: View) : ViewHolder(view) {
            private val titleLabel: TextView = view.findViewById(R.id.mainRowSectionTitle)

            fun configure(title: String) {
                titleLabel.text = title
            }
        }

        class ItemViewHolder(view: View, private val context: Context) : ViewHolder(view) {
            private val numberLabel: TextView = view.findViewById(R.id.mainRowItemNumber)
            private val titleLabel: TextView = view.findViewById(R.id.mainRowItemTitle)
            private val background: View = view.findViewById(R.id.mainRowItemRoot)

            fun configure(number: Int, item: MainViewModel.Item, onClick: (MainViewModel.Item) -> Unit) {
                numberLabel.text = "$number"
                titleLabel.text = item.title
                background.setOnClickListener { onClick(item) }
                if (item.isAvailable) {
                    titleLabel.setTextColor(ContextCompat.getColor(context, R.color.top_view_cell_text_enabled))
                    background.setBackgroundResource(R.color.top_view_cell_background_enabled)
                } else {
                    titleLabel.setTextColor(ContextCompat.getColor(context, R.color.top_view_cell_text_disabled))
                    background.setBackgroundResource(R.color.top_view_cell_background_disabled)
                }
            }
        }
    }
}