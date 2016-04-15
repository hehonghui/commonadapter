package simple.com.demo;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;

/**
 * Created by mrsimple on 13/4/16.
 */
public class ImageItem {
    public String url = "";
    public Rect rect = new Rect();
    public Bitmap bitmap;
    public float scaleW = 1;
    public float scaleH = 1;
    public Matrix matrix = null;
    public int getWidth() {
        return rect.width();
    }

    public int getHeight() {
        return rect.height();
    }
}
