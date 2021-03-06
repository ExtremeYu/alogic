package com.logicbus.examples;

import com.anysoft.util.Properties;
import com.logicbus.backend.Context;
import com.logicbus.backend.ServantException;
import com.logicbus.models.servant.Argument;
import com.logicbus.models.servant.getter.Default;

public class AppendHello extends Default {
	public AppendHello(Properties props) {
		super(props);
	}

	
	public String getValue(Argument argu, Context ctx) throws ServantException {
		return super.getValue(argu,ctx) + "hello";
	}
}
