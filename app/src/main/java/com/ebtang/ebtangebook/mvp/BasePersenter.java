package com.ebtang.ebtangebook.mvp;


/**
 * 登录UI
 */
public abstract class BasePersenter<T extends MvpView> implements Presenter<T> , ResultListener {

    public ApiModel apiModel;

    public BasePersenter(){
        apiModel = new ApiModel(this);
    }


    private T mMvpView;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

    @Override
    public void onSuccess(String json) {
        try {
            setData(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Exception e) {
        try {
            mMvpView.showError("BasePersenter_error");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public abstract void getData();
    public abstract void setData(String obj);

}
