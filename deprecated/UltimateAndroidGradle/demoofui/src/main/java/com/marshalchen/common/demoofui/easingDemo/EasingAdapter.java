package com.marshalchen.common.demoofui.easingDemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.marshalchen.common.demoofui.R;
import com.marshalchen.common.uimodule.easing.BaseEasingMethod;
import com.marshalchen.common.uimodule.easing.Skill;


public class EasingAdapter extends BaseAdapter {

    private Context mContext;

    public EasingAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return Skill.values().length;
    }

    @Override
    public Object getItem(int i) {
        return Skill.values()[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Object o = getItem(i);
        BaseEasingMethod b = ((Skill) o).getMethod(1000);
        int start = ((Object)b).getClass().getName().lastIndexOf(".") + 1;
        String name = ((Object)b).getClass().getName().substring(start);
        View v = LayoutInflater.from(mContext).inflate(R.layout.easing_item, null);
        TextView tv = (TextView) v.findViewById(R.id.list_item_text);
        tv.setText(name);
        v.setTag(o);
        return v;
    }
}
