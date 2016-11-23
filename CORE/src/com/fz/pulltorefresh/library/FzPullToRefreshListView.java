package com.fz.pulltorefresh.library;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ListView;
import com.fz.adapter.BaseAdapterHelper;
import com.fz.adapter.BaseQuickAdapter;
import com.fz.afinal.http.AjaxParams;
import com.fz.afinal.http.FzHttpRequest;
import com.fz.listener.FzHttpListener;
import com.fz.listener.SimpleListViewFzHttpListener;
import com.fz.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.fz.utils.LogUtils;

/**
 * @author cate 2014-12-4 下午3:13:29
 * 
 */

@Deprecated
public class FzPullToRefreshListView extends PullToRefreshListView implements OnRefreshListener2<ListView>
{
	
	/**
	 * 网络数据的回调接口， 如果为空，则会调用默认的SimpleListViewFzHttpListener 一般下拉刷新的控件，如无特殊要求，都可以使用默认的实现方式，不需要设置回调接口
	 */
	private FzHttpListener httpListener;
	
	public FzPullToRefreshListView(Context context)
	{
		super(context);
	}
	
	public FzPullToRefreshListView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}
	
	public FzPullToRefreshListView(Context context, Mode mode)
	{
		super(context, mode);
	}
	
	public FzPullToRefreshListView(Context context, Mode mode, AnimationStyle style)
	{
		super(context, mode, style);
	}
	
	public String getUrl()
	{
		if (mConfig!=null)
		{
			return mConfig.getUrl();
		}else {
			return "";
		}
	}
	
	/**
	 * 设置需要加载的链接地址
	 * 
	 * @param url
	 * @see #getUrl()
	 */
	public void setUrl(String url)
	{
		if (mConfig!=null)
		{
			mConfig.setUrl(url);
		}
	}
	
	/**
	 * 获取网络请求参数
	 * 
	 * @return
	 */
	public AjaxParams getParams()
	{
		if (mConfig!=null)
		{
			return	mConfig.getParams();
		}
			return null;
	}
	
	/**
	 * 设置网络请求参数 Page PageSize不需要额外设定
	 * 
	 * @param params
	 * @see #getParams()
	 */
	public void setParams(AjaxParams params)
	{
		if (mConfig!=null)
		{
			mConfig.setParams(params);
		}
	}
	
	public FzHttpListener getHttpListener()
	{
		return httpListener;
	}
	
	/**
	 * 注册网络返回数据的监听 默认监听为SimpleListViewFzHttpListener，一般来说，使用默认的就行
	 * 
	 * @param httpListener
	 * @see #getHttpListener()
	 */
	public void setHttpListener(FzHttpListener httpListener)
	{
		this.httpListener = httpListener;
	}
	
	public int getPage()
	{
		if (mConfig!=null)
		{
			return mConfig.getPage();
		}
		return 0;
	}
	
	/**
	 * 设置当前加载的页面
	 * 
	 * @param page
	 * @see #getPage()
	 */
	public void setPage(int page)
	{
		if (mConfig!=null)
		{
			mConfig.setPage(page);
		}
	}
	
	public int getPageSize()
	{
		if (mConfig!=null)
		{
			mConfig.getPage();
		}
		return 0;
	}
	
	/**
	 * 设置当前每页加载的数量
	 * 
	 * @param pageSize
	 * @see #getPageSize()
	 */
	public void setPageSize(int pageSize)
	{
		if (mConfig!=null)
		{
			mConfig.setPageSize(pageSize);
		}
	}
	
	/**
	 * 当前Page+1
	 */
	public void addPage()
	{
		if (mConfig!=null)
		{
			mConfig.addPage();
		}
	}
	
	
	
	private PullConfig<?> mConfig;
	
	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public void initConfig(Context context, Object b,BaseQuickAdapter<?, BaseAdapterHelper> mAdapter)
	{
		if (mConfig==null)
		{
			mConfig = new PullConfig(context, b,mAdapter);
			mConfig.setmRefreshListView(this);
		}
		setOnRefreshListener(this);
	}
	
	
	public void startLoading(){
		if (mConfig!=null)
		{
			mConfig.startLoading();
		}
	}
	
	public static class PullConfig<T>
	{
		private T t;
		/**
		 * 加载的指定页
		 */
		private int Page = 1;
		/**
		 * 每页加载的大小 默认为20
		 */
		private int PageSize = 20;
		/**
		 * 网络请求的地址
		 */
		private String Url;
		/**
		 * 网络请求的参数
		 */
		private AjaxParams Params;
		
		private Context mContext;
		private FzHttpListener httpListener;
		private BaseQuickAdapter<T, BaseAdapterHelper> mAdapter;
		private PullToRefreshListView mRefreshListView;
		
		public PullConfig(Context pContext, T t,BaseQuickAdapter<T, BaseAdapterHelper> mAdapter)
		{
			this.mContext = pContext;
			this.t = t;
			this.mAdapter = mAdapter;
		}
		
		public void startLoading()
		{
			FzHttpRequest http = FzHttpRequest.getInstance();
			if (TextUtils.isEmpty(getUrl()) || getParams() == null)
			{
				LogUtils.e("dsd", "您还没有设置Url或参数");
				return;
			}
			Params.put("pageindex", String.valueOf(getPage()));
			Params.put("pagesize", String.valueOf(PageSize));
			
			if (getHttpListener() == null)
			{
				http.post(Url, Params,
						new SimpleListViewFzHttpListener<T>(Page, PageSize, mContext, mAdapter, (FzPullToRefreshListView) getmRefreshListView()), (Class<?>) t);
			}
			else
			{
				http.post(Url, Params, getHttpListener(), (Class<?>) t);
			}
			
		}
		
		public String getUrl()
		{
			return Url;
		}
		
		/**
		 * 设置需要加载的链接地址
		 * 
		 * @param url
		 * @see #getUrl()
		 */
		public void setUrl(String url)
		{
			Url = url;
		}
		
		/**
		 * 获取网络请求参数
		 * 
		 * @return
		 */
		public AjaxParams getParams()
		{
			return Params;
		}
		
		/**
		 * 设置网络请求参数 Page PageSize不需要额外设定
		 * 
		 * @param params
		 * @see #getParams()
		 */
		public void setParams(AjaxParams params)
		{
			Params = params;
		}
		
		public FzHttpListener getHttpListener()
		{
			return httpListener;
		}
		
		/**
		 * 注册网络返回数据的监听 默认监听为SimpleListViewFzHttpListener，一般来说，使用默认的就行
		 * 
		 * @param httpListener
		 * @see #getHttpListener()
		 */
		public void setHttpListener(FzHttpListener httpListener)
		{
			this.httpListener = httpListener;
		}
		
		public int getPage()
		{
			return Page;
		}
		
		/**
		 * 设置当前加载的页面
		 * 
		 * @param page
		 * @see #getPage()
		 */
		public void setPage(int page)
		{
			Page = page;
		}
		
		public int getPageSize()
		{
			return PageSize;
		}
		
		/**
		 * 设置当前每页加载的数量
		 * 
		 * @param pageSize
		 * @see #getPageSize()
		 */
		public void setPageSize(int pageSize)
		{
			PageSize = pageSize;
		}
		
		/**
		 * 当前Page+1
		 */
		public void addPage()
		{
			Page++;
		}

		public PullToRefreshListView getmRefreshListView()
		{
			return mRefreshListView;
		}

		public void setmRefreshListView(PullToRefreshListView mRefreshListView)
		{
			this.mRefreshListView = mRefreshListView;
		}
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
	{
		setPage(1);
		startLoading();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
	{
		startLoading();
	}
	
}
