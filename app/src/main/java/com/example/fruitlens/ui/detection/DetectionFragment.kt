package com.example.fruitlens.ui.detection

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fruitlens.databinding.FragmentDetectionBinding
import com.yalantis.ucrop.UCrop
import java.io.File

class DetectionFragment : Fragment() {

    private var _binding: FragmentDetectionBinding? = null
    private lateinit var cropImageLauncher: ActivityResultLauncher<Intent>
    private var currentImageUri: Uri? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cropImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val resultUri = data?.let { UCrop.getOutput(it) }
                resultUri?.let { showImage(it) }
                currentImageUri = resultUri
            } else if (result.resultCode == UCrop.RESULT_ERROR) {
                val cropError = result.data?.let { UCrop.getError(it) }
                Log.e("UCrop", "Error: $cropError")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DetectionViewModel::class.java)

        _binding = FragmentDetectionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.apply {
            btnGallery.setOnClickListener {
                startGallery()
            }
        }

        return root
    }

    private fun showImage(uri: Uri) {
        binding.previewImageView.setImageURI(uri)
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        uri?.let {
            startUCrop(it)
        }
    }

    private fun startUCrop(sourceUri: Uri) {
        val destinationUri = getOutputUri()
        UCrop.of(sourceUri, destinationUri)
            .withAspectRatio(16F, 9F)
            .withMaxResultSize(800, 800)
            .getIntent(requireContext()).also { intent ->
                cropImageLauncher.launch(intent)
            }
    }

    private fun getOutputUri(): Uri {
        val context = requireContext()
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "${System.currentTimeMillis()}_cropped.jpg")
        return FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}