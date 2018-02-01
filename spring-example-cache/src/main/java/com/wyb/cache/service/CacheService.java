package com.wyb.cache.service;

/**
 * Description:
 *
 * @author: Kunzite
 * @version: 2018-02-01 15:28
 */
public interface CacheService {

    public String setValue(String key,String value);

    public String getValueByKey(String key);

    public boolean delValueBykey(String key);
}
