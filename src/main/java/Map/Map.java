package Map;

public class Map {
    private static final Map instance = new Map();

    private Map () {
        //here is the code to implement
    }

    public static Map getMap() {
        return instance;
    }
}
