package com.siukatech.poc.react.backend.app.internal.security.constant;

import com.siukatech.poc.react.backend.module.core.security.constant.CoreSecurityConstants;

public interface InternalSecurityConstants extends CoreSecurityConstants {

    interface ResourceType extends CoreSecurityConstants.ResourceType {
        String I18N = "I18N";
        String ATTACHMENT = "ATTACHMENT";
        String ITEM = "ITEM";
        String MERCHANT = "MERCHANT";
        String FIGURE = "FIGURE";
    }

    interface AccessRight extends CoreSecurityConstants.AccessRight {
        String CREATE = "create";
        String UPDATE = "update";
        String DELETE = "delete";
    }

}
