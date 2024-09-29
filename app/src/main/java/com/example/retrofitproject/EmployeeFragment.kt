package com.example.retrofitproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.retrofitproject.databinding.FragmentEmployeeListBinding
import com.example.retrofitproject.viewModel.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EmployeeFragment : Fragment(), EmployeeAdapter.ItemClickListener {
    private lateinit var binding: FragmentEmployeeListBinding
    private val viewModel: EmployeeViewModel by viewModels()

    @Inject
    lateinit var employeeAdapter: EmployeeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmployeeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.employeeRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        employeeAdapter = EmployeeAdapter()
        EmployeeAdapter.listener = this
        viewModel.getEmployee()
        viewModel.items.observe(viewLifecycleOwner) {
            it?.let {
                employeeAdapter.setEmployeeList(it)
                employeeAdapter.notifyDataSetChanged()
                recyclerView.adapter = employeeAdapter
            }
        }
    }

    override fun onItemClick(id: Int) {
        val action = EmployeeFragmentDirections.actionEmployeeFragmentToDetailsFragment(id)
        findNavController().navigate(action)
    }

}