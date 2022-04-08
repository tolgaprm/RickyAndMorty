package comprmto.rickyandmorty.presentation.character.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import comprmto.rickyandmorty.databinding.FragmentCharacterListBinding
import comprmto.rickyandmorty.presentation.adapter.CharacterAdapter
import comprmto.rickyandmorty.presentation.character.viewmodel.CharacterViewModel
import comprmto.rickyandmorty.util.ItemClickListener
import comprmto.rickyandmorty.util.NavigateState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: CharacterViewModel
    private lateinit var characterAdapter: CharacterAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterListBinding.inflate(layoutInflater, container, false)
        Timber.d("asas")
        viewModel = ViewModelProvider(requireActivity())[CharacterViewModel::class.java]

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        prepareCharacterAdapter()

       viewModel.checkIfTheFilterHasBeenApplied()


        lifecycleScope.launch {
            viewModel.getListData().collectLatest {
                characterAdapter.submitData(it)
            }
        }
        return binding.root
    }


    private fun prepareCharacterAdapter() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.characterList.layoutManager = layoutManager


        characterAdapter = CharacterAdapter(
            ItemClickListener {
                val action =
                    CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                        it,
                        NavigateState.CHARACTERLIST
                    )
                findNavController().navigate(action)
            }
        )

        characterAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.characterList.adapter = characterAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.imageButton.setOnClickListener {
            val action = CharacterListFragmentDirections.actionCharacterListFragmentToFilterDialog()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}