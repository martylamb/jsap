/*
 * Copyright (c) 2002-2004, Martian Software, Inc.
 * This file is made available under the LGPL as described in the accompanying
 * LICENSE.TXT file.
 */

package com.martiansoftware.jsap;

import java.io.IOException;
import java.util.Stack;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Initializes the JSAP options from a runtime xml config file using a SAX parser. 
 */
public class SAXConfigHandler extends DefaultHandler {
    private JSAP jsap = null;
    private AbstractParameter p = null;
    private String help = null;
    private String argname = null;
    private String defaultvalue = null;
    private String currentElement = null;
    private Switch s = null;
    private FlaggedOption fo = null;
    private UnflaggedOption ufo = null;
    private QualifiedSwitch qs = null;
    private boolean active = false; // TODO: Not yet used, but can be used to switch jsap parsing on and of if it is embedded in other XML code.
    private Stack elements = null;
    private boolean processing = false;
    private StringParser stringparser = null;
    
    public SAXConfigHandler (JSAP jsap, String xmlConfig) {
        this.jsap = jsap;
        elements = new Stack();
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(xmlConfig, this);
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FactoryConfigurationError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#characters(char[], int, int)
     */
    public void characters(char[] ch, int start, int length) throws SAXException {
	    if (currentElement.equals("Help")) {
	        this.help = new String(ch, start, length);
	    } else if (currentElement.equals("UsageName")) {
	        this.argname = new String(ch, start, length);
	    } else if (currentElement.equals("Default")) {
	        this.defaultvalue = new String(ch, start, length);
	    }
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {
            if (qName.equals("JSAP")) {
                this.active = false;
            } else if (qName.equals("Switch")) {
                if (this.defaultvalue != null) this.s.setDefault(this.defaultvalue); // TODO: Special handling for lists
                if (this.help != null) this.s.setHelp(this.help);
                this.jsap.registerParameter(s);
                processing = false;
            } else if (qName.equals("FlaggedOption")) {
                if (this.stringparser != null) this.fo.setStringParser(this.stringparser);
                if (this.defaultvalue != null) this.fo.setDefault(this.defaultvalue); // TODO: Special handling for lists
                if (this.help != null) this.fo.setHelp(this.help);
                //if (this.argname != null) this.fo.setArgumentName(this.argname);
                this.jsap.registerParameter(fo);
                processing = false;
            } else if (qName.equals("UnflaggedOption")) {
                if (this.stringparser != null) this.ufo.setStringParser(this.stringparser);
                if (this.defaultvalue != null) this.ufo.setDefault(this.defaultvalue); // TODO: Special handling for lists
                if (this.help != null) this.ufo.setHelp(this.help);
                //if (this.argname != null) this.ufo.setArgumentName(this.argname);
                this.jsap.registerParameter(ufo);
                processing = false;
            } else if (qName.equals("QualifiedSwitch")) {
                if (this.stringparser != null) this.qs.setStringParser(this.stringparser);
                if (this.defaultvalue != null) this.qs.setDefault(this.defaultvalue); // TODO: Special handling for lists
                if (this.help != null) this.qs.setHelp(this.help);
                //if (this.argname != null) this.qs.setArgumentName(this.argname);
                this.jsap.registerParameter(qs);
                processing = false;
            }
        } catch (JSAPException e) {
            throw new SAXException(e);
        } finally {
            if (!this.processing) {
	            this.help = null;
	            this.argname = null;
	            this.defaultvalue = null;
	            this.s = null;
	            this.fo = null;
	            this.ufo = null;
	            this.qs = null;
            }
            if (!elements.empty()) { // TODO: this is not really clever, but it works for now.
                elements.pop();
                if (!elements.empty()) {
                    this.currentElement = (String)elements.peek();
                } else {
                    this.currentElement = null;
                }
            } else {
                this.currentElement = null;
            }
        }
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.currentElement = qName;
        elements.push(qName);
        try {
            if (qName.equals("JSAP")) {
                this.active = true;
            } else if (qName.equals("Switch")) {
                this.s = new Switch(attributes.getValue("id"));
                if (attributes.getValue("shortFlag") != null) this.s.setShortFlag(attributes.getValue("shortFlag").charAt(0));
                if (attributes.getValue("longFlag") != null) this.s.setLongFlag(attributes.getValue("longFlag"));
                processing = true;
            } else if (qName.equals("FlaggedOption")) {
                this.fo = new FlaggedOption(attributes.getValue("id"));
                //if (attributes.getValue("default") != null) this.fo.setDefault(attributes.getValue("default"));	// TODO: Special handling for lists
                if (attributes.getValue("list") != null) this.fo.setList(attributes.getValue("list").equals("true"));
                if (attributes.getValue("listSeparator") != null) this.fo.setListSeparator(attributes.getValue("listSeparator").charAt(0));
                if (attributes.getValue("longFlag") != null) this.fo.setLongFlag(attributes.getValue("longFlag"));
                if (attributes.getValue("multiple") != null) this.fo.setAllowMultipleDeclarations(attributes.getValue("multiple").equals("true"));
                if (attributes.getValue("required") != null) this.fo.setRequired(attributes.getValue("required").equals("true"));
                if (attributes.getValue("shortFlag") != null) this.fo.setShortFlag(attributes.getValue("shortflag").charAt(0));
                if (attributes.getValue("stringparser") != null) this.stringparser = (StringParser) Class.forName(attributes.getValue("stringparser")).newInstance();
                // TODO: default:for lists
                processing = true;
            } else if (qName.equals("UnflaggedOption")) {
                this.ufo = new UnflaggedOption(attributes.getValue("id"));
                //if (attributes.getValue("default") != null) this.ufo.setDefault(attributes.getValue("default"));	// TODO: Special handling for lists
                if (attributes.getValue("greedy") != null) this.ufo.setGreedy(attributes.getValue("greedy").equals("true"));
                if (attributes.getValue("list") != null) this.ufo.setList(attributes.getValue("list").equals("true"));
                if (attributes.getValue("listSeparator") != null) this.ufo.setListSeparator(attributes.getValue("listSeparator").charAt(0));
                if (attributes.getValue("required") != null) this.ufo.setRequired(attributes.getValue("required").equals("true"));
                if (attributes.getValue("stringparser") != null) this.stringparser = (StringParser) Class.forName(attributes.getValue("stringparser")).newInstance();
                // TODO: default:for lists
                processing = true;
            } else if (qName.equals("QualifiedSwitch")) {
                this.qs = new QualifiedSwitch(attributes.getValue("id"));
                //if (attributes.getValue("default") != null) this.qs.setDefault(attributes.getValue("default"));	// TODO: Special handling for lists
                if (attributes.getValue("list") != null) this.qs.setList(attributes.getValue("list").equals("true"));
                if (attributes.getValue("listSeparator") != null) this.qs.setListSeparator(attributes.getValue("listSeparator").charAt(0));
                if (attributes.getValue("longFlag") != null) this.qs.setLongFlag(attributes.getValue("longFlag"));
                if (attributes.getValue("multiple") != null) this.qs.setAllowMultipleDeclarations(attributes.getValue("multiple").equals("true"));
                if (attributes.getValue("required") != null) this.qs.setRequired(attributes.getValue("required").equals("true"));
                if (attributes.getValue("shortFlag") != null) this.qs.setShortFlag(attributes.getValue("shortFlag").charAt(0));
                if (attributes.getValue("stringparser") != null) this.stringparser = (StringParser) Class.forName(attributes.getValue("stringparser")).newInstance();
                // TODO: default:for lists
                processing = true;
            } else if (qName.equals("property")) {
                if (this.stringparser != null && this.stringparser instanceof PropertyStringParser) {
                    ((PropertyStringParser) this.stringparser).setProperty(attributes.getValue("name"),attributes.getValue("value"));
                }
            }
        } catch (Exception e) {
            throw new SAXException(e);
        }
    }
}
