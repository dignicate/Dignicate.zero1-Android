package com.dignicate.zero1.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dignicate.zero1.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    class Adapter(private val recyclerView: RecyclerView,
                  private val data: String,
                  private val viewModel: MainViewModel
    ) : RecyclerView.Adapter<Adapter.ViewHolder>() {

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
            return ViewHolder(recyclerView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        }

        override fun getItemCount(): Int {
            return MainViewModel.ContentStructure.numberOfRows
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    }
}