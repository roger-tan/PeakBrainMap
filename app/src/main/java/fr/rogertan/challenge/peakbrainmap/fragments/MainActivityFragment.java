package fr.rogertan.challenge.peakbrainmap.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import fr.rogertan.challenge.peakbrainmap.R;
import fr.rogertan.challenge.peakbrainmap.charts.SpiderChartView;
import fr.rogertan.challenge.peakbrainmap.models.Stat;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private List<Stat> mStats = new ArrayList<Stat>();
    private List<Stat> mCompetitorStats = new ArrayList<Stat>();

    public static MainActivityFragment newInstance() {
        Bundle args = new Bundle();
        MainActivityFragment fragment = new MainActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int[] userValues =  {240, 600, 290, 240, 700, 924};
        int[] competitorsValues =  {121, 242, 424, 42, 100, 121};

        mStats = statsWithValues(userValues);
        mCompetitorStats = statsWithValues(competitorsValues);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        RelativeLayout chartLayout = (RelativeLayout)view.findViewById(R.id.chart_view);
        chartLayout.addView(new SpiderChartView(getContext(), mStats, mCompetitorStats));
        return view;
    }

    private List<Stat> statsWithValues(int[] values) {
        List<Stat> stats = new ArrayList<Stat>();

        Stat stat1 = new Stat();
        stat1.title = "Language";
        stat1.score = values[0];
        stat1.color = ContextCompat.getColor(getContext(), R.color.purple);
        stats.add(stat1);

        Stat stat2 = new Stat();
        stat2.title = "Problem Solving";
        stat2.score = values[1];
        stat2.color = ContextCompat.getColor(getContext(), R.color.green);
        stats.add(stat2);

        Stat stat3 = new Stat();
        stat3.title = "Memory";
        stat3.score = values[2];
        stat3.color = ContextCompat.getColor(getContext(), R.color.orange);

        stats.add(stat3);

        Stat stat4 = new Stat();
        stat4.title = "Peak Brain Score";
        stat4.score = values[3];
        stat4.color = ContextCompat.getColor(getContext(), R.color.peak_blue);
        stats.add(stat4);

        Stat stat5 = new Stat();

        stat5.title = "Focus";
        stat5.score = values[4];
        stat5.color = ContextCompat.getColor(getContext(), R.color.red);
        stats.add(stat5);

        Stat stat6 = new Stat();
        stat6.title = "Mental Agility";
        stat6.score = values[5];
        stat6.color = ContextCompat.getColor(getContext(), R.color.blue);
        stats.add(stat6);

        return stats;
    }

}
