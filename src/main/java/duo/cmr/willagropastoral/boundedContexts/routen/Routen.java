package duo.cmr.willagropastoral.boundedContexts.routen;

public class Routen {

    public static final String  ADMINROUTE = "/adminindex";
    public static final String  LEADERROUTE = "/leaderindex";
    public static final String  ANALYSE = "/analyse";

    public static final String  AVIS = "/avis";
    public static final String  DELETEAVIS = "/avis/delete/{id}";
    public static final String  AVISLISTE = "/avis/liste";

    public static final String  PASSWORDEINGABE = "/passwordeingabe";
    public static final String  MAILEINGABE = "/maileingabe";

    public static final String  REGISTRATION = "/registration";

    public static final String  FINANCESUEBERSICHT = "/finances/uebersicht";
    public static final String  FINANCESDELETE = "/finances/delete/{id}";

    public static final String  CONTACTS = "/contacts";

    public static final String  VOLLAILLEUEBERSICHT = "/vollaille";
    public static final String  VOLLAILLEMODIFIER = "/vollaille/modifier/{id}";
    public static final String  VOLLAILLEDELETE = "/vollaille/delete/{id}";

    public static final String  TELECHARGER = "telecharger";
    public static final String ADDSTANDARD = "/addsandards";
}
