package com.wyb.shiro.config.annotation;

import com.wyb.shiro.config.ShiroWebMvcConfigurerAdapter;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.annotation.*;


/**
 * Annotation to automatically register the following beans for usage with Spring MVC.
 * <ul>
 * <li>
 * {@link com.wyb.shiro.config.annotation.SessionUser}.
 * </li>
 * </ul>
 * @author Kunzite
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Inherited
@Import({ EnableShiroWebSupport.ShiroWebMvcConfigurerAdapterImportSelector.class })
public @interface EnableShiroWebSupport {

    static class ShiroWebMvcConfigurerAdapterImportSelector implements ImportSelector {

        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            return new String[] { ShiroWebMvcConfigurerAdapter.class.getName() };
        }

    }
}
