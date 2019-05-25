package hr.ferit.brunozoric.taskie.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.ferit.brunozoric.taskie.R

class AboutAppFragment : Fragment() {

    companion object {
        fun newAboutAppFragmentInstance(): Fragment = AboutAppFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
        Bundle?
    ): View? = inflater.inflate(R.layout.fragment_about_app, container, false)
}