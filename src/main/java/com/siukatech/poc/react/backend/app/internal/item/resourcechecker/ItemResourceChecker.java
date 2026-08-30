package com.siukatech.poc.react.backend.app.internal.item.resourcechecker;

import com.siukatech.poc.react.backend.app.internal.item.business.form.ItemForm;
import com.siukatech.poc.react.backend.module.core.security.resourcechecker.ResourceCheckResult;
import com.siukatech.poc.react.backend.module.core.security.resourcechecker.ResourceChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class ItemResourceChecker implements ResourceChecker {

    public ResourceCheckResult getItemById(String targetItemId) {
        log.info("getItemById - targetItemId: [{}]", targetItemId);
        ResourceCheckResult resourceCheckResult = new ResourceCheckResult(true);
        resourceCheckResult.setHasAccess(false);
        resourceCheckResult.getOutputMap().putAll(Map.of("targetItemId", targetItemId))
        ;
        return resourceCheckResult;
    }
    public ResourceCheckResult updateItem(ItemForm itemForm, String targetItemId) {
        log.info("updateItem - targetItemId: [{}], itemForm: [{}]", targetItemId, itemForm);
        ResourceCheckResult resourceCheckResult = new ResourceCheckResult(true);
        resourceCheckResult.getOutputMap().putAll(Map.of("targetItemId", targetItemId))
        ;
        return resourceCheckResult;
    }
    public ResourceCheckResult deleteItem(String targetItemId) {
        log.info("deleteItem - targetItemId: [{}]", targetItemId);
        ResourceCheckResult resourceCheckResult = new ResourceCheckResult(true);
        resourceCheckResult.getOutputMap().putAll(Map.of("targetItemId", targetItemId))
        ;
        return resourceCheckResult;
    }
}
