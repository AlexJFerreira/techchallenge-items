package br.com.postech.techchallengeitems.adapters.gateway.database.repository;

import br.com.postech.techchallengeitems.adapters.gateway.database.entity.ItemEntity;
import br.com.postech.techchallengeitems.core.domain.enums.ItemType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
    List<ItemEntity> findByType(ItemType type);
}
