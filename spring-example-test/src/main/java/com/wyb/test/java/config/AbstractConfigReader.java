package com.wyb.test.java.config;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.wyb.test.java.common.BaseEntity;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * https://juejin.cn/post/6977203482931494920?utm_source=gold_browser_extension#heading-1
 * 从远端拉去配置
 * 解析为bean
 */
public abstract class AbstractConfigReader {
    public static final Logger logger = Logger.getLogger("AbstractConfigReader");

    /**
     * 配置项的超时时间：1min
     */
    private final static int EXPIRE_MILLS = 1 * 60 * 1000;

//    @Autowired
//    private
    private Map<Long, ConfigValueParser<?>> configParserMap = Maps.newConcurrentMap();

    /**
     * 缓存
     */
    private LoadingCache<Long, Object> configCache = CacheBuilder.newBuilder()
            .expireAfterWrite(EXPIRE_MILLS, TimeUnit.MILLISECONDS)
            .build(
                    new CacheLoader<Long, Object>() {
                        @Override
                        public Object load(Long key) {
                            final String value = readFromServerRemote(key);
                            return configParserMap.computeIfAbsent(key, k -> getConfigParser(k)).parse(value);
                        }

                        @Override
                        public ListenableFuture<Object> reload(Long key, Object oldValue) throws Exception {
                            Preconditions.checkNotNull(key);
                            Preconditions.checkNotNull(oldValue);
                            return Futures.immediateFuture(this.load(key));
                        }
                    }
            );


    /**
     * 配置项解析映射接口，实现String类型的配置项解析为指定类型
     *
     * @param <R>解析后的数据guava类型
     */
    public interface ConfigValueParser<R> {

        /**
         * 实现类型解析逻辑
         *
         * @param configValue 配置项的String类型数据
         * @return 对应的类型
         */
        R parse(String configValue);
    }

    /**
     * 从远端拉去或解析异常
     */
    public static class ReadConfigFailException extends Exception {
        private static final long serialVersionUID = -7481050665161948679L;

        public ReadConfigFailException(Throwable cause) {
            super(cause);
        }
    }

    // 读取配置项接口，传入配置的key获取其对于的解析后类型
    @SuppressWarnings("unchecked")
    public <R> R get(Long key) throws ReadConfigFailException {
        try {
            return (R) configCache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
            logger.info("get config error" + e);
            throw new ReadConfigFailException(e);
        }
    }

    private String readFromServerRemote(Long key) {
        BaseEntity entity = new BaseEntity();
        entity.setId(1L);
        entity.setCreateTime(new Date());
        return JSONObject.toJSONString(entity);
    }

    /**
     * 实现该抽象方法做每个key读取到的String类型配置项如何映射到相对应的数据类型
     *
     * @param key 配置项的ket
     * @return 该key对应的解析映射方式
     */
    protected abstract ConfigValueParser<?> getConfigParser(Long key);
}


