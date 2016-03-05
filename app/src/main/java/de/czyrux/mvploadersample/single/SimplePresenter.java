package de.czyrux.mvploadersample.single;

import de.czyrux.mvploadersample.base.Presenter;

public class SimplePresenter implements Presenter<SimpleView> {

    private final String title;
    private SimpleView view;
    int count = 0;

    public SimplePresenter(String title) {
        this.title = title;
    }

    @Override
    public void onViewAttached(SimpleView view) {
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
