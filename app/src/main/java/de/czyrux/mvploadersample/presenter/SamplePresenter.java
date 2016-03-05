package de.czyrux.mvploadersample.presenter;

import de.czyrux.mvploadersample.base.Presenter;

public class SamplePresenter implements Presenter<SampleView> {

    private final String title;
    private SampleView view;
    int count = 0;

    public SamplePresenter(String title) {
        this.title = title;
    }

    @Override
    public void onViewAttached(SampleView view) {
        this.view = view;
        this.count++;
        this.view.showMessage(title + ". View attached " + count + " times");
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void onDestroyed() {
        // Nothing to clean up
    }
}
