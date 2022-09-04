package com.example.myxherocard.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myxherocard.R
import com.example.myxherocard.databinding.FragmentHomeBinding
import com.example.myxherocard.utils.sendNotification
import com.example.myxherocard.viewmodel.AuthenticationState
import com.example.myxherocard.viewmodel.CardsViewModel
import com.example.myxherocard.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val cardsViewModel : CardsViewModel by viewModel()

    private lateinit var adapter: CardListAdapter

    private companion object {
        private val TAG = HomeFragment::class.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        adapter = CardListAdapter(
            CardListAdapter.AdapterClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it))
            },
            CardListAdapter.AdapterClickListener {
                val isFavoriteCard = if(it.isFavorite == 0) 1 else 0
                it.isFavorite = isFavoriteCard
                cardsViewModel.updateFavorite(it)

                if(it.isFavorite == 1) {
                    sendNotification(requireContext(), it)
                }
            }
        )
        binding.recycler.layoutManager = LinearLayoutManager(context)
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT
        binding.recycler.adapter = adapter

        cardsViewModel.cards.observe(viewLifecycleOwner) {
            updateUIState(!it.isEmpty())

            adapter.setData(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun updateUIState(isDataLoaded: Boolean) {
        if(isDataLoaded) {
             Log.d(TAG, "Data was successfully loaded")
             binding.recycler.visibility = View.VISIBLE
             binding.errorLabel.visibility = View.INVISIBLE
        } else {
            Log.d(TAG, "Data was not successfully loaded")
            binding.recycler.visibility = View.INVISIBLE
            binding.errorLabel.visibility = View.VISIBLE
        }
    }

}