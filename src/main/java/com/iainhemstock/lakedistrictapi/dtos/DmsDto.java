package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({"degrees", "minutes", "seconds", "hemisphere" })
public class DmsDto {
    private String degrees;
    private String minutes;
    private String seconds;
    private String hemisphere;
    private String formatted;

    public DmsDto(String degrees, String minutes, String seconds, String hemisphere) {
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
        return String.format("%s° %s' %s\" %s",
                degrees, minutes, seconds, hemisphere);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DmsDto dmsDTO = (DmsDto) o;
        if (degrees != null ? !degrees.equals(dmsDTO.degrees) : dmsDTO.degrees != null) return false;
        if (minutes != null ? !minutes.equals(dmsDTO.minutes) : dmsDTO.minutes != null) return false;
        if (seconds != null ? !seconds.equals(dmsDTO.seconds) : dmsDTO.seconds != null) return false;
        if (hemisphere != null ? !hemisphere.equals(dmsDTO.hemisphere) : dmsDTO.hemisphere != null) return false;
        return formatted != null ? formatted.equals(dmsDTO.formatted) : dmsDTO.formatted == null;
    }

    @Override
    public int hashCode() {
        int result = degrees != null ? degrees.hashCode() : 0;
        result = 31 * result + (minutes != null ? minutes.hashCode() : 0);
        result = 31 * result + (seconds != null ? seconds.hashCode() : 0);
        result = 31 * result + (hemisphere != null ? hemisphere.hashCode() : 0);
        result = 31 * result + (formatted != null ? formatted.hashCode() : 0);
        return result;
    }
}
