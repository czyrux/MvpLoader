package de.czyrux.mvploadersample.presenter;

import android.util.Log;

import de.czyrux.mvploadersample.base.Presenter;

public class SamplePresenter implements Presenter<SampleView> {

    private static final String TAG = "presenter";
    private final String title;
    private SampleView view;
    private int count = 0;

    public SamplePresenter(String title) {
        this.title = title;
    }

    @Override
    public void onViewAttached(SampleView view) {
        Log.d(TAG, "P - onViewAttached");
        this.view = view;
        this.count++;
        this.view.showMessage(title + ". View attached " + count + " times");
    }

    @Override
    public void onViewDetached() {
        this.view = null;
        Log.d(TAG, "P - onViewDetached");
    }

    @Override
    public void onDestroyed() {
        Log.d(TAG, "P - onDestroyed");
        // Nothing to clean up
    }
}
