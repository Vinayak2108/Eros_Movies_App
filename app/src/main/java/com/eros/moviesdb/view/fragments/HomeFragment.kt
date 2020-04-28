package com.eros.moviesdb.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.eros.moviesdb.databinding.HomeFragmentBinding
import com.eros.moviesdb.viewmodel.HomeFragmentViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy


class HomeFragment : BaseFragment() {

    private lateinit var viewBinding: HomeFragmentBinding
    private val fragmentList = ArrayList<FragmentData>().also{
        it.add(FragmentData("Movies", MoviesFragment.newInstance()))
        it.add(FragmentData("Favourite", FavouriteMoviesFragment.newInstance()))
    }

    //todo check its requirement
    companion object {
        fun newInstance() = HomeFragment()
    }

    //todo check its requirement
    private lateinit var fragmentViewModel: HomeFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = HomeFragmentBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        renderUI()
    }

    override fun loadData() {}

    override fun renderUI() {
        viewBinding.pager.adapter = PagerAdapter(this,fragmentList)
        TabLayoutMediator(viewBinding.tabLayout, viewBinding.pager,
            TabConfigurationStrategy { tab: TabLayout.Tab, position: Int ->
                tab.text = fragmentList[position].title
            }
        ).attach()
    }

}


class PagerAdapter(host:Fragment,private val fragmentList:ArrayList<FragmentData>) : FragmentStateAdapter(host){

    override fun getItemCount(): Int {
        return fragmentList.size
    }
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position].fragment
    }
}

data class FragmentData(val title:String,val fragment:Fragment)
