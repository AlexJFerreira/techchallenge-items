package br.com.postech.techchallengeitems.core.usecase;

import br.com.postech.techchallengeitems.core.domain.entity.Item;
import br.com.postech.techchallengeitems.core.domain.enums.ItemType;
import java.util.List;
import javax.validation.constraints.NotNull;

public interface SearchItemUseCase {
    List<Item> execute(@NotNull ItemType type);
}
