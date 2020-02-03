package utils;

import java.util.List;

/**
 * 操作集合常用方法
 */
public class CollectionUtil {
    /**
     * 打印出集合的值
     */

    public static String listToString(List<String> list){
        if(list == null){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        int size = list.size();
        for(int index = 0; index < size; index++){
            if(index == (size - 1)){
                stringBuilder.append(list.get(index));
                break;
            }
            stringBuilder.append(list.get(index)).append(",");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
