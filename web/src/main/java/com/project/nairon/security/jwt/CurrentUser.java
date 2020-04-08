/**
 * 
 */
package com.project.nairon.security.jwt;


import java.lang.annotation.*;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

/**
 * @author gbemisola
 *
 */
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {

}
