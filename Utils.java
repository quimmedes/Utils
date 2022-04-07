package com.kimede.giganimaonline.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import com.facebook.common.util.ByteConstants;
import com.kimede.giganimaonline.GoogleAnalyticsApp;
import java.io.InputStream;
import java.io.OutputStream;

public class Utils {
    public static final String BOLD_FONT_PATH = "fonts/Montserrat-Bold.ttf";
    public static final String REGULAR_FONT_PATH = "fonts/Montserrat-Regular.ttf";
    public static Typeface boldFont;
    public static Typeface regularFont;

    public static void CopyStream(InputStream inputStream, OutputStream outputStream) {
        try {
            byte[] bArr = new byte[ByteConstants.KB];
            while (true) {
                int read = inputStream.read(bArr, 0, ByteConstants.KB);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                } else {
                    return;
                }
            }
        } catch (Exception e) {
        }
    }

    public static Bitmap GetImageFromAssets(Context context, String str) {
        try {
            return BitmapFactory.decodeStream(context.getAssets().open(str));
        } catch (Exception e) {
            Log.d("Application Find", e.getMessage());
            return null;
        }
    }

    public static void loadFonts() {
        regularFont = Typeface.createFromAsset(GoogleAnalyticsApp.getContext().getAssets(), REGULAR_FONT_PATH);
        boldFont = Typeface.createFromAsset(GoogleAnalyticsApp.getContext().getAssets(), BOLD_FONT_PATH);
    }

    public static void setFontAllView(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                setFontAllView((ViewGroup) childAt);
            } else if (childAt != null) {
                Typeface typeface = (childAt.getTag() == null || !childAt.getTag().toString().toLowerCase().equals("bold")) ? regularFont : boldFont;
                if (childAt instanceof TextView) {
                    ((TextView) childAt).setTypeface(typeface);
                } else if (childAt instanceof EditText) {
                    ((EditText) childAt).setTypeface(typeface);
                } else if (childAt instanceof RadioButton) {
                    ((RadioButton) childAt).setTypeface(typeface);
                } else if (childAt instanceof CheckBox) {
                    ((CheckBox) childAt).setTypeface(typeface);
                }
            }
        }
    }
}
