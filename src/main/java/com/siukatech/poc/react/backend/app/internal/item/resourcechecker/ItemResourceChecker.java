package com.siukatech.poc.react.backend.app.internal.item.resourcechecker;

import com.siukatech.poc.react.backend.app.internal.security.constant.InternalSecurityConstants;
import com.siukatech.poc.react.backend.module.core.security.annotation.PermissionControl;
import com.siukatech.poc.react.backend.module.core.security.annotation.ResourceCheck;
import com.siukatech.poc.react.backend.module.core.security.resourcechecker.ResourceChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class ItemResourceChecker implements ResourceChecker {
    @Override
    public String getSupportedType() {
        return InternalSecurityConstants.ResourceType.ITEM;
    }

    @Override
    public boolean check(ResourceCheck resourceCheck
            , String resourceId
            , Map<String, String> validatedResources
            , PermissionControl permissionControl
            , Authentication authentication) {
        boolean hasAccess = false;
        hasAccess = true;
        log.info("check - resourceCheck: [{}]"
                        + ", resourceId: [{}]"
                        + ", validatedResources: [{}]"
                        + ", permissionControl: [{}]"
//                        + ", authentication: [{}]"
                        + ", start"
                , resourceCheck, resourceId, validatedResources, permissionControl
//                , authentication
        );
        return hasAccess;
    }
}
