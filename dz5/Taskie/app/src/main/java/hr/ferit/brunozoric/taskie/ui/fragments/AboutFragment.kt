package hr.ferit.brunozoric.taskie.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.ui.adapters.FragmentAdapter
import hr.ferit.brunozoric.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : BaseFragment() {

    companion object {
        fun newInstance(): Fragment = AboutFragment()
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_about

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.adapter = FragmentAdapter(childFragmentManager)
        tab_layout.setupWithViewPager(view_pager)
    }
}