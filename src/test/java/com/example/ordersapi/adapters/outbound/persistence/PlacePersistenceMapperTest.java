package com.example.ordersapi.adapters.outbound.persistence;

import com.example.ordersapi.domain.model.Place;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlacePersistenceMapperTest {

    private final PlacePersistenceMapper mapper = new PlacePersistenceMapper();

    @Test
    void toEntity_should_map_domain_to_entity() {
        Place domain = new Place();
        domain.setId(1L);
        domain.setName("Museo del Prado");
        domain.setCity("Madrid");
        domain.setCodPostal(28014);

        PlaceEntity entity = mapper.toEntity(domain);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(1L);
        assertThat(entity.getName()).isEqualTo("Museo del Prado");
        assertThat(entity.getCity()).isEqualTo("Madrid");
        assertThat(entity.getCodPostal()).isEqualTo(28014);
    }

    @Test
    void toDomain_should_map_entity_to_domain() {
        PlaceEntity entity = new PlaceEntity();
        entity.setId(2L);
        entity.setName("Gran Teatro de Cáceres");
        entity.setCity("Cáceres");
        entity.setCodPostal(10002);

        Place domain = mapper.toDomain(entity);

        assertThat(domain).isNotNull();
        assertThat(domain.getId()).isEqualTo(2L);
        assertThat(domain.getName()).isEqualTo("Gran Teatro de Cáceres");
        assertThat(domain.getCity()).isEqualTo("Cáceres");
        assertThat(domain.getCodPostal()).isEqualTo(10002);
    }

    @Test
    void toEntity_should_return_null_when_domain_is_null() {
        PlaceEntity entity = mapper.toEntity(null);
        assertThat(entity).isNull();
    }

    @Test
    void toDomain_should_return_null_when_entity_is_null() {
        Place domain = mapper.toDomain(null);
        assertThat(domain).isNull();
    }
}
