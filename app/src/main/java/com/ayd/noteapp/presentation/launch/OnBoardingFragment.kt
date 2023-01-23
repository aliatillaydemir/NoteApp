package com.ayd.noteapp.presentation.launch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ayd.noteapp.R
import com.ayd.noteapp.databinding.FragmentOnBoardingBinding
import com.ayd.noteapp.framework.utils.Constants
import com.ayd.noteapp.presentation.MainActivity
import com.google.android.material.button.MaterialButton


class OnBoardingFragment : Fragment() {

    private lateinit var onBoardingItemsAdapter: OnBoardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnBoardingItems()
        setupIndicator()
        setCurrentIndicator(0)
    }


    private fun setOnBoardingItems(){
        onBoardingItemsAdapter = OnBoardingItemsAdapter(
            listOf(
                OnBoardingItem(
                    onboardingImage = R.drawable.note1,
                    title = "ziziziizi",
                    description = "dsafıkpodashfoısd"
                ),
                OnBoardingItem(
                    onboardingImage = R.drawable.note2,
                    title = "djsoaıdasfasdasdasoıhfoısdfds",
                    description = "zazaza"
                ),
                OnBoardingItem(
                    onboardingImage = R.drawable.note3,
                    title = "zezezeze",
                    description = "dsafıkpodashfoısd"
                ),
            )
        )
        val onBoardingViewPager = binding.onBoardingViewPager
        onBoardingViewPager.adapter = onBoardingItemsAdapter
        onBoardingViewPager.registerOnPageChangeCallback(object:
            ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onBoardingViewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        binding.nextImage.setOnClickListener {
            if(onBoardingViewPager.currentItem + 1 < onBoardingItemsAdapter.itemCount){
                onBoardingViewPager.currentItem += 1
            }else{
                navigateHome()
            }
        }

        binding.textSkip.setOnClickListener { navigateHome() }
        binding.startButton.setOnClickListener{ navigateHome() }

    }

    private fun navigateHome(){
        findNavController().navigate(R.id.action_onBoardingFragment_to_mainActivity)
        onBoardFinish()
        activity?.finish()
    }

    private fun setupIndicator(){
        indicatorsContainer = binding.indicatorsContainer
        val indicators = arrayOfNulls<ImageView>(onBoardingItemsAdapter.itemCount)

        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(8,0,8,0)

        for(i in indicators.indices){
            indicators[i] = ImageView(requireContext())
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int){
        val childCount = indicatorsContainer.childCount
        for(i in 0 until childCount){
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if(i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_active_background
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }

    private fun onBoardFinish(){
        val sharedPref = requireActivity().getSharedPreferences(Constants.SHARED_PREF_BOARD, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean(Constants.SHARED_PREF_EDIT,true)
        editor.apply()
    }



}