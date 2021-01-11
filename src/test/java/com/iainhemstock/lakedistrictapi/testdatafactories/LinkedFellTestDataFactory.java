package com.iainhemstock.lakedistrictapi.testdatafactories;

import com.iainhemstock.lakedistrictapi.config.TestApiConfiguration;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkedFell;

public class LinkedFellTestDataFactory {

    public static LinkedFell helvellynLinkedFell() {
        return new LinkedFell(FellTestDataFactory.helvellynFell(), new TestApiConfiguration().apiProperties().getBaseUrl());
    }

}
