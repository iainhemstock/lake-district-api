package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({"degrees", "minutes", "seconds", "hemisphere" })
public class DmsDTO {
    private int degrees;
    private int minutes;
    private int seconds;
    private String hemisphere;
    private String formatted;

    public DmsDTO(int degrees, int minutes, int seconds, String hemisphere) {
        this.degrees = degrees;
        this.minutes = minutes;
        this.seconds = seconds;
        this.hemisphere = hemisphere;
    }

    public String getFormatted() {
        formatted = toString();
        return formatted;
    }

    @Override
    public String toString() {
        return String.format("%d° %d' %d\" %s",
                degrees, minutes, seconds, hemisphere);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DmsDTO dms = (DmsDTO) o;

        if (degrees != dms.degrees) return false;
        if (minutes != dms.minutes) return false;
        if (seconds != dms.seconds) return false;
        return Objects.equals(hemisphere, dms.hemisphere);
    }

    @Override
    public int hashCode() {
        int result = degrees;
        result = 31 * result + minutes;
        result = 31 * result + seconds;
        result = 31 * result + (hemisphere != null ? hemisphere.hashCode() : 0);
        return result;
    }
}
