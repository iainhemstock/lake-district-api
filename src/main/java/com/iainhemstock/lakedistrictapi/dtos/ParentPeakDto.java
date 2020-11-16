package com.iainhemstock.lakedistrictapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ParentPeakDto {
    private String name;
    private String url;

    @Override
    public String toString() {
        return "ParentPeakDTO{" +
            "name='" + name + '\'' +
            ", url='" + url + '\'' +
            '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ParentPeakDto)) return false;
        ParentPeakDto that = (ParentPeakDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }
}
