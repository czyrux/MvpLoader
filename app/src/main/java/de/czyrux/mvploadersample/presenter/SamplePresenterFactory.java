package de.czyrux.mvploadersample.presenter;

import de.czyrux.mvploadersample.base.PresenterFactory;

public class SamplePresenterFactory implements PresenterFactory<SamplePresenter>{

    private final String title;

    public SamplePresenterFactory(String title) {
        this.title = title;
    }

    @Override
    public SamplePresenter create() {
        return new SamplePresenter(title);
    }
}
