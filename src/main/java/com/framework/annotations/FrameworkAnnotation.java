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

/**
 * A custom annotation for annotating test methods within the framework.
 * <p>
 * This annotation allows you to associate metadata, such as authors and categories,
 * directly with a test method. This information can then be used for reporting or
 * for filtering tests during execution.
 * <p>
 * Example:
 * <pre>
 * {@code
 * @FrameworkAnnotation(author = {AuthorType.ASWIN}, category = {CategoryType.SMOKE, CategoryType.REGRESSION})
 * @Test
 * public void yourTestMethod() {
 *     // Test implementation
 * }
 * }
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME) // Ensures the annotation is available at runtime for processing.
@Target(ElementType.METHOD) // Specifies that this annotation can only be applied to methods.
public @interface FrameworkAnnotation {

    /**
     * Defines the author or authors of the test method.
     * This is a mandatory element of the annotation.
     *
     * @return An array of {@link AuthorType}.
     */
    public AuthorType[] author();

    /**
     * Defines the category or categories the test method belongs to (e.g., SMOKE, REGRESSION).
     * This is a mandatory element of the annotation.
     *
     * @return An array of {@link CategoryType}.
     */
    public CategoryType[] category();
}