package com.siukatech.poc.react.backend.app.base.web.controller.encrypted;

import com.siukatech.poc.react.backend.app.item.business.service.ItemService;
import com.siukatech.poc.react.backend.app.item.web.controller.ItemController;
import com.siukatech.poc.react.backend.app.item.business.form.ItemForm;
import com.siukatech.poc.react.backend.parent.web.annotation.v1.EncryptedApiV1Controller;
import com.siukatech.poc.react.backend.parent.web.micrometer.CorrelationIdHandler;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This is not working
 * Those endpoints are required to re-expose.
 * <p>
 * https://stackoverflow.com/questions/60776046/when-is-the-handleemptybody-method-in-the-interface-requestbodyadvice-called
 * https://stackoverflow.com/questions/41692669/spring-requestbodyadvice-not-being-triggered
 * https://stackoverflow.com/a/52301252
 * https://www.baeldung.com/spring-http-logging
 * https://frandorado.github.io/spring/2018/11/15/log-request-response-with-body-spring.html
 *
 */
@Slf4j
@EncryptedApiV1Controller
public class EncryptedItemController extends ItemController {
    public EncryptedItemController(
//            ModelMapper modelMapper
////            , ItemRepository itemRepository
//            ,
            ItemService itemService
            , CorrelationIdHandler correlationIdHandler
    ) {
        super(
//                modelMapper
////                , itemRepository
//                ,
                itemService,
                correlationIdHandler);
    }

    @GetMapping("/items")
    public ResponseEntity<?> listItems(@RequestHeader HttpHeaders httpHeaders) {
        return super.listItems(httpHeaders);
    }

    @GetMapping("/items/{targetItemId}")
    public ResponseEntity<?> getItemById(@PathVariable(required = true) String targetItemId) {
        return super.getItemById(targetItemId);
    }

    @PostMapping(value = "/items")
    public ResponseEntity<?> createItem(@Valid @RequestBody ItemForm itemForm) {
        return super.createItem(itemForm);
    }

    @PutMapping("/items/{targetItemId}")
    public ResponseEntity<?> updateItem(@Valid @RequestBody ItemForm itemForm, @PathVariable(required = true) String targetItemId) {
        return super.updateItem(itemForm, targetItemId);
    }

    @DeleteMapping("/items/{targetItemId}")
    public HttpStatus deleteItem(@PathVariable(required = true) String targetItemId) {
        return super.deleteItem(targetItemId);
    }

}
