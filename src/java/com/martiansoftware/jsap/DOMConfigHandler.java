/*
 * Copyright (c) 2002-2004, Martian Software, Inc.
 * This file is made available under the LGPL as described in the accompanying
 * LICENSE.TXT file.
 */

package com.martiansoftware.jsap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * Initializes the JSAP options from a runtime xml config file using a DOM parser. 
 */
public class DOMConfigHandler {
    private JSAP jsap = null;
    private NodeList nodelist = null;
    private Locale locale = null;

    /**
     * Constructor for the DOMConfigHandler class
     * @param xmlConfig XML file with command line parameter configuration. 
     * @param locale Language to use to select the &lt;Help&gt; and &lt;UsageName&gt; messages 
     */
    public DOMConfigHandler (String xmlConfig, Locale locale) {
        this.locale = locale;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document dom = builder.parse(xmlConfig);
            this.nodelist = dom.getDocumentElement().getChildNodes();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FactoryConfigurationError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Sets up an JSAP instance.
     * @param jsap JSAP instance to configure
     * @throws JSAPException
     */
    public void setup (JSAP jsap) throws JSAPException {
        AbstractParameter p = null;
        Node node = null;
        String id = null;
        
        for (int i=0; i<nodelist.getLength(); i++) {
            node = nodelist.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
	            id = getAttributeValue(node, "id");
	            if (node.getNodeName().equals("Switch")) {
	                p = new Switch(id); // TODO: Could also be p = setupSwitch(n, id);
	            } else if (node.getNodeName().equals("FlaggedOption")) {
	                p = new FlaggedOption(id);
	            } else if (node.getNodeName().equals("UnflaggedOption")) {
	                p = new UnflaggedOption(id);
	            } else if (node.getNodeName().equals("QualifiedSwitch")) {
		            p = new QualifiedSwitch(id);
	            }
	            if (p instanceof AbstractParameter) {
	                setupAbstractParameter((AbstractParameter)p, node);
	            }
	            if (p instanceof Option) {
	                setupOption((Option)p, node);
	            }
	            if (p instanceof FlaggedOption) {
	                setupFlaggedOption((FlaggedOption)p, node);
	            }
	            if (p instanceof QualifiedSwitch) {
	                setupQualifiedSwitch((QualifiedSwitch)p, node);
	            }
	            if (p instanceof UnflaggedOption) {
	                setupUnflaggedOption((UnflaggedOption)p, node);
	            }
	            if (p instanceof Switch) {
	                setupSwitch((Switch)p, node);
	            }
	            if (p != null) {
		            jsap.registerParameter(p);
	            }
            }
        }
    }
    
    /**
     * Get an attribute value.
     * @param node XML Node to be processed
     * @param name Attribute name
     * @return Value of the attribute with the given name, null if not available.
     */
    private String getAttributeValue(Node node, String name) {
        NamedNodeMap attrs = node.getAttributes();
        Node attr = attrs.getNamedItem(name);
        String s = null;
        if (attr != null) {
            s = attr.getNodeValue();
        }
        return s;
    }

    /**
     * Get a child element with a certain name.
     * @param node XML Node to be processed
     * @param name Element name of the child element 
     * @return First child element with the given name, null otherwise.
     */
    private Node getChildElement (Node node, String name) {
        NodeList nl = node.getChildNodes();
        for (int i=0; i<nl.getLength(); i++) {
            Node n = nl.item(i);
            if ((n.getNodeType() == Node.ELEMENT_NODE) && (n.getNodeName().equals(name))) {
                return n;
            }
        }
        return null;
    }
    
    /**
     * Get character values of an element.
     * @param node XML Node to be processed
     * @param name Name of the child element
     * @return Character values of the first child element with the given name
     */
    private String getElementValue(Node node, String name) {
        NodeList nl = node.getChildNodes();
        for (int i=0; i<nl.getLength(); i++) {
            Node n = nl.item(i);
            if ((n.getNodeType() == Node.ELEMENT_NODE) && (n.getNodeName().equals(name))) {
                NodeList nl1 = n.getChildNodes();
                String s = "";
                for (int j=0; j<nl1.getLength(); j++) {
                    Node n1 = nl1.item(j);
                    if (n1.getNodeType() == Node.TEXT_NODE) {
                        s += n1.getNodeValue();
                    }
                }
                return s;
            }
        }
        return null;
    }
    
    /**
     * Get a map with the attribute values as map key and the element characters as map values.
     * @param node XML Node to be processed
     * @param elementName Name of the child element
     * @param attrName Attribute name to use as map key
     * @return Map with the attribute values as key and the element's character values as map values
     */
    private Map getValueMap(Node node, String elementName, String attrName) {
        Map map = new HashMap();
        NodeList nl = node.getChildNodes();
        for (int i=0; i<nl.getLength(); i++) {
            Node n = nl.item(i);
            if ((n.getNodeType() == Node.ELEMENT_NODE) && (n.getNodeName().equals(elementName))) {
                NodeList nl1 = n.getChildNodes();
                String s = "";
                for (int j=0; j<nl1.getLength(); j++) {
                    Node n1 = nl1.item(j);
                    if (n1.getNodeType() == Node.TEXT_NODE) {
                        try {
                            String av = n.getAttributes().getNamedItem(attrName).getNodeValue();
                            map.put(av,n1.getNodeValue());
                        } catch(Exception e) {
                            // Nothing to do.
                        }
                    }
                }
            }
        }
        return map;
    }
    
    // Datatype helper methods
    /**
     * Get boolean value from a string.
     * @param s String representation of the boolean value
     * @return Boolean value true, if String equals to 'true', false otherwise.
     */
    private boolean getBoolean (String s) {
        return (s != null) && (s.equals("true"));        
    }
    
    /**
     * Get character value from a string.
     * @param s String representation of a character value
     * @return First character of the given string, 0x00 if string is empty.
     */
    private char getChar (String s) {
        return (s != null) ? s.charAt(0) : 0x00;        
    }
    
    /**
     * Selects a localized string, using english as default language, if the current locale is not used in the config file.
     * @param node XML Node to be processed
     * @param elementName Name of the child elements to select the localized strings from
     * @return String which fits the current locale using "en" as the default if the current locale matches no attribute with name "lang". 
     */
    private String getLocalizedString (Node node, String elementName) {
        Map map = getValueMap(node, elementName, "lang");
        String lang = "en"; // Default language
        String locLang = locale.getLanguage();
        if (map.containsKey(locLang)) lang = locLang;
        return (String)map.get(lang);
    }
    
    /**
     * Sets up an AbstractParameter instance.
     * @param par
     * @param node
     */
    // Setup methods for every parameter type
    private void setupAbstractParameter(AbstractParameter par, Node node) {
        String help = getLocalizedString(node, "Help");
        if (help != null) {
            par.setHelp(help);
        }

        // TODO: Why is setDefault not part of the AbstractParameter?
        String deflt = getElementValue(node, "Default");
        if (deflt != null) {
            par._setDefault(deflt); // TODO: Special handling for lists
        }
    }

    /**
     * Sets up a Flagged instance.
     * @param f
     * @param node
     */
    private void setupFlagged (Flagged f, Node node) {
        // TODO: f.setShortFlag(), f.setLongFlag() if they get part of the interface 
    }

    /**
     * Sets up an Option instance.
     * @param option
     * @param node
     */
    private void setupOption (Option option, Node node) {
        // Nothing to do.
    }

    /**
     * Sets up a FlaggedOption instance.
     * @param option
     * @param node
     */
    private void setupFlaggedOption (FlaggedOption option, Node node) {
        String strVal = null;
        char chrVal = 0x00;
        
        // Allow multiple declarations
        strVal = getAttributeValue(node,"multiple");
        option.setAllowMultipleDeclarations(getBoolean(strVal));

        // Usage name
        strVal = getLocalizedString(node,"UsageName");
        if (strVal != null) {
            option.setUsageName(strVal);
        }
        
        // List
        strVal = getAttributeValue(node,"list");
        if (strVal != null) {
            option.setList(getBoolean(strVal));
        }
        
        // List separator
        chrVal = getChar(getAttributeValue(node,"listSeparator"));
        if (chrVal != 0x00) {
            option.setListSeparator(chrVal);
        }
        
        // Long flag
        strVal = getAttributeValue(node,"longFlag");
        if (strVal != null) {
            option.setLongFlag(strVal);
        }
        
        // Required
        strVal = getAttributeValue(node,"required");
        if (strVal != null) {
            option.setRequired(getBoolean(strVal));
        }
        
        // Short flag
        chrVal = getChar(getAttributeValue(node,"shortFlag"));
        if (chrVal != 0x00) {
            option.setShortFlag(chrVal);
        }

        // String parser
        Node spn = getChildElement (node, "StringParser");
        if (spn != null) {
            strVal = getAttributeValue(spn, "classname");
            try {
                StringParser sp = (StringParser) Class.forName(strVal).newInstance(); 
                if (sp instanceof PropertyStringParser) {
                    NodeList nodelist = spn.getChildNodes();
                    for (int i=0; i<nodelist.getLength(); i++) {
                        Node n = nodelist.item(i);
                        if ((n.getNodeType() == Node.ELEMENT_NODE) && (n.getNodeName().equals("property"))) {
                            ((PropertyStringParser) sp).setProperty(getAttributeValue(n,"name"),getAttributeValue(n,"value"));
                        }
                    }
                }
                option.setStringParser(sp);
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets up an UnFlaggedOption instance.
     * @param option
     * @param node
     */
    private void setupUnflaggedOption (UnflaggedOption option, Node node) {
        String strVal = null;

        // Usage name
        strVal = getLocalizedString(node,"UsageName");
        if (strVal != null) {
            option.setUsageName(strVal);
        }
        
        // Greedy
        strVal = getAttributeValue(node,"greedy");
        if (strVal != null) {
            option.setGreedy(getBoolean(strVal));
        }
    }

    /**
     * Sets up a QualifiedSwitch instance.
     * @param qsw
     * @param node
     */
    private void setupQualifiedSwitch (QualifiedSwitch qsw, Node node) {
        // Nothing to do.
    }

    /**
     * Sets up a Switch instance.
     * @param sw
     * @param node
     */
    private void setupSwitch (Switch sw, Node node) {
        // TODO: If setLongFlag/setShortFlag was part of Flagged,
        // this redundancy with setupFlaggedOption could be eleminated 
        String strVal = null;
        char chrVal = 0x00;
        
        // Long flag
        strVal = getAttributeValue(node,"longFlag");
        if (strVal != null) {
            sw.setLongFlag(strVal);
        }
        
        // Short flag
        chrVal = getChar(getAttributeValue(node,"shortFlag"));
        if (chrVal != 0x00) {
            sw.setShortFlag(chrVal);
        }
    }

}
