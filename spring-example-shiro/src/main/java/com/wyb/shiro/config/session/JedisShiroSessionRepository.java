package com.wyb.shiro.config.session;

import com.wyb.shiro.config.redis.RedisUtil;
import com.wyb.shiro.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Kunzite
 */
@Slf4j
public class JedisShiroSessionRepository implements ShiroSessionRepository {

	private RedisTemplate<String, Object> objectRedisTemplate;

	@Override
	public void saveSession(Session session) {
		if (session == null || session.getId() == null) {
			log.error("session或者session id为空");
			return;
		}
		// 因为CustomSessionListener中的超时事件,必须在redis中的session存在时才会触发,
		// 所以为保证该事件的触发,需要确保在本地中的session超时的时候,redis中的session还依然存在,
		// 此处X2是为了保证该session在自动回话管理中能够删除
		String session_key = getRedisSessionKey(session.getId());
		long timeOut = session.getTimeout() / 1000 * 2;
		RedisUtil.set(objectRedisTemplate, session_key, session, timeOut);
	}

	@Override
	public void deleteSession(Serializable sessionId) {
		log.debug("删除session:" + sessionId);
		if (sessionId == null) {
			log.error("id为空");
			return;
		}
		String session_key = getRedisSessionKey(sessionId);
		RedisUtil.del(objectRedisTemplate, session_key);
		//TODO 更新数据库在线状态
	}

	@Override
	public Session getSession(Serializable sessionId) {
		// log.debug("取得session:" + sessionId);
		if (sessionId == null) {
			log.error("id为空");
			return null;
		}
		String session_key = getRedisSessionKey(sessionId);
		// if(!RedisUtil.hasKey(objectRedisTemplate, session_key)){
		// return null;
		// }
		Session session = (SimpleSession) RedisUtil.get(objectRedisTemplate, session_key);

		return session;
	}

	@Override
	public Collection<Session> getAllSessions() {
		log.debug("取得所有session");
		Set<Session> sessions = new HashSet<Session>();
		String session_ids = Constants.REDIS_SHIRO_SESSION + "*";
		Set<String> keys = objectRedisTemplate.keys(session_ids);
		List<Object> objs = objectRedisTemplate.opsForValue().multiGet(keys);
		for (Object obj : objs) {
			Session s = (Session) obj;
			sessions.add(s);
		}
		return sessions;
	}

	/**
	 * 获取redis中的session key
	 *
	 * @param sessionId
	 * @return
	 */
	private String getRedisSessionKey(Serializable sessionId) {
		return Constants.REDIS_SHIRO_SESSION + sessionId;
	}

	public RedisTemplate<String, Object> getObjectRedisTemplate() {
		return objectRedisTemplate;
	}

	public void setObjectRedisTemplate(RedisTemplate<String, Object> objectRedisTemplate) {
		this.objectRedisTemplate = objectRedisTemplate;
	}

}
