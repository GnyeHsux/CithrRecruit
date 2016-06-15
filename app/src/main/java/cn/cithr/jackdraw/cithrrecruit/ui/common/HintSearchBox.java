package cn.cithr.jackdraw.cithrrecruit.ui.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.quinny898.library.persistentsearch.SearchBox;

/**
 * Created by xusha on 2016/6/15.
 */
public class HintSearchBox extends SearchBox {
    public HintSearchBox(Context context) {
        super(context);
    }

    public HintSearchBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HintSearchBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setLogoText(String text) {
        super.setLogoText(text);
        View v = this.getRootView();
        EditText search = (EditText) this.findViewById(com.quinny898.library.persistentsearch.R.id.search);
        search.setHint(text);
    }
}
