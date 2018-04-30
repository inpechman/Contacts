package com.sruly.stu.contacts.logic;

import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Created by stu on 4/30/2018.
 *
 */

public class BirthDate implements Comparable<BirthDate>{
    Long birthDateInMilis;

    public BirthDate(long birthDateInMilis) {
        this.birthDateInMilis = birthDateInMilis;
    }

    @Override
    public int compareTo(@NonNull BirthDate o) {
        return this.birthDateInMilis.compareTo(o.birthDateInMilis);
    }
    
    public static Comparator<BirthDate> getComparator(){
        return new Comparator<BirthDate>() {
            @Override
            public int compare(BirthDate o1, BirthDate o2) {
                return o1.compareTo(o2);
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        };
    }
}
