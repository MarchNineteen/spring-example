package com.wyb.cache.factory;

/**
 * Description:
 *
 * @author: Kunzite
 * @version: 2018-02-01 15:28
 */
public interface Cache {

    public String setValue(String key,String value);

    public String getValueByKey(String key);

    public boolean delValueBykey(String key);
}
