package com.dignicate.zero1.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dignicate.zero1.R
import com.dignicate.zero1.databinding.MainFragmentBinding
import com.dignicate.zero1.rx.DisposeBag
import com.dignicate.zero1.rx.disposedBy
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
        // TODO: Use the ViewModel

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
            adapter = Adapter()
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        }
    }

    class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

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
                ViewType.SECTION.value -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_row_section, parent, false))
                ViewType.ITEM.value -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_row_item, parent, false))
                else -> throw IllegalStateException("Unexpected viewType: $viewType")
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            Timber.d("position: $position")
        }

        override fun getItemCount(): Int {
            return MainViewModel.ContentStructure.numberOfRows
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    }
}