package de.czyrux.mvploadersample;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.czyrux.mvploadersample.base.BasePresenterFragment;
import de.czyrux.mvploadersample.base.PresenterFactory;
import de.czyrux.mvploadersample.presenter.SamplePresenter;
import de.czyrux.mvploadersample.presenter.SamplePresenterFactory;
import de.czyrux.mvploadersample.presenter.SampleView;

public class SampleFragment extends BasePresenterFragment<SamplePresenter, SampleView> implements SampleView {
    private static final String TAG = "SampleFragment";
    private static final String ARG_NAME = "name";
    private static final String ARG_COLOR = "color";

    @ColorRes
    private int color;
    private String name;

    private TextView titleTextView;
    private SamplePresenter presenter;

    public static SampleFragment newInstance(String name, @ColorRes int color) {
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putInt(ARG_COLOR, color);

        SampleFragment fragment = new SampleFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
            color = getArguments().getInt(ARG_COLOR);
        }

        Log.e(TAG, "onCreate-" + name);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single, container, false);
        view.setBackgroundColor(getResources().getColor(color));

        titleTextView = (TextView) view.findViewById(R.id.single_text);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated-" + tag());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart-" + tag());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume-" + tag());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause-" + tag());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop-" + tag());
    }

    @NonNull
    @Override
    protected String tag() {
        return name;
    }

    @NonNull
    @Override
    protected PresenterFactory<SamplePresenter> getPresenterFactory() {
        return new SamplePresenterFactory(name);
    }

    @Override
    protected void onPresenterPrepared(@NonNull SamplePresenter presenter) {
        this.presenter = presenter;
        Log.i(TAG, "onPresenterPrepared-" + tag());
    }

    @Override
    public void showMessage(String text) {
        titleTextView.setText(text);
    }
}
