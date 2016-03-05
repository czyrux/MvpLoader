package de.czyrux.mvploadersample.single;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import de.czyrux.mvploadersample.R;
import de.czyrux.mvploadersample.base.BasePresenterFragment;
import de.czyrux.mvploadersample.base.PresenterFactory;

public class SimpleFragment extends BasePresenterFragment<SimplePresenter> implements SimpleView {
    private static final String TAG = "SingleFragment";
    private static final String ARG_NAME = "name";
    private static final String ARG_COLOR = "color";

    @ColorRes
    private int color;
    private String name;

    private TextView titleTextView;
    private SimplePresenter presenter;

    public static SimpleFragment newInstance(String name, @ColorRes int color) {
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putInt(ARG_COLOR, color);

        SimpleFragment fragment = new SimpleFragment();
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
        Log.d(TAG, "onCreateView-" + name);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated-" + name);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated-" + tag());
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart-" + tag());
        Log.d(TAG, "onStart-is_null:" + String.valueOf(presenter == null).toUpperCase(Locale.ENGLISH));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume-" + tag());
        Log.e(TAG, "presenter.onViewAttached-" + tag());
        presenter.onViewAttached(this);
    }

    @Override
    public void onPause() {
        presenter.onViewDetached();
        super.onPause();
        Log.d(TAG, "onPause-" + name);

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop-" + name);
    }

    @Override
    protected String tag() {
        return name;
    }

    @Override
    protected PresenterFactory<SimplePresenter> getPresenterFactory() {
        return new SimplePresenterFactory(name);
    }

    @Override
    protected void onPresenterPrepared(SimplePresenter presenter) {
        this.presenter = presenter;
        Log.d(TAG, "onPresenterPrepared-" + tag());
    }

    @Override
    public void showMessage(String text) {
        titleTextView.setText(text);
    }
}
