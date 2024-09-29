package com.example.retrofitproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.retrofitproject.databinding.FragmentDetailsBinding
import com.example.retrofitproject.reposatories.EmployeeDetailsRepository
import com.example.retrofitproject.viewModel.EmployeeDetailsViewModel

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: EmployeeDetailsViewModel
    private val args: DetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.data
        Log.d("one", "onViewCreated: $id")

        viewModel = EmployeeDetailsViewModel(EmployeeDetailsRepository())
        viewModel.getEmployeeDetails(id)
        viewModel.items.observe(viewLifecycleOwner) {
            it?.let {
                binding.tvEmployeeName.text = it.name
                binding.tvEmployeeEmail.text = it.email
                binding.tvEmployeeUserName.text = it.username
                binding.tvEmployeeStreet.text = it.address.street
                binding.tvEmployeeSuite.text = it.address.suite
                binding.tvEmployeeCity.text = it.address.city
                binding.tvEmployeeZipcode.text = it.address.zipcode
                binding.tvEmployeeLat.text = it.address.geo.lat
                binding.tvEmployeeLng.text = it.address.geo.lng
                binding.tvEmployeePhone.text = it.phone
                binding.tvEmployeeWebsite.text = it.website
                binding.tvEmployeeCompanyBs.text = it.company.bs
                binding.tvEmployeeCatchPhrase.text = it.company.catchPhrase
                binding.tvEmployeeCompanyName.text = it.company.name
                binding.tvEmployeeId.text = it.id.toString()
            }
        }
    }
}