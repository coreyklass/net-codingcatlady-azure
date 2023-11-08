package net.codingcatlady.azure.util;

public class AzureJsonParser {


    /**
     * Stores the Azure JSON Parser to use
     */
    static private AzureJsonParser _parser = new AzureJsonParser();


    /**
     * Returns the Azure Json Parser
     * @return
     */
    static public AzureJsonParser getParser() {
        return _parser;
    }


    /**
     * Sets the Azure JSON Parser to use
     * @param parser
     */
    static public void setParser(AzureJsonParser parser) {
        _parser = parser;
    }





    /**
     * Parses a string and returns an object
     * @param text
     * @return
     */
    public Object parse(String text) throws AzureJsonParserUnimplementedException {
        throw new AzureJsonParserUnimplementedException();
    }

}
