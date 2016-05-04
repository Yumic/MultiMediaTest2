package yumic.diverbob.love.multimediatest2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oathkeeper on 2016/5/4.
 */
public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> groupList;
    private List item[] = new ArrayList[3];
    private List<List<String>> itemList;

    /**
     * 初始化适配器
     * @param context
     */
    public MyExpandableListViewAdapter(Context context)
    {
        this.context = context;
        groupList = new ArrayList<String>();
        groupList.add("视频");
        groupList.add("音频");
        groupList.add("图片");



        item[0] = new ArrayList<String>();
        item[0].add("所有视频");

        item[1] = new ArrayList<String>();
        item[1].add("所有音频");

        item[2] = new ArrayList<String>();
        item[2].add("所有图片");

        itemList = new ArrayList<List<String>>();
        itemList.add(item[0]);
        itemList.add(item[0]);
        itemList.add(item[0]);


    }

    /**
     * 获取组的个数
     * @return
     */
    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    /**
     * 获取指定组中的子元素个数
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return itemList.get(groupPosition).size();
    }

    /**
     * 获取指定组中的数据
     * @param groupPosition
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        return itemList.get(groupPosition);
    }


    /**
     * 获取指定组中的指定子元素数据
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return itemList.get(groupPosition).get(childPosition);
    }

    /**
     * 获取指定组的id，这个组id必须是唯一的
     * @param groupPosition
     * @return
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * 获取指定组中的指定子元素id
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * 组和子元素是否持有稳定的ID，也就是底层数据的改变不会影响到它们
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * 获取显示指定组的View
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
       GroupHolder groupHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.expendlist_group,null);
            groupHolder = new GroupHolder();
            groupHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(groupHolder);

        }else{
            groupHolder = (GroupHolder) convertView.getTag();
        }
        groupHolder.textView.setText(groupList.get(groupPosition));


        return convertView;
    }

    /**
     * 获得一个View对象，显示指定组中的指定子元素数据
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemHolder itemHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.expendlist_item,null);
            itemHolder = new ItemHolder();
            itemHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(itemHolder);
        }else{
            itemHolder = (ItemHolder) convertView.getTag();
        }

        itemHolder.textView.setText(itemList.get(groupPosition).get(childPosition));

        return convertView;
    }

    /**
     * 是否选中指定位置上的子元素
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder
    {
        public TextView textView;

    }

    class ItemHolder
    {
        public TextView textView;
    }
}
