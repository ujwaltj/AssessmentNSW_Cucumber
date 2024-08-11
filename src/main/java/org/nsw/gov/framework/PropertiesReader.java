package org.nsw.gov.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesReader {

    Properties propertiesObject;
    public PropertiesReader(File propFile)
    {
        propertiesObject = new Properties();
        try
        {
            propertiesObject.load(new InputStreamReader(new FileInputStream(propFile)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

	/*
	public static PropertiesReader getInstance()
	{
	     if(instance == null)
	     {
	        instance = new PropertiesReader();
	     }
	     return instance;
	}
	*/

    public String getData(String key)
    {
        return propertiesObject.getProperty(key);
    }
}
