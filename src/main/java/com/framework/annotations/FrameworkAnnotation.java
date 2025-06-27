/**
 * @author Aswin Chandran PC
 * https://www.linkedin.com/in/aswin-p-c/
 * https://github.com/aswinpc143
 */
/***************************************************/
package com.framework.annotations;

import com.framework.enums.AuthorType;
import com.framework.enums.CategoryType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//This is an Custom Annotation
@Retention ( RetentionPolicy.RUNTIME)
@Target ( ElementType.METHOD)
public @interface FrameworkAnnotation
{
    // This is not a method
    public AuthorType[] author();

    // public String[] category();
    public
    CategoryType[] category();
}
