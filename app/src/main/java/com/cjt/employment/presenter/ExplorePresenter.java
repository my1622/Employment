package com.cjt.employment.presenter;

import android.util.Log;

import com.cjt.employment.bean.InformationBean;
import com.cjt.employment.bean.Recruit;
import com.cjt.employment.model.ExploreModelImp;
import com.cjt.employment.model.Imodel.ExploreModel;
import com.cjt.employment.model.Imodel.RecruitModel;
import com.cjt.employment.model.RecruitModelImp;
import com.cjt.employment.ui.fragment.ExploreFragment;
import com.cjt.employment.ui.fragment.ExploreView;
import com.cjt.employment.ui.fragment.HomeFragment;
import com.cjt.employment.ui.view.HomeView;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者: 陈嘉桐 on 2016/8/12
 * 邮箱: 445263848@qq.com.
 */
public class ExplorePresenter extends BasePresenter<ExploreFragment>{
    private ExploreModel mExploreModel;
    private ExploreView mExploreView;
    public ExplorePresenter(ExploreView mExploreView) {
        mExploreModel = ExploreModelImp.getInstance();
        this.mExploreView = mExploreView;
    }

    public void getInfomation(String action) {
        if (mExploreModel != null) {
            mExploreView.showProgressBar();
            mExploreModel.getInfomation(action)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<InformationBean>() {
                        @Override
                        public void call(InformationBean informationBean) {
                            mExploreView.updateInfomation(informationBean.getData());
                            mExploreView.hideProgressBar();
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.i("RxJava", "又是在这里出现了问题呀----->" + throwable.toString());
                            mExploreView.hideProgressBar();
                        }
                    });
        } else {
            Log.i("CJT", "model is null");
        }
    }
}
