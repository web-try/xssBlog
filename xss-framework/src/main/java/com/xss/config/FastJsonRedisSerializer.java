package com.xss.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.util.Assert;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Redis使用FastJson序列化
 * 
 * @author sg
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T>
{
    // 默认字符集
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    // 序列化对象类型
    private Class<T> clazz;

    // 静态代码块，设置全局的FastJson配置，开启自动类型支持
    static
    {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    // 构造函数，传入序列化对象类型
    public FastJsonRedisSerializer(Class<T> clazz)
    {
        super();
        this.clazz = clazz;
    }

    // 序列化方法，将对象转换为字节数组
    @Override
    public byte[] serialize(T t) throws SerializationException
    {
        // 如果对象为空，返回空字节数组
        if (t == null)
        {
            return new byte[0];
        }
        // 使用FastJson将对象转换为字符串，并设置序列化特性，将类名写入字符串
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    // 反序列化方法，将字节数组转换为对象
    @Override
    public T deserialize(byte[] bytes) throws SerializationException
    {
        // 如果字节数组为空或长度为0，返回null
        if (bytes == null || bytes.length <= 0)
        {
            return null;
        }
        // 将字节数组转换为字符串
        String str = new String(bytes, DEFAULT_CHARSET);

        // 使用FastJson将字符串转换为对象
        return JSON.parseObject(str, clazz);
    }

    // 获取Java类型
    protected JavaType getJavaType(Class<?> clazz)
    {
        // 使用TypeFactory创建Java类型
        return TypeFactory.defaultInstance().constructType(clazz);
    }
}