package com.wyb.shiro.config.freemarker;


import com.wyb.shiro.config.freemarker.tag.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * @author Kunzite
 */
public class ShiroTagFreeMarkerConfigurer extends FreeMarkerConfigurer {

    @Override  
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();  
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
    }
}