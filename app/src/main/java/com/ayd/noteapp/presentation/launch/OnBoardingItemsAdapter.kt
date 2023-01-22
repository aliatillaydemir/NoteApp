package com.ayd.noteapp.presentation.launch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ayd.noteapp.R

class OnBoardingItemsAdapter(private val onboardingItems: List<OnBoardingItem>): RecyclerView.Adapter<OnBoardingItemsAdapter.OnBoardingViewHolder>() {

    inner class OnBoardingViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val imageOnBoarding = view.findViewById<ImageView>(R.id.imageOnboarding)
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDesc)

        fun bind(onboardingItem: OnBoardingItem){
            imageOnBoarding.setImageResource(onboardingItem.onboardingImage)
            textTitle.text = onboardingItem.title
            textDescription.text = onboardingItem.description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.onboarding_item_container,parent,false))
    }

    override fun getItemCount(): Int {
        return onboardingItems.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(onboardingItems[position])
    }

}