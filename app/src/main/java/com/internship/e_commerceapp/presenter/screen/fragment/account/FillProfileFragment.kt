package com.internship.e_commerceapp.presenter.screen.fragment.account

import android.Manifest
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.internship.e_commerceapp.R
import com.internship.e_commerceapp.databinding.FragmentFillProfileBinding
import com.internship.e_commerceapp.presenter.screen.activity.authentication.AuthenticationActivity
import com.internship.e_commerceapp.util.Constant
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FillProfileFragment : Fragment() {
    private var _binding: FragmentFillProfileBinding? = null
    private val binding: FragmentFillProfileBinding get() = _binding!!

    private val calendar: Calendar = Calendar.getInstance()
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener

    /* Gallery Permission here */
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            selectImageFromGallery()
        } else {
            showPermissionDeniedDialog()
        }
    }

    private val getImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            binding.civFillProfile.setImageURI(uri)
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFillProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            binding.etDob.setText(dateFormat.format(calendar.time).toString())
        }

        val adapter = ArrayAdapter<String>(requireContext(),
            android.R.layout.simple_spinner_dropdown_item, arrayOf("Male", "Female"))

        binding.apply {
                imgBackPressed.setOnClickListener {
                (activity as AuthenticationActivity).onBackPressed()
            }
            etDob.setOnClickListener {
                showCalendar()
            }

            actGender.setAdapter(adapter)
            ivChangeImage.setOnClickListener { checkGalleryPermission() }

            btnContinue.setOnClickListener {
                findNavController().navigate(R.id.resetPassSuccessDialogFragment, bundleOf(
                    Constant.DIALOG_FRAGMENT to Constant.FILL_PROFILE
                ))
            }
        }
    }

    private fun showCalendar() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    /* Gallery Permission */
    private fun checkGalleryPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                selectImageFromGallery()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                showPermissionRationaleDialog()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    private fun selectImageFromGallery() {
        getImageLauncher.launch("image/*")
    }

    private fun showPermissionRationaleDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Permission Needed")
            .setMessage("This app needs access to your gallery to display images. Please grant the permission.")
            .setPositiveButton("OK") { dialog, _ ->
                // Request the permission after showing rationale
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // Handle what happens when the user cancels the dialog
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Permission Denied")
            .setMessage("You have denied the permission. Please enable it from settings to use this feature.")
            .setPositiveButton("Settings") { dialog, _ ->
                // Send the user to the app's settings
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", requireContext().packageName, null)
                )
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // Handle what happens when the user cancels the dialog
                dialog.dismiss()
            }
            .create()
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}