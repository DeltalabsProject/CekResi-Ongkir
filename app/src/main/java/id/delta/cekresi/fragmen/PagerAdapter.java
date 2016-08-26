package id.delta.cekresi.fragmen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Administrator on 8/19/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment=new JneFragment();
                break;
            case 1:
                fragment=new TikiFragment();
                break;
            case 2:
                fragment=new PosFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=" ";
        switch (position){
            case 0:
                title="JNE";
                break;
            case 1:
                title="TIKI";
                break;
            case 2:
                title="POS";
                break;
        }

        return title;
    }
}
