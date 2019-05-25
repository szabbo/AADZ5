package hr.ferit.brunozoric.taskie.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.ferit.brunozoric.taskie.ui.fragments.AboutAppFragment
import hr.ferit.brunozoric.taskie.ui.fragments.AboutAuthorFragment

class FragmentAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    val fragments = arrayOf(
        AboutAppFragment.newAboutAppFragmentInstance(),
        AboutAuthorFragment.newAboutAuthorFragmentInstance()
    )
    private val titles = arrayOf("About App", "About Author")

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getPageTitle(position: Int): CharSequence? = titles[position]

    override fun getCount(): Int = fragments.size
}