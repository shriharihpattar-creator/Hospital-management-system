package com.hospitalsystem.util;

import com.hospitalsystem.model.Doctor;
import java.util.List;

/**
 * Selection sort algorithm to order doctors by fee ascending.
 */
public class DoctorSorter {

    public static void sortByFeeAscending(List<Doctor> list) {
        if (list == null || list.size() < 2) {
            return;
        }

        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int lowestIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j).getFee() < list.get(lowestIdx).getFee()) {
                    lowestIdx = j;
                }
            }
            if (lowestIdx != i) {
                Doctor swapTemp = list.get(i);
                list.set(i, list.get(lowestIdx));
                list.set(lowestIdx, swapTemp);
            }
        }
    }
}
