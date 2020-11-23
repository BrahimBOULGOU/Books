package com.publicissapient.publicissapienttest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.publicissapient.publicissapienttest.R
import com.publicissapient.publicissapienttest.ui.DetailsFragmentArgs.Companion.fromBundle
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {
   // private val args: DetailsFragmentArgs by navArgs()
    val variableName by lazy {
        fromBundle(requireArguments()).book
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(view)
            .load(variableName)
            .into(imageView2)
    }
}