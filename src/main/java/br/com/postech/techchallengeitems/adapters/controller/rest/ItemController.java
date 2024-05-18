package br.com.postech.techchallengeitems.adapters.controller.rest;


import br.com.postech.techchallengeitems.adapters.controller.rest.request.ItemEditionRequest;
import br.com.postech.techchallengeitems.adapters.controller.rest.request.ItemRegistrationRequest;
import br.com.postech.techchallengeitems.adapters.controller.rest.response.ItemSearchByTypeResponse;
import br.com.postech.techchallengeitems.core.domain.entity.Item;
import br.com.postech.techchallengeitems.core.domain.enums.ItemType;
import br.com.postech.techchallengeitems.core.usecase.DeleteItemUseCase;
import br.com.postech.techchallengeitems.core.usecase.EditItemUseCase;
import br.com.postech.techchallengeitems.core.usecase.RegisterItemUseCase;
import br.com.postech.techchallengeitems.core.usecase.SearchItemByIdsUseCase;
import br.com.postech.techchallengeitems.core.usecase.SearchItemByTypeUseCase;
import jakarta.validation.Valid;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/techchallenge/items")
public class ItemController {

  private final RegisterItemUseCase registerItemUseCase;
  private final EditItemUseCase editItemUseCase;
  private final SearchItemByTypeUseCase searchItemByTypeUseCase;
  private final SearchItemByIdsUseCase searchItemByIdsUseCase;
  private final DeleteItemUseCase deleteItemUseCase;
  private final ModelMapper modelMapper;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ItemSearchByTypeResponse itemRegistration(@Valid @RequestBody final ItemRegistrationRequest itemRegistrationRequest) {
    log.info("Item registration request: {} received", itemRegistrationRequest);
    var item = modelMapper.map(itemRegistrationRequest, Item.class);
    return modelMapper.map(registerItemUseCase.execute(item), ItemSearchByTypeResponse.class);
  }

  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ItemSearchByTypeResponse itemEdition(@Valid @RequestBody final ItemEditionRequest itemEditionRequest, @PathVariable final Integer id) {
    log.info("Item edition request: {} received", itemEditionRequest);
    var item = modelMapper.map(itemEditionRequest, Item.class);
    var savedItem = editItemUseCase.execute(id, item);
    return modelMapper.map(savedItem, ItemSearchByTypeResponse.class);
  }

  @GetMapping(params = "type")
  @ResponseStatus(HttpStatus.OK)
  public List<ItemSearchByTypeResponse> itemSearchByType(@NotNull @RequestParam final ItemType type) {
    log.info("Item search request: {} received", type);
    var items = searchItemByTypeUseCase.execute(type);
    return items.stream()
        .map(item -> modelMapper.map(item, ItemSearchByTypeResponse.class))
        .toList();
  }
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ItemSearchByTypeResponse> itemSearchByIds(@RequestParam List<Integer> ids) {
    log.info("Item search by ids: {} request received", ids);
    var items = searchItemByIdsUseCase.execute(ids);
    return items.stream()
        .map(item -> modelMapper.map(item, ItemSearchByTypeResponse.class))
        .toList();
  }



  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void itemDelete(@NotNull @PathVariable final Integer id) {
    log.info("Item delete request: {} received", id);
    deleteItemUseCase.execute(id);
  }
}
