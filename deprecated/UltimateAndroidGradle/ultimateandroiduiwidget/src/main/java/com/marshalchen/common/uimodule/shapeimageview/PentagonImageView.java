package com.marshalchen.common.uimodule.shapeimageview;

import android.content.Context;
import android.util.AttributeSet;

import com.marshalchen.common.uimodule.widgets.R;
import com.marshalchen.common.uimodule.shapeimageview.shader.ShaderHelper;
import com.marshalchen.common.uimodule.shapeimageview.shader.SvgShader;

public class PentagonImageView extends ShaderImageView {

    public PentagonImageView(Context context) {
        super(context);
    }

    public PentagonImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PentagonImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public ShaderHelper createImageViewHelper() {
        return new SvgShader(R.raw.shape_imageview_imgview_pentagon);
    }
}
