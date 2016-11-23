package com.fz.listener;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.fz.R;
import com.fz.adapter.BaseAdapterHelper;
import com.fz.adapter.BaseQuickAdapter;
import com.fz.pop.BaseFzProgressDialog;
import com.fz.pop.FzProgressDialog;
import com.fz.pulltorefresh.library.FzPullToRefreshListView;
import com.fz.pulltorefresh.library.PullToRefreshBase.State;
import com.fz.utils.SystemUtils;
import com.fz.utils.ToastUtils;

/**
 * 已弃用  由SimpleListViewCallBack加上PullToRefreshAdapterViewBase代替
 * FzHttpListener的实现类，主要针对FzPullToRefreshListView的数据处理及托管
 * Toast和ProgressDialog的托管
 * 
 * @author cate 2014-12-4 上午10:00:34
 */
@Deprecated
public class SimpleListViewFzHttpListener<T> implements FzHttpListener {

	private int mPage;
	@SuppressWarnings("unused")
	private int mPageSize;
	private Context mContext;
	private BaseQuickAdapter<T, BaseAdapterHelper> mAdapter;
	private FzPullToRefreshListView mRefreshView;
	private BaseFzProgressDialog mProgressDialog;

	public SimpleListViewFzHttpListener(int mPage, int mPageSize,
			Context mContext, BaseQuickAdapter<T, BaseAdapterHelper> mAdapter,
			FzPullToRefreshListView refreshView) {
		super();
		this.mPage = mPage;
		this.mPageSize = mPageSize;
		this.mContext = mContext;
		this.mAdapter = mAdapter;
		this.mRefreshView = refreshView;
		mProgressDialog = new FzProgressDialog(mContext);
	}

	@Override
	public void onSuccess(Object t) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<T> data = (List<T>) t;
		if (data == null) {
			if (mPage != 1) {
				ToastUtils.showLongToast(R.string.tips_pageending);
			} else {
				data = new ArrayList<T>();
				mAdapter.replaceAll(data);
				ToastUtils.showLongToast(R.string.tips_nodata);
				showDialog(false);
			}
		} else {
			if (mPage == 1) {
				showDialog(false);
				mAdapter.replaceAll(data);
			} else {
				mAdapter.addAll(data);
			}
			mRefreshView.addPage();
		}

		refreshComplete();
	}

	
	
	@Override
	public void onFailure(Throwable t, int errorNo, String strMsg) {
		if (mPage == 1) {
			showDialog(false);
		}

		if (!SystemUtils.checkNet(mContext)) {
			ToastUtils.showLongToast(R.string.error_net);
		} else {
			if (errorNo==888)
			{
				if (mPage != 1) {
					ToastUtils.showLongToast(R.string.tips_pageending);
				} else {
					mAdapter.replaceAll( new ArrayList<T>());
					ToastUtils.showLongToast(R.string.tips_nodata);
					showDialog(false);
				}
			}else {
				ToastUtils.showLongToast(R.string.tips_failedload);
			}
			
		}
		refreshComplete();
	}

	@Override
	public void onLoading(long count, long current) {

	}


	private void refreshComplete() {
		if (mRefreshView != null) {
			if (mRefreshView.getState() == State.REFRESHING) {
				mRefreshView.onRefreshComplete();
			}

		}
	}

	private void showDialog(boolean isShow) {

		mProgressDialog.setShowMessage(null);
		if (isShow) {
			mProgressDialog.showProgress();
		} else {
			mProgressDialog.dismissProgress();
		}
	}

	@Override
	public void onStart() {
		if (mPage == 1) {
			showDialog(true);
			// newDialog(true);
		}
	}
}
