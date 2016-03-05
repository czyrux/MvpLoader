package de.czyrux.mvploadersample.single;

import de.czyrux.mvploadersample.base.PresenterFactory;

public class SimplePresenterFactory implements PresenterFactory<SimplePresenter>{

    private final String title;

    public SimplePresenterFactory(String title) {
        this.title = title;
    }

    @Override
    public SimplePresenter create() {
        return new SimplePresenter(title);
    }
}
