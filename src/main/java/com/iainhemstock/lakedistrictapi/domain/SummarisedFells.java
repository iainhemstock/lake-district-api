package com.iainhemstock.lakedistrictapi.domain;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ToString
public class SummarisedFells {
    private final List<SummarisedFell> fells = new ArrayList<>();

    public SummarisedFells(final SummarisedFell fell) {
        add(fell);
    }

    public void add(final SummarisedFell fell) {
        this.fells.add(fell);
    }

    @Override
    public boolean equals(final Object other) {
        List<SummarisedFell> otherList = ((SummarisedFells) other).fells;
        return fells.containsAll(otherList) & otherList.containsAll(fells);
    }
}
