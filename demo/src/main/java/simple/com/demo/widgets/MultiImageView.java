package simple.com.demo.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
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
    private List<ImageItem> mImages = new ArrayList<>();
    private Bitmap mLoadingBmp;
    private int mSingleImageWidth;
    private int mViewHeight;
    /**
     * 图片矩阵对象,用于对图片进行缩放
     */
    private Matrix mDrawMatrix = new Matrix();
    /**
     * 图片之间的水平间距
     */
    private int mHorizontalSpacing = 10;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MultiImageView(Context context) {
        this(context, null);
    }

    public MultiImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLoadingBmp = BitmapFactory.decodeResource(getResources(), R.drawable.imageloading);
        mHorizontalSpacing = convertToPx(3);
        pLeft = getPaddingLeft();
        pTop = getPaddingTop();
    }

    /**
     * 设置图片之间的水平间距
     *
     * @param dp 单位为dp
     */
    public void setmHorizontalSpacing(int dp) {
        mHorizontalSpacing = convertToPx(dp);
    }

    public void setImages(List<ImageItem> images) {
        if (images.size() < 3) {
            return;
        }
        mImages.clear();
        this.mImages.addAll(images);
        loadImagesAsync();
    }

    public int convertToPx(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
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

    int pLeft;
    int pTop;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 用来显示三章图片的完整区域
        int totalWidth = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - mHorizontalSpacing * 3);
        mSingleImageWidth = totalWidth / 3;
        mViewHeight = getMeasuredHeight();
        // 每张图片的位置以及宽高
        for (int i = 0; i < mImages.size(); i++) {
            final ImageItem item = mImages.get(i);
            // 设置该图绘制的位置, 这里的240为图片的宽度
            item.rect.set(pLeft + i * 240, pTop,
                    pLeft + 240 * (i + 1), pTop + mViewHeight);
            // 计算变换矩阵
            calculateMartrx(item);
        }
    }

    /**
     * 缩放图片 , 使用的模式参考 ImageView的center crop
     *
     * @param image
     */
    private void calculateMartrx(ImageItem image) {
        float scale;
        float dx = 0, dy = 0;
        int dwidth = image.getWidth();
        int dheight = image.getHeight();
        int vwidth = mSingleImageWidth;
        int vheight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        if (dwidth * vheight > vwidth * dheight) {
            scale = (float) vheight / (float) dheight;
            dx = (vwidth - dwidth * scale) * 0.5f;
        } else {
            scale = (float) vwidth / (float) dwidth;
            dy = (vheight - dheight * scale) * 0.5f;
        }

        mDrawMatrix.setScale(scale, scale);
        mDrawMatrix.postTranslate(Math.round(dx), Math.round(dy));
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        for (int i = 0; i < mImages.size(); i++) {
//            final ImageItem item = mImages.get(i);
//            // 绘制图片
//            if (item.bitmap != null) {
//                int saveCount = canvas.getSaveCount();
//                canvas.save();
//                canvas.translate(pLeft, pTop);
//                if (mDrawMatrix != null) {
//                    canvas.concat(mDrawMatrix);
//                }
//                // 绘制三张图片
//                canvas.drawBitmap(item.bitmap,
//                        item.rect.left + i * mHorizontalSpacing, item.rect.top, mPaint);
//                canvas.restoreToCount(saveCount);
//            } else {
//                // 绘制loading图
//                canvas.drawBitmap(mLoadingBmp, item.rect.left, item.rect.top, mPaint);
//            }
//        }

        for (int i = 0; i < mImages.size(); i++) {
            final ImageItem item = mImages.get(i);
            // 绘制图片
            int saveCount = canvas.getSaveCount();
            canvas.save();
            canvas.translate(pLeft, pTop);
            if (mDrawMatrix != null) {
                canvas.concat(mDrawMatrix);
            }
            // 绘制三张图片
            canvas.drawBitmap(item.bitmap,
                    item.rect.left + i * mHorizontalSpacing, item.rect.top, mPaint);
            canvas.restoreToCount(saveCount);
        }
    }
}
