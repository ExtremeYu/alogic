package com.alogic.together.plugins;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alogic.together.ExecuteWatcher;
import com.alogic.together.Logiclet;
import com.alogic.together.LogicletContext;
import com.anysoft.util.Properties;
import com.anysoft.util.PropertiesConstants;
import com.jayway.jsonpath.JsonPath;

/**
 * 循环
 * 
 * @author duanyy
 *
 */
public class Repeat extends Segment{
	protected String jsonPath;
	
	public Repeat(String tag, Logiclet p) {
		super(tag, p);
	}
	
	public void configure(Properties p){
		jsonPath = PropertiesConstants.getString(p, "path", jsonPath);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onExecute(Map<String, Object> root,
			Map<String, Object> current, LogicletContext ctx, ExecuteWatcher watcher) {
		if (StringUtils.isNotEmpty(jsonPath)){
			Object result = JsonPath.read(current, jsonPath);

			if (result instanceof List<?>){
				List<Object> list = (List<Object>)result;
				for (Object o:list){
					if (o instanceof Map<?,?>){
						Map<String,Object> newCurrent = (Map<String,Object>)o;
						super.onExecute(root, newCurrent, ctx, watcher);
					}
				}
			}else{
				if (result instanceof Map<?,?>){
					Map<String,Object> newCurrent = (Map<String,Object>)result;
					super.onExecute(root, newCurrent, ctx, watcher);
				}else{
					logger.error("Can not locate the path:" + jsonPath);
				}
			}
		}
	}
	
}
