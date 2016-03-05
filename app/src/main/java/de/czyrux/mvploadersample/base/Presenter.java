package de.czyrux.mvploadersample.base;

public interface Presenter <V>{
    void onViewAttached(V view);
    void onViewDetached();
    void onDestroyed();
}
