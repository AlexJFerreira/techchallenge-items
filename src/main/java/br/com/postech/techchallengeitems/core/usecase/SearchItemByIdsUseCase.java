package br.com.postech.techchallengeitems.core.usecase;

import br.com.postech.techchallengeitems.core.domain.entity.Item;
import java.util.List;
import javax.validation.constraints.NotNull;

public interface SearchItemByIdsUseCase {
  List<Item> execute(@NotNull List<Integer> itemIds);

}
