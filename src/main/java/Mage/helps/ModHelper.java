package Mage.helps;

public class ModHelper {
    private static final  String TAG = "Mage";
    public static  String MakePath(String id) {
        return TAG +":" + id;
    }

    public static  String MakeAssetPath(String path) {
        return TAG + "/" + path;
    }

    public static  String MakeCharacterPath(String name) {
        return TAG+ ":"+ name;
    }
}
