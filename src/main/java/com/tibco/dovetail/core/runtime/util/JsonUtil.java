package com.tibco.dovetail.core.runtime.util;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ParseContext;

public class JsonUtil {
    static Configuration jsonconfig = Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL).addOptions(Option.ALWAYS_RETURN_LIST);

    public static ParseContext getJsonParser (){
        return JsonPath.using(jsonconfig);
    }

}
