package de.czyrux.mvploadersample.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

class LoaderHandler<P extends Presenter> {

    private static final int LOADER_ID = -100;

    private final String logTag;
    private final Context context;
    private final LoaderManager loaderManager;
    private final PresenterFactory<P> factory;
    private P presenter;

    public LoaderHandler(String logTag, Context context, LoaderManager loaderManager, PresenterFactory<P> factory) {
        this.logTag = logTag;
        this.context = context;
        this.loaderManager = loaderManager;
        this.factory = factory;
    }

    public void init(Receiver<P> receiver) {
        Loader<P> loader = loaderManager.getLoader(LOADER_ID);
        if (loader == null) {
            initLoader(receiver);
        } else {
            presenter = ((PresenterLoader<P>) loader).getPresenter();
            receiver.onPresenterCreatedOrRestored(presenter);
        }
    }

    private void initLoader(Receiver<P> receiver) {
        // LoaderCallbacks as an object, so no hint regarding loader will be leak to the subclasses.
        loaderManager.initLoader(LOADER_ID, null, new LoaderManager.LoaderCallbacks<P>() {
            @Override
            public final Loader<P> onCreateLoader(int id, Bundle args) {
                Log.d(logTag, "onCreateLoader");
                return new PresenterLoader<>(context, factory, logTag);
            }

            @Override
            public final void onLoadFinished(Loader<P> loader, P presenter) {
                Log.d(logTag, "onLoadFinished");
                receiver.onPresenterCreatedOrRestored(presenter);
            }

            @Override
            public final void onLoaderReset(Loader<P> loader) {
                Log.d(logTag, "onLoaderReset");
                LoaderHandler.this.presenter = null;
            }
        });
    }

    interface Receiver<P extends Presenter> {
        void onPresenterCreatedOrRestored(@NonNull P presenter);
    }
}
