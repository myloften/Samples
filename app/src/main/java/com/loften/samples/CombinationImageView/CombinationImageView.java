package com.loften.samples.CombinationImageView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.loften.samples.R;

import java.io.File;
import java.util.Vector;

public class CombinationImageView extends LinearLayout {
	private final String tag = CombinationImageView.class.getSimpleName();
	private final boolean VDEBUG = true;
	private final int MAX_SIZE = 9;
	private Object lock = null;
	private int ROW_COUNT = 3;

	private ImageView mImageView = null;
	private Context mContext = null;

	private int mImgSpace = 5;

	private int mImgWidth = 0;
	private int mImgHeight = 0;

	private int mViewWidth = 0;
	private int mViewHeight = 0;

	/**
	 * if true,show wait text in file downloading
	 */
	private boolean mEnableWaitText = true;
	/**
	 * wait text
	 */
	private String mWaitText = "Loading";

	private Vector<Bitmap> mBitVec = null;

	public CombinationImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.d(tag, "CombinationImageView create");
		mContext = context;
		init();
	}

	public CombinationImageView(Context context) {
		super(context);
		Log.d(tag, "CombinationImageView create");
		mContext = context;
		init();
	}

	private void init() {
		lock = new Object();
		Log.d(tag, "call init");
		mBitVec = new Vector<Bitmap>(MAX_SIZE);
		View view = LayoutInflater.from(mContext).inflate(
				R.layout.layout_imageview, this, true);
		mImageView = (ImageView) view.findViewById(R.id.iv);
		mImageView.setWillNotDraw(true);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mViewHeight = getHeight();
		mViewWidth = getWidth();
		Log.d(tag, "onMeasure ViewHeight" + mViewHeight + " -- Width"
				+ mViewWidth);
		if (mViewHeight != 0 && mViewWidth != 0) {
			synchronized (lock) {
				lock.notify();
			}
		}
	}

	public void setEnableWaitText(boolean b, String msg) {
		mEnableWaitText = b;
		mWaitText = msg;
	}

	public void loadImg(Vector<String> vec) throws Exception {
		Log.d(tag, "call loadImg");
		if (vec.size() > MAX_SIZE) {
			throw new Exception("MAX_SIZE is " + MAX_SIZE);
		}
		new AsyncImgLoad(vec).execute();
	}

	class AsyncImgLoad extends AsyncTask<Void, Integer, Boolean> {
		private Vector<String> mVec = null;
		/**
		 * max file size
		 */
		private final long MAX_FILE_SIZE = 1024;

		public AsyncImgLoad(Vector<String> mVec) {
			super();
			this.mVec = mVec;
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			synchronized (lock) {
				try {
					// wait to onMeasure executed and get view size
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (VDEBUG) {
				Log.d(tag, "doInBackground");
			}
			if (mVec.size() == 0) {
				return false;
			}
			preLoad();
			HttpClientHelper mHelper = new HttpClientHelper();
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 4;
			for (String path : mVec) {
				File fp = null;
				if (isNetFile(path)) {
					String resPath = mHelper.download(path);
					fp = new File(resPath);
				} else {
					fp = new File(path);
				}

				if (fp.exists()) {
					Bitmap bm = null;
					if (fp.length() <= MAX_FILE_SIZE) {
						bm = BitmapFactory.decodeFile(fp.getAbsolutePath());
					} else {
						bm = BitmapFactory.decodeFile(fp.getAbsolutePath(),
								options);
					}
					if (bm != null) {
						mBitVec.add(Bitmap.createScaledBitmap(bm, mImgWidth,
								mImgHeight, true));
						bm.recycle();
					} else {
						Log.e(tag, "decodeFile:" + fp.getAbsolutePath()
								+ "failed!");
					}
				} else {
					if (VDEBUG) {
						Log.e(tag, "file:" + path + "does not exit!");
					}
				}
			}
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (VDEBUG) {
				Log.d(tag, "onPostExecute Result:" + result);
			}
			if (result) {
				if (VDEBUG) {
					Log.d(tag, "call invalidate");
				}
				invalidate();
			}
		}

		private void preLoad() {
			int b_width = mViewWidth;
			int b_hegith = mViewHeight;
			switch (mVec.size()) {
			case 2:
				b_width = mViewWidth / 2;
				break;
			case 3:
			case 4:
				b_hegith = mViewHeight / 2;
				b_width = mViewWidth / 2;
				ROW_COUNT = 2;
				break;
			case 5:
			case 6:
				b_hegith = mViewHeight / 3;
				b_width = mViewWidth / 2;
				ROW_COUNT = 2;
				break;
			case 7:
			case 8:
			case 9:
				b_hegith = mViewHeight / 3;
				b_width = mViewWidth / 3;
				ROW_COUNT = 3;
				break;
			}

			mImgHeight = b_hegith - mImgSpace;
			mImgWidth = b_width - mImgSpace;
		}

		private boolean isNetFile(String path) {
			if (path.startsWith("http://") && path.endsWith(".jpg")) {
				return true;
			} else {
				return false;
			}
		}

	}

	@Override
	protected void onDetachedFromWindow() {
		// TODO Auto-generated method stub
		super.onDetachedFromWindow();
		for (Bitmap bm : mBitVec) {
			if (bm != null && !bm.isRecycled()) {
				bm.recycle();
			}
		}
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (VDEBUG) {
			Log.d(tag, "call dispatchDraw");
		}
		if (mBitVec.size() == 0 && mEnableWaitText) {
			Paint pt = new Paint();
			pt.setTextSize(pt.getTextSize() * 5);
			pt.setColor(Color.BLACK);
			canvas.drawText(mWaitText,
					(mViewWidth - pt.measureText(mWaitText)) / 2,
					mViewHeight / 2, pt);
		}
		int mTotalWidth = 0;
		int mTotalHeight = 0;
		for (int i = 0; i < mBitVec.size(); i++) {
			Bitmap bm = mBitVec.get(i);
			if (bm != null) {
				int mWdith = bm.getWidth() + mImgSpace;
				int mHeight = bm.getHeight() + mImgSpace;
				canvas.drawBitmap(bm, mTotalWidth, mTotalHeight, null);
				if ((i + 1) % ROW_COUNT == 0) {
					mTotalWidth = 0;
					mTotalHeight += mHeight;
				} else {
					mTotalWidth += mWdith;
				}

			}
		}
	}

}
