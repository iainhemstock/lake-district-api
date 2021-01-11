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

    public static ResultPageMetaData of(final int offset, final int limit) {
        return new ResultPageMetaData(offset, limit);
    }
}
