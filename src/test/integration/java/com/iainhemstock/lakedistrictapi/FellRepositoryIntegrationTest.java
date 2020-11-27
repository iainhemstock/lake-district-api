package com.iainhemstock.lakedistrictapi;

import com.iainhemstock.lakedistrictapi.entities.fells.ScafellPikeFell;
import com.iainhemstock.lakedistrictapi.repositories.FellRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FellRepositoryIntegrationTest {

    @Autowired private FellRepository fellRepository;

    @Test
    public void size_is_one() {
        assertEquals(new ScafellPikeFell(), fellRepository.findByNameLikeIgnoreCase("Scafell Pike"));
    }

}
