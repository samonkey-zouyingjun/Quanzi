
package com.fz.listener;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.view.View;
import com.fz.R;
import com.fz.adapter.BaseAdapterHelper;
import com.fz.adapter.BaseQuickAdapter;
import com.fz.afinal.http.AjaxParams;
import com.fz.afinal.http.FzHttpResponse;
import com.fz.base.FzHttpReq;
import com.fz.pop.BaseFzProgressDialog;
import com.fz.pop.FzProgressDialog;
import com.fz.pop.ProgressDialog;
import com.fz.pulltorefresh.library.PullToRefreshAdapterViewBase;
import com.fz.pulltorefresh.library.PullToRefreshBase;
import com.fz.pulltorefresh.library.PullToRefreshBase.State;
import com.fz.utils.SystemUtils;
import com.fz.utils.ToastUtils;
import com.fz.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;

/**
 * FzCallBack的子类，主要针对PullToRefreshAdapterViewBase的数据处理及托管 Toast和ProgressDialog的托管 适用于所有PullToRefreshAdapterViewBase的子类
 * 
 * @author cate 2014-12-4 上午10:00:34
 */
public abstract class SimpleListViewCallBack<T> extends FzCallBack<T> implements OnRefreshListener2<View>
{
	private String url;
	private AjaxParams mParams;
	private int mPage = 1;
	private int mPageSize = 20;
	private Context mContext;
	private BaseQuickAdapter<T, BaseAdapterHelper> mAdapter;
	private PullToRefreshAdapterViewBase mRefreshView;
	private BaseFzProgressDialog mProgressDialog;
	private FzHttpReq mReq;
	
	private boolean showDialog = true;
	
	
	/**
	 * 加载中
	 */
	public static final int MODE_START = 0;
	/**
	 * 没数据
	 */
	public static final int MODE_NODATA = 1;
	/**
	 * 数据加载失败
	 */
	public static final int MODE_ERROR = 2;
	/**
	 * 数据加载成功
	 */
	public static final int MODE_SUCCESS = 3;
	
	/**
	 * Page参数 默认为pageindex
	 */
	private String mPageParams = "pageindex";
	/**
	 * PageSize参数 默认为pagesize
	 */
	private String mPageSizeParams = "pagesize";
	
	
	private boolean isPost = true;
	
	
	/**
	 * 默认Page为1 PageSize为20 具体见{@link SimpleListViewCallBack}
	 * 
	 * @param mContext
	 * @param url
	 * @param mAdapter
	 * @param refreshView
	 */
	public SimpleListViewCallBack(Context mContext, String url, BaseQuickAdapter<T, BaseAdapterHelper> mAdapter,
			PullToRefreshAdapterViewBase refreshView)
	{
		this(mContext, 1, 20, url, mAdapter, refreshView,true);
	}
	
	/**
	 * 
	 * @param mContext
	 * @param mPage
	 * @param mPageSize
	 * @param url
	 *            网络加载地址
	 * @param mAdapter
	 *            设配器 BaseQuickAdapter子类
	 * @param refreshView
	 *            PullToRefreshAdapterViewBase子类
	 */
	public SimpleListViewCallBack(Context mContext, int mPage, int mPageSize, String url,
			BaseQuickAdapter<T, BaseAdapterHelper> mAdapter, PullToRefreshAdapterViewBase refreshView,boolean showdialog)
	{
		super();
		this.mPage = mPage;
		this.url = url;
		this.mPageSize = mPageSize;
		this.mContext = mContext;
		this.mAdapter = mAdapter;
		this.mRefreshView = refreshView;
		this.showDialog = showdialog;
		mParams = new AjaxParams();
		setFzHttpReq(new FzHttpReq());
		
		this.mRefreshView.setOnRefreshListener(this);
		
//		this.mRefreshView.setOnRefreshListener(new OnRefreshListener2<View>()
//		{
//			@Override
//			public void onPullDownToRefresh(PullToRefreshBase<View> refreshView)
//			{
//				// TODO Auto-generated method stub
//				setPage(1);
//				startLoading();
//			}
//			
//			@Override
//			public void onPullUpToRefresh(PullToRefreshBase<View> refreshView)
//			{
//				// TODO Auto-generated method stub
//				startLoading();
//			}
//		});
		mProgressDialog = new ProgressDialog(mContext);
	}
	
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<View> refreshView)
	{
		// TODO Auto-generated method stub
		setPage(1);
		startLoading();
	}
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<View> refreshView)
	{
		startLoading();
	}
	
	@Override
	public void onSuccess(FzHttpResponse<T> response)
	{
		super.onSuccess(response);
		if (response.getFlag().equals("0"))
		{
			List<T> data = response.getDatalist();
			if (data == null)
			{
				if (getPage() != 1)
				{
					ToastUtils.showLongToast(R.string.tips_pageending);
				}
				else
				{
					data = new ArrayList<T>();
					mAdapter.replaceAll(data);
					ToastUtils.showLongToast(R.string.tips_nodata);
					showDialog(false);
					
					// 没数据
					// loadNoData();
					
					loadResult(MODE_NODATA);
				}
			}
			else
			{
				if (getPage() == 1)
				{
					showDialog(false);
					mAdapter.replaceAll(data);
				}
				else
				{
					mAdapter.addAll(data);
				}
				mPage++;
				
				// 数据加载成功
				loadResult(MODE_SUCCESS);
			}
			refreshComplete();
		}
		else
		{
			if (getPage() != 1)
			{
				ToastUtils.showLongToast(R.string.tips_pageending);
			}
			else
			{
				mAdapter.replaceAll(new ArrayList<T>());
				ToastUtils.showLongToast(R.string.tips_nodata);
				showDialog(false);
				loadResult(MODE_NODATA);
				
			}
			refreshComplete();
		}
	}
	
	@Override
	public void onFailure(Throwable t, int errorNo, String strMsg)
	{
		if (getPage() == 1)
		{
			showDialog(false);
		}
		if (!SystemUtils.checkNet(mContext))
		{
			ToastUtils.showLongToast(R.string.error_net);
		}
		else
		{
			ToastUtils.showLongToast(R.string.tips_failedload);
		}
		refreshComplete();
		
		// loadFailure();
		loadResult(MODE_ERROR);
		
	}
	
	private void refreshComplete()
	{
		if (mRefreshView != null)
		{
			if (mRefreshView.getState() == State.REFRESHING)
			{
				mRefreshView.onRefreshComplete();
			}
			
		}
	}
	
	private void showDialog(boolean isShow)
	{
		mProgressDialog.setShowMessage(null);
		if (isShow && showDialog)
		{
			mProgressDialog.showProgress();
		}
		else
		{
			mProgressDialog.dismissProgress();
		}
	}
	
	@Override
	public void onStart()
	{
		if (getPage() == 1)
		{
			showDialog(true);
			// startFirstLoding();
			loadResult(MODE_START);
		}
	}
	
	/**
	 * 第一次加载
	 */
	public void firstLoading(){
		getParams().put(getPageParams(),"1");
		getParams().put(getPageSizeParams(), String.valueOf(getPageSize()));
		
		
		if (isPost)
		{
			getFzHttpReq().post(getUrl(),getParams(), this);
		}else {
			getFzHttpReq().get(getUrl(),getParams(), this);
		}
		
		
	}
	
	/**
	 * 从当前page页开始加载
	 */
	public void startLoading()
	{
		getParams().put(getPageParams(), String.valueOf(getPage()));
		getParams().put(getPageSizeParams(), String.valueOf(getPageSize()));
//		getFzHttpReq().post(getUrl(), getParams(), this);
		if (isPost)
		{
			getFzHttpReq().post(getUrl(),getParams(), this);
		}else {
			getFzHttpReq().get(getUrl(),getParams(), this);
		}
	}
	
	public AjaxParams getParams()
	{
		return mParams;
	}
	
	/**
	 * 获取提交的参数
	 * 
	 * @param mParams
	 */
	public void setParams(AjaxParams mParams)
	{
		this.mParams = mParams;
	}
	
	public int getPage()
	{
		return mPage;
	}
	
	/**
	 * 设置当前加载的页数
	 * 
	 * @param mPageSize
	 */
	public void setPage(int mPage)
	{
		this.mPage = mPage;
	}
	
	public int getPageSize()
	{
		return mPageSize;
	}
	
	/**
	 * 设置没页加载的数量
	 * 
	 * @param mPageSize
	 */
	public void setPageSize(int mPageSize)
	{
		this.mPageSize = mPageSize;
	}
	
	public String getUrl()
	{
		return url;
	}
	
	/**
	 * 设置加载的基础Url
	 * 
	 * @param url
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	public String getPageParams()
	{
		return mPageParams;
	}
	
	/**
	 * 设置Page的参数 默认为pageindex
	 * 
	 * @param mPageParams
	 */
	public void setPageParams(String mPageParams)
	{
		this.mPageParams = mPageParams;
	}
	
	public String getPageSizeParams()
	{
		return mPageSizeParams;
	}
	
	/**
	 * 设置PageSize的参数 默认为pagesize
	 * 
	 * @param mPageSizeParams
	 */
	public void setPageSizeParams(String mPageSizeParams)
	{
		this.mPageSizeParams = mPageSizeParams;
	}
	
	/**
	 * 数据加载结果
	 * 
	 * @param resultMode
	 */
	public void loadResult(int resultMode)
	{
		
	}

	public FzHttpReq getFzHttpReq()
	{
		return mReq;
	}

	public void setFzHttpReq(FzHttpReq mReq)
	{
		this.mReq = mReq;
	}

	public boolean isPost()
	{
		return isPost;
	}

	public void setPost(boolean isPost)
	{
		this.isPost = isPost;
	}
	
}
