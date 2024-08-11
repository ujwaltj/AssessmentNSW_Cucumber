package org.nsw.gov.framework;

import java.io.File;

public class ConfigData {
    static PropertiesReader locatorPropReaderObj;
    static
    {
        locatorPropReaderObj = new PropertiesReader(new File("./src/main/resources/config.properties"));
    }

    public static final String sfURL = locatorPropReaderObj.getData("sfURL");

    public static final String sfDirectUserURL = locatorPropReaderObj.getData("sfDirectUserURL");
    public static final String sfUsernam = locatorPropReaderObj.getData("sfRodneyUN");
}
