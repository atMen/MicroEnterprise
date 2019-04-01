package com.spring.chanba.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.DisplayMetrics;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Utils {
    /**
     * 判断String是否为空
     *
     * @param data
     * @return
     */
    public static boolean isStringEmpty(String data) {
        return data == null || "".equals(data);
    }

    /**
     * 判断List是否为空
     *
     * @param list
     * @return
     */
    public static boolean isStringEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static String ignoreEmoji(String source) {
        return source.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", "");
    }

    /**
     * 格式化HTML
     *
     * @param strHtml
     * @return
     */
    public static String transHtmltext(String strHtml) {
        try {
            Document doc = Jsoup.parse(strHtml);
            Elements elements = doc.getElementsByTag("img");
            for (Element element : elements) {
                element.attr("width", "100%").attr("height", "auto");
            }
            return doc.toString();
        } catch (Exception e) {
            return strHtml;
        }
    }

    /**
     * bitmap转base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取屏幕宽度按
     *
     * @param cxt
     * @return
     */
    public static int getWidth(Context cxt) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) cxt).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param cxt
     * @return
     */
    public static int getHeight(Context cxt) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) cxt).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
}
