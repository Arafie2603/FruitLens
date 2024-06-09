package com.example.fruitlens.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fruitlens.ViewModelFactory
import com.example.fruitlens.databinding.FragmentHomeBinding
import com.example.fruitlens.ui.login.LoginActivity
import com.example.fruitlens.ui.quiz.QuizActivity
import com.example.fruitlens.utils.Injection

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>() {
        ViewModelFactory { Injection.provideUserRepository(requireContext()) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        binding.apply {
            btnPlay.setOnClickListener {
                val inten = Intent(requireContext(), QuizActivity::class.java)
                startActivity(inten)
            }
        }
        // Ambil argumen dari bundle
        val name = arguments?.getString("name") ?: "Arafie"
        Log.d("Tester", "isi name = $name")

        viewModel.getSession()
        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            user?.let {
                val currentText = binding.titleGreetings.text.toString()
                val newText = currentText.replace("Arafie", user.email)
                binding.titleGreetings.text = newText
            } ?: run {
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                Log.e("HomeFragment", "User session is null")
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}