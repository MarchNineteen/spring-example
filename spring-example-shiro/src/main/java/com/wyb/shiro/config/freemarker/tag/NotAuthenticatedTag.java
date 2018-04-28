package com.wyb.shiro.config.freemarker.tag;

import freemarker.core.Environment;
import freemarker.log.Logger;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

/**
 * Freemarker tag that renders the tag body only if the current user has
 * <em>not</em> executed a successful authentication attempt
 * <em>during their current session</em>.
 *
 * <p>
 * The logically opposite tag of this one is the
 * {@link org.apache.shiro.web.tags.AuthenticatedTag}.
 *
 * <p>
 * Equivalent to {@link org.apache.shiro.web.tags.NotAuthenticatedTag}
 * </p>
 */
@Slf4j
public class NotAuthenticatedTag extends SecureTag {

	@Override
	public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
		if (getSubject() == null || !getSubject().isAuthenticated()) {
			log.debug("Subject does not exist or is not authenticated.  Tag body will be evaluated.");
			renderBody(env, body);
		} else {
			log.debug("Subject exists and is authenticated.  Tag body will not be evaluated.");
		}
	}
}