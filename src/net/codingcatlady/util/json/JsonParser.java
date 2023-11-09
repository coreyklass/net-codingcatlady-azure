package net.codingcatlady.util.json;

public class JsonParser {


    /**
     * Stores the Azure JSON Parser to use
     */
    static private JsonParser _parser = new JsonParser();


    /**
     * Returns the Azure Json Parser
     * @return
     */
    static public JsonParser getParser() {
        return _parser;
    }


    /**
     * Sets the Azure JSON Parser to use
     * @param parser
     */
    static public void setParser(JsonParser parser) {
        _parser = parser;
    }





    /**
     * Parses a string and returns an object
     * @param text
     * @return
     */
    public Object parse(String text) throws JsonParserUnimplementedException {
        throw new JsonParserUnimplementedException();
    }

}
