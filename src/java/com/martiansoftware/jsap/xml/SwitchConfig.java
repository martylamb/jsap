package com.martiansoftware.jsap.xml;

import com.martiansoftware.jsap.AbstractParameter;
import com.martiansoftware.jsap.Switch;


/**
 * @author mlamb
 */
public class SwitchConfig extends FlaggedConfig {

	public AbstractParameter getConfiguredParameter() {
		Switch result = new Switch(getId());
		super.configure(result);
		return (result);
	}
}
