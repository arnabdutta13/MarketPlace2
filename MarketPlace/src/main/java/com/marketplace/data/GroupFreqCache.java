package com.marketplace.data;

import com.marketplace.entity.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupFreqCache {

    private static GroupFreqCache cache = new GroupFreqCache();

    private GroupFreqCache() {}

    public static GroupFreqCache getInstance() {
        return cache;
    }

    List<Category> list = Collections.synchronizedList(new ArrayList<>());

    private static final int SIZE = 5;

    public void addToCache(String category) {
        if (list.isEmpty()) {
            list.add(new Category(category, 1));
            return;
        }
        boolean found = false;
        for( Category c : list) {
            if (category.equals(c.getName())) {
                c.setCount(c.getCount() + 1);
                found = true;
                break;
            }
        }
        if (!found) {

            list.add(new Category(category, 1));
        }
        Collections.sort(list);
    }

    public void removeFromCache(String category) {
        if (list.isEmpty()) {
            return;
        }
        for( Category c : list) {
            if (category.equals(c.getName())) {
                c.setCount(c.getCount() - 1);
                if (c.getCount() == 0) {
                    list.remove(c);
                }
                break;
            }
        }
        Collections.sort(list);
    }

    public String getTop(int num) {
        StringBuffer sb = new StringBuffer();
        while (num > 0) {
            sb.append(list.get(list.size() - num).toString());
            num--;
        }
        return sb.toString();
    }

}
