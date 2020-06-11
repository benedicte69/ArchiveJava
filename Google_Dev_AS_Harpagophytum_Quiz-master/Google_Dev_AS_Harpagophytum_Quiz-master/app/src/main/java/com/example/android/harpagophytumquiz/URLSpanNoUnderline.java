package com.example.android.harpagophytumquiz;

import android.text.TextPaint;
import android.text.style.URLSpan;

/**
 * disables the automatic links'underlining
 * Created on 11/01/2018.
 */

public class URLSpanNoUnderline extends URLSpan {
    public URLSpanNoUnderline(String p_Url) {
        super(p_Url);
    }

    public void updateDrawState(TextPaint p_DrawState) {
        super.updateDrawState(p_DrawState);
        p_DrawState.setUnderlineText(false);
    }
}

