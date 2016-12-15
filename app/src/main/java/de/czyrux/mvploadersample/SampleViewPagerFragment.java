package de.czyrux.mvploadersample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SampleViewPagerFragment extends Fragment {
    private static final String ARG_PAGES = "pages";
    private static final int MIN_OFF_SCREEN_LIMIT = 1;

    private int pages;
    private ViewPager viewPager;

    public static SampleViewPagerFragment newInstance(int pages) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGES, pages);

        SampleViewPagerFragment fragment = new SampleViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pages = getArguments().getInt(ARG_PAGES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager.setAdapter(new PagerAdapter(getChildFragmentManager(), pages));
        viewPager.setOffscreenPageLimit(Math.max(MIN_OFF_SCREEN_LIMIT, pages / 2));
    }


    private static class PagerAdapter extends FragmentStatePagerAdapter {

        private static int[] COLOR_ARRAY = new int[]{R.color.blue, R.color.yellow, R.color.brown, R.color.pink,
                R.color.blue_gray, R.color.deep_purple, R.color.green};

        private final int numberOfPages;

        public PagerAdapter(FragmentManager fm, int numberOfPages) {
            super(fm);
            this.numberOfPages = numberOfPages;
        }

        @Override
        public Fragment getItem(int position) {
            return SampleFragment.newInstance("fragment-#" + position, COLOR_ARRAY[position % COLOR_ARRAY.length]);
        }

        @Override
        public int getCount() {
            return numberOfPages;
        }
    }
}
