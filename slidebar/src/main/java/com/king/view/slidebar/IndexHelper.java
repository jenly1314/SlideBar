package com.king.view.slidebar;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1.要索引联动的实体实现IIndexKey接口
 * 2.在Adapter中数据更新后调用refreshMap方法
 * 3.在setOnTouchLetterChangeListener调用getPositionByLetter方法获取position
 * 4.调用LayoutManage的scrollToPositionWithOffset方法置顶选中的字母对应的item的首个
 */
public class IndexHelper {

    private final Map<String, Integer> mapLetter;

    public IndexHelper() {
        mapLetter = new HashMap<>();
    }

    /**
     * 如果数据源未进行过排序，则这里必须写排序的实现
     *
     * @param list
     * @param data
     * @param <T>
     */
    public <T> void refreshMap(List<T> list, Comparator<? super T> data) {
        if (data != null) {
            Collections.sort(list, data);
        }
        mapLetter.clear();
        if (list != null) {
            /*
            倒序填充map
            C   10
            C   9
            C   8
            那么map中存放的就是首次出现某个字母的索引
             */
            for (int i = list.size() - 1; i >= 0; i--) {
                T c = list.get(i);
                if (c instanceof IIndexKey) {
                    IIndexKey indexKey = (IIndexKey) c;
                    mapLetter.put(indexKey.getIndexKey(), i);
                } else {
                    throw new IllegalArgumentException("所传参数必须实现IIndexKey接口");
                }
            }
        }
    }

    /**
     * 获取滑动到的字母的首个item的索引
     *
     * @param letter
     * @return
     */
    public int getPositionByLetter(String letter) {
        Integer integer = mapLetter.get(letter);
        if (integer == null) {
            return -1;
        }
        return integer;
    }

    public interface IIndexKey {
        String getIndexKey();
    }
}
