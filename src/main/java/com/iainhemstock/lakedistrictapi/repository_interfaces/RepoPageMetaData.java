package com.iainhemstock.lakedistrictapi.repository_interfaces;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RepoPageMetaData {
    private int offset;
    private int limit;

    public static RepoPageMetaData of(final int offset, final int limit) {
        return new RepoPageMetaData(offset, limit);
    }
}
