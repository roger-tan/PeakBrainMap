package fr.rogertan.challenge.peakbrainmap.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fr.rogertan.challenge.peakbrainmap.fragments.AgeGroupFragment;
import fr.rogertan.challenge.peakbrainmap.fragments.MainActivityFragment;
import fr.rogertan.challenge.peakbrainmap.fragments.ProfessionFragment;

/**
 * Created by rogertan on 07/10/15.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "You", "Age Group", "Profession" };
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MainActivityFragment.newInstance();
            case 1:
                return AgeGroupFragment.newInstance();
            case 2:
                return ProfessionFragment.newInstance();
            default:
                return MainActivityFragment.newInstance();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
