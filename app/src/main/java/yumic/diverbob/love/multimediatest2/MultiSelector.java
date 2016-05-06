package yumic.diverbob.love.multimediatest2;

import android.util.SparseBooleanArray;

/**
 * Created by Oathkeeper on 2016/5/6.
 */
public class MultiSelector {

    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();
    private boolean mIsSelectable = false;


    private void setItemChecked(int position, boolean isChecked) {
        mSelectedPositions.put(position, isChecked);

    }
    private boolean isItemChecked(int position) {
        return mSelectedPositions.get(position);
    }

    private void setSelectable(boolean selectable) {
        mIsSelectable = selectable;
    }

    private boolean isSelectable() {
        return mIsSelectable;
    }

}
