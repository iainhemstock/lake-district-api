package com.iainhemstock.lakedistrictapi.repository_interfaces;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ResultPageMetaData {
    private int offset;
    private int limit;

    public static ResultPageMetaData empty() {
        return new ResultPageMetaData(-1, -1);
    }

    public static ResultPageMetaData of(final int offset, final int limit) {
        return new ResultPageMetaData(offset, limit);
    }

    public boolean isEmpty() {
        return offset == -1 && limit == -1;
    }
}
