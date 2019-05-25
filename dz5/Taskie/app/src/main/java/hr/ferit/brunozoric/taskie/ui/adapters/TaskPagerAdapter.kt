package hr.ferit.brunozoric.taskie.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.ferit.brunozoric.taskie.ui.fragments.AboutAppFragment
import hr.ferit.brunozoric.taskie.ui.fragments.AboutAuthorFragment
import hr.ferit.brunozoric.taskie.ui.fragments.TasksFragment

//didn't use this pager adapter because I made mine already and saw this one after, so.. Use it if you want
class TaskPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> AboutAppFragment.newAboutAppFragmentInstance()
            1 -> AboutAuthorFragment.newAboutAuthorFragmentInstance()
            else -> TasksFragment.newInstance()
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        val titles = arrayOf("test","test2")
        return titles[position]
    }
}