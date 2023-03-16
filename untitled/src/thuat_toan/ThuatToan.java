package thuat_toan;

import java.util.*;

public class ThuatToan {
    public static void main(String[] args) {
        int[] arr1 = {3, 4, 5, 7, 9, 7, 4, 3, 6, 2, 7, 9};
        int[] arr2 = {3, 4, 1, 9, 4, 8, 6, 2, 7};

        Map<Integer, Integer> map = new HashMap<>();

        List<Integer> result = new ArrayList<>();

        for (int item1 : arr1) {
            map.put(item1, 1);
        }

        for (int item2 : arr2) {
            if (map.containsKey(item2)) {
                result.add(item2);
                map.remove(item2);
            }
        }
        System.out.println("Kết quả" + result);
    }
}
