package simple.com.demo.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import simple.com.demo.ImageItem;
import simple.com.demo.R;

/**
 * 在一个View上绘制三张图片的View,将图片数据传递进来之后首先绘制Loading图,然后通过ImageLoader加载图片,加载完图片之后重绘对应的区域.
 * <p/>
 * Created by mrsimple on 13/4/16.
 */
public class MultiImageView extends View {
    /**
     * 显示图片总数
     */
    public static final int ImageNumber=2;
    /**
     * 图片集合
     */
    private List<ImageItem> mImages = new ArrayList<>();
    /**
     * loadding中的图片
     */
    private Bitmap mLoadingBmp;
    /**
     * 单张图片的宽度
     */
    private int mSingleImageWidth;
    /**
     * 视图的高度
     */
    private int mViewHeight;
    /**
     * 图片矩阵对象,用于对图片进行缩放
     */
//    private Matrix mDrawMatrix = new Matrix();
    /**
     * 图片之间的水平间距
     */
    private int mHorizontalSpacing = 10;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int pLeft;
    private int pTop;
    private OnImageClickListener mImageClickListener;
    static BitmapFactory.Options sOptions = new BitmapFactory.Options();


    public MultiImageView(Context context) {
        this(context, null);
    }

    public MultiImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        sOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        mLoadingBmp = BitmapFactory.decodeResource(getResources(), R.drawable.imageloading, sOptions);
        mHorizontalSpacing = convertToPx(ImageNumber);
        pLeft = getPaddingLeft();
        pTop = getPaddingTop();
    }


    /**
     * 设置图片之间的水平间距
     *
     * @param dp 单位为dp
     */
    public void setHorizontalSpacing(int dp) {
        mHorizontalSpacing = convertToPx(dp);
    }

    /**
     * 设置图片集
     *
     * @param images
     */
    public void setImages(List<ImageItem> images) {
        if (images.size() < ImageNumber) {
            return;
        }
        mImages.clear();
        this.mImages.addAll(images);
        loadImagesAsync();
    }

    /**
     * 异步加载图片
     */
    private void loadImagesAsync() {
        for (int i = 0; i < mImages.size(); i++) {
            final ImageItem item = mImages.get(i);
            item.bitmap = mLoadingBmp;
            ImageLoader.getInstance().loadImage(item.url, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String s, View view) {

                }

                @Override
                public void onLoadingFailed(String s, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                    // 判断该View是否被复用,避免图片显示错乱
                    if (!TextUtils.isEmpty(s) && s.equals(item.url)) {
                        item.bitmap = bitmap;
                        invalidate(item.rect);
                    }
                }

                @Override
                public void onLoadingCancelled(String s, View view) {

                }
            });
        }
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 用来显示三章图片的完整区域
        int totalWidth = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - mHorizontalSpacing * (ImageNumber-1));
        mSingleImageWidth = totalWidth / ImageNumber;
        mViewHeight = getMeasuredHeight();
        // 每张图片的位置以及宽高
        for (int i = 0; i < mImages.size(); i++) {
            final ImageItem item = mImages.get(i);
            // 计算变换矩阵
            calculateMartrx(item);
            float leftDraw=(i * (mHorizontalSpacing + mSingleImageWidth)) / item.scaleW;
            float topDraw=item.rect.top;
            float rightDraw=leftDraw+mSingleImageWidth/item.scaleW;
            float bottomDeaw=topDraw+(getMeasuredHeight() - getPaddingTop() - getPaddingBottom()) / item.scaleW;
            // 设置该图绘制的位置, 图片的绘制位置参数会跟随matrix参数的scale 同步放大缩小，比如如果绘制起点是100px处同时matrix.scale=0.5
//            则实际绘制中绘制起点在50px处
            item.rect.set((int)leftDraw, (int)topDraw,
                    (int)rightDraw, (int)bottomDeaw);
        }
    }

    /**
     * 缩放图片 , 使用的模式参考 ImageView的center crop
     *
     * @param image
     */
    private void calculateMartrx(ImageItem image) {
        float scale;
        float dwidth = image.bitmap.getWidth();
        float dheight = image.bitmap.getHeight();
        int vwidth = mSingleImageWidth;
        int vheight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        if (dwidth * vheight > vwidth * dheight) {
            scale = (float) vheight / (float) dheight;
        } else {
            scale = (float) vwidth / (float) dwidth;
        }
        Matrix mDrawMatrix = new Matrix();
        mDrawMatrix.setScale(scale, scale);
        image.matrix=mDrawMatrix;
        image.scaleW=scale;



    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < mImages.size(); i++) {
            final ImageItem item = mImages.get(i);
            // 绘制图片
            int saveCount = canvas.getSaveCount();
            canvas.save();
            canvas.translate(pLeft, pTop);
            if (item.matrix != null) {
                canvas.concat(item.matrix);
            }
            // 设置该图绘制的区域, 图片的绘制区域参数会跟随matrix参数的scale 同步放大缩小，比如如果绘制起点是100px处同时matrix.scale=0.5
//            则实际绘制中绘制起点在50px处
            canvas.clipRect(item.rect);
            // 绘制三张图片
            //  canvas.drawBitmap(）中绘制起点位置随matrix参数的scale 同步放大缩小,比如如果绘制起点是100px处同时matrix.scale=0.5
//            则实际绘制中绘制起点在50px处
            canvas.drawBitmap(item.bitmap,
                    item.rect.left, item.rect.top, mPaint);
            canvas.restoreToCount(saveCount);
        }
    }

    /**
     * 设置loading 图片
     *
     * @param res
     */
    public void setLoadingBitmapResource(int res) {
        if (this.mLoadingBmp != null
                && !mLoadingBmp.isRecycled()) {
            mLoadingBmp.recycle();
            mLoadingBmp = null;
        }
        this.mLoadingBmp = BitmapFactory.decodeResource(getResources(), res, sOptions);
        invalidate();
    }

    public void setImageClickListener(OnImageClickListener listener) {
        this.mImageClickListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            handleClickImageEvent(event);
        }
        return super.onTouchEvent(event);
    }

    /**
     * 通过点击的x坐标位置判断点击的是第几张图,然后回调给调用者
     *
     * @param event
     */
    private void handleClickImageEvent(MotionEvent event) {
        // 获取点击的位置
        int clickIndex = (int) event.getX() / mSingleImageWidth;
        if (mImageClickListener != null && clickIndex < mImages.size()) {
            mImageClickListener.onClick(clickIndex);
        }
    }

    public int convertToPx(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 图片点击Listener
     */
    public static interface OnImageClickListener {
        public void onClick(int position);
    }
}
