package com.iainhemstock.lakedistrictapi.serviceinterfaces;

import com.iainhemstock.lakedistrictapi.domain.DetailedFell;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;

public interface DetailedFellAssembler {
    DetailedFell toDetailedFell(FellEntity fellEntity);
}
