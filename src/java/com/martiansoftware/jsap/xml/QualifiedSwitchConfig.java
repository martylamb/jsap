package com.martiansoftware.jsap.xml;

import com.martiansoftware.jsap.AbstractParameter;
import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.QualifiedSwitch;


/**
 * @author mlamb
 */
public class QualifiedSwitchConfig extends FlaggedOptionConfig {

	protected void configure(QualifiedSwitch qs) {
		super.configure(qs);
	}

	public AbstractParameter getConfiguredParameter() {
		QualifiedSwitch result = new QualifiedSwitch(getId());
		configure(result);
		return (result);
	}
}
