package br.com.postech.techchallengeitems.utils;

import br.com.postech.techchallengeitems.adapters.controller.rest.request.ItemEditionRequest;
import br.com.postech.techchallengeitems.adapters.controller.rest.request.ItemRegistrationRequest;
import br.com.postech.techchallengeitems.adapters.gateway.database.entity.ItemEntity;
import br.com.postech.techchallengeitems.core.domain.entity.Item;
import br.com.postech.techchallengeitems.core.domain.enums.ItemType;
import java.math.BigDecimal;

public abstract class ItemTestProvider {
  public ItemEntity getFakeItemEntity() {
    ItemEntity outPut = new ItemEntity();
    outPut.setDescription("refrigerante");
    outPut.setName("Coca Cola");
    outPut.setType(ItemType.BEVERAGE);
    outPut.setPrice(new BigDecimal("5.0"));
    outPut.setId(1);
    return outPut;
  }

  public Item getFakeInputItem() {
    Item inputItem = new Item();
    inputItem.setDescription("refrigerante");
    inputItem.setName("Coca Cola");
    inputItem.setType(ItemType.BEVERAGE);
    inputItem.setPrice(new BigDecimal("5.0"));
    return inputItem;
  }

  public Item getFakeOutputItem() {
    Item inputItem = new Item();
    inputItem.setDescription("refrigerante");
    inputItem.setName("Coca Cola");
    inputItem.setType(ItemType.BEVERAGE);
    inputItem.setPrice(new BigDecimal("5.0"));
    inputItem.setId(1);
    return inputItem;
  }

  public ItemRegistrationRequest getFakeItemRegistrationRequest() {
    ItemRegistrationRequest itemRegistrationRequest = new ItemRegistrationRequest();
    itemRegistrationRequest.setType(ItemType.BEVERAGE);
    itemRegistrationRequest.setName("Coca Cola");
    itemRegistrationRequest.setDescription("refrigerante");
    itemRegistrationRequest.setPrice(new BigDecimal("5.0"));
    return itemRegistrationRequest;
  }

  public ItemEditionRequest getFakeItemEditionRequest() {
    ItemEditionRequest itemEditionRequest = new ItemEditionRequest();
    itemEditionRequest.setDescription("soda");
    return itemEditionRequest;
  }



}
