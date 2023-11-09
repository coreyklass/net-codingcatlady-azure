package net.codingcatlady.util.json;

public class JsonCreator {


    /**
     * Stores the Azure JSON Creator to use
     */
    static private JsonCreator _creator = new JsonCreator();


    /**
     * Returns the Azure Json Creator
     * @return
     */
    static public JsonCreator getCreator() {
        return _creator;
    }


    /**
     * Sets the Azure JSON Creator to use
     * @param creator
     */
    static public void setCreator(JsonCreator creator) {
        _creator = creator;
    }





    /**
     * Parses a string and returns an object
     * @param creator
     * @return
     */
    public String create(Object object) throws JsonCreatorUnimplementedException {
        throw new JsonCreatorUnimplementedException();
    }

}
