package com.finup.utils;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

/**
 * Created by zhouwentong on 2016/8/21.
 * Json 工具类
 */
public final class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    /** 格式化时间的string */
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private JsonUtils(){}
    /**
     * jackjson把json字符串转换为Java对象的实现方法
     *
     * <pre>
     * return JackJson.fromJsonToObject(this.answersJson, new TypeReference&lt;List&lt;StanzaAnswer&gt;&gt;() {
     * });
     * </pre>
     *
     * @param <T>
     *            转换为的java对象
     * @param json
     *            json字符串
     * @param typeReference
     *            jackjson自定义的类型
     * @return 返回Java对象
     */
    public static <T> T fromJsonToObject(String json, TypeReference<T> typeReference) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, typeReference);
        } catch (JsonParseException e) {
            logger.error("JsonParseException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }

    /**jackjson把json字符串转换为Java对象的实现方法
     * @author xiaobowen
     * @param json
     * @param typeReference
     * @param df
     * @return
     */
    public static <T> T fromJsonToObject(String json, TypeReference<T> typeReference , DateFormat df) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.setDateFormat(df);
            return mapper.readValue(json, typeReference);
        } catch (JsonParseException e) {
            logger.error("JsonParseException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }

    /**
     * jackjson把json字符串转换为Java对象的实现方法，当存在无法识别的属性，不报错，直接成功。
     * @param json
     * @param typeReference
     * @param df
     * @param <T>
     * @return
     */
    public static <T> T fromJsonToObjectUnknownProperties(String json, TypeReference<T> typeReference , DateFormat df) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.setDateFormat(df);
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(json, typeReference);
        } catch (JsonParseException e) {
            logger.error("JsonParseException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }

    /**
     * json转换为java对象
     *
     * <pre>
     * return JackJson.fromJsonToObject(this.answersJson, JackJson.class);
     * </pre>
     *
     * @param <T>
     *            要转换的对象
     * @param json
     *            字符串
     * @param valueType
     *            对象的class
     * @return 返回对象
     */
    public static <T> T fromJsonToObject(String json, Class<T> valueType) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, valueType);
        } catch (JsonParseException e) {
            logger.error("JsonParseException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }

    /**
     * java对象转换为json字符串
     *
     * @param object
     *            Java对象
     * @return 返回字符串
     */
    public static String fromObjectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            logger.error("JsonGenerationException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }

    /**
     * java对象转换为json字符串
     *
     * @author xiaobowen
     * @param object
     *          Java对象
     * @param df
     *          时间格式对象
     * @return 返回字符串
     */
    public static String fromObjectToJson(Object object , DateFormat df) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.setDateFormat(df);
            return mapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            logger.error("JsonGenerationException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }

    /**
     * java对象转换为json字符串
     *
     * @author xiaobowen
     * @param object
     *          Java对象
     * @param df
     *          时间格式对象
     * @return 返回字符串
     */
    public static String fromObjectToJson(Object object , DateFormat df , JsonSerializer<Object> nvs) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.setDateFormat(df);
            mapper.getSerializerProvider().setNullValueSerializer(nvs);
            return mapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            logger.error("JsonGenerationException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }

    /**
     * java对象转换为json字符串
     *
     * @param object
     *            要转换的对象
     * @param filterName
     *            过滤器的名称
     * @param properties
     *            要过滤哪些字段
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String fromObjectToJson(Object object, String filterName, Set<String> properties) {
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter(filterName,
                SimpleBeanPropertyFilter.serializeAllExcept(properties));
        try {
            return mapper.filteredWriter(filters).writeValueAsString(object);
        } catch (JsonGenerationException e) {
            logger.error("JsonGenerationException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }

    /**
     * java对象转换为json字符串
     *
     * @param object
     *            要转换的对象
     * @param filterName
     *            过滤器的名称
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String fromObjectToJson(Object object, String filterName, String property) {
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter(filterName,
                SimpleBeanPropertyFilter.serializeAllExcept(property));
        try {
            return mapper.filteredWriter(filters).writeValueAsString(object);
        } catch (JsonGenerationException e) {
            logger.error("JsonGenerationException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }

    /**
     * java对象(包含日期字段或属性)转换为json字符串
     *
     * @param object
     *            Java对象
     * @return 返回字符串
     */
    public static String fromObjectHasDateToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.getSerializationConfig().withDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            logger.error("JsonGenerationException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }

    /**
     * java对象(包含日期字段或属性)转换为json字符串
     *
     * @param object
     *            Java对象
     * @param dateTimeFormatString
     *            自定义的日期/时间格式。该属性的值遵循java标准的date/time格式规范。如：yyyy-MM-dd
     * @return 返回字符串
     */
    public static String fromObjectHasDateToJson(Object object, String dateTimeFormatString) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.getSerializationConfig().withDateFormat(new SimpleDateFormat(dateTimeFormatString));
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            logger.error("JsonGenerationException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }
}

