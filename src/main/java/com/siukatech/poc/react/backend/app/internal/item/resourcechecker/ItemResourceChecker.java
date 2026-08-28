package com.siukatech.poc.react.backend.app.internal.item.resourcechecker;

import com.siukatech.poc.react.backend.app.internal.security.constant.InternalSecurityConstants;
import com.siukatech.poc.react.backend.module.core.security.annotation.PermissionControl;
import com.siukatech.poc.react.backend.module.core.security.annotation.ResourceCheck;
import com.siukatech.poc.react.backend.module.core.security.aop.ReqVariableData;
import com.siukatech.poc.react.backend.module.core.security.resourcechecker.ResourceCheckResult;
import com.siukatech.poc.react.backend.module.core.security.resourcechecker.ResourceChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ItemResourceChecker implements ResourceChecker {

    @Override
    public String getSupportedType() {
        return InternalSecurityConstants.ResourceType.ITEM;
    }

    @Override
    public ResourceCheckResult check(ResourceCheck resourceCheck
//            , String resourceId
//            , Map<String, String> validatedResources
            , ReqVariableData reqVariableData
            , Map<String, ResourceCheckResult> validatedResources
            , PermissionControl permissionControl
            , Authentication authentication
    ) {
        boolean hasAccess = false;
        hasAccess = true;
        Map<String, String> resourceIdMap = new HashMap<>();
        ResourceCheckResult resourceCheckResult = new ResourceCheckResult(hasAccess, resourceIdMap);
        log.info("check - resourceCheck: [{}]"
//                        + ", resourceId: [{}]"
                        + ", reqVariableData: [{}]"
                        + ", validatedResources: [{}]"
                        + ", permissionControl: [{}]"
//                        + ", authentication: [{}]"
                        + ", start"
                , resourceCheck
//                , resourceId
                , reqVariableData
                , validatedResources
                , permissionControl
//                , authentication
        );
        return resourceCheckResult;
    }
}
