package easytests.api.v1.controllers;

import easytests.auth.services.AccessControlLayerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author malinink
 */
@SuppressWarnings("checkstyle:MultipleStringLiterals")
public class AbstractController {

    @Autowired
    protected AccessControlLayerServiceInterface acl;
}
