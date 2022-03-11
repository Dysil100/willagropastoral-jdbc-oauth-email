package duo.cmr.willagropastoral.boundedContexts.routen;

public class Routen {

    public static final String  ADMINROUTE = "/adminindex";
    public static final String  LEADERROUTE = "/leaderindex";
    public static final String  ANALYSE = "/analyse";

    public static final String  AVIS = "/avis";
    public static final String  DELETEAVIS = "/avis/delete/{id}";
    public static final String  AVISLISTE = "/avisliste";

    public static final String  PASSWORDEINGABE = "/passwordeingabe";
    public static final String  MAILEINGABE = "/maileingabe";

    public static final String  REGISTRATION = "/registration";

    public static final String  FINANCESUEBERSICHT = "/financesuebersicht";
    public static final String  FINANCESDELETE = "/finances/delete/{id}";

    public static final String  CONTACTS = "/contacts";

    public static final String PONDEUSESFINANCESUEBERSICHT = "/pondeusesfinancesuebersicht";
    public static final String PONDEUSESUEBERSICHT = "/pondeuses";
    public static final String PONDEUSESMODIFIER = "/pondeuses/modifier/{id}";
    public static final String PONDEUSESDELETE = "/pondeuses/delete/{id}";

    public static final String  PORCINSFINANCESUEBERSICHT = "/porcinsfinancesuebersicht";
    public static final String  PORCINSUEBERSICHT = "/porcins";
    public static final String  PRORCINSMODIFIER = "/porcins/modifier/{id}";
    public static final String  PORCINSDELETE = "/porcins/delete/{id}";

    public static final String  TELECHARGER = "telecharger";
    public static final String ADDSTANDARD = "/addsandards";

    public static final String EMPTYROUTE = "";

    public static final String USERINDEX = "/index";

    public static final String USERLISTE = "/userliste";

    public static final String NOTIFICATIONS = "/notifications";

    public static final String CONFIRMPASSWORDRECOVER = "/delete/confirm";
    public static final String CONFRIMREGISTRATION = "/registration/confirm";
}
