package net.dictionmasters.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordListCollection {

	public final String[] audioName = {"abalone",
			"abandon",
			"abel",
			"abiotic",
			"about",
			"above",
			"abraham",
			"abstemious",
			"accessory",
			"academia",
			"accept",
			"acceptable",
			"accolade",
			"accountant",
			"accurate",
			"accusative",
			"acoustic",
			"acquiesce",
			"action",
			"actually",
			"adage",
			"addiction",
			"adhesion",
			"adhesive",
			"adjacent",
			"adolescence",
			"adopt",
			"advisable",
			"advisor",
			"advocacy",
			"adze",
			"aegis",
			"aeronautics",
			"affidavit",
			"afraid",
			"african",
			"agate",
			"ago",
			"agony",
			"agree",
			"agreement",
			"ague",
			"ahoy",
			"aisle",
			"aitch",
			"alert",
			"alex",
			"alexandra",
			"alfred",
			"algae",
			"alimony",
			"allocate",
			"along",
			"alternative",
			"although",
			"alto",
			"alumni",
			"amateur",
			"amazed",
			"amazing",
			"amazon",
			"ambiguity",
			"amoeba",
			"anaemia",
			"anal",
			"anemone",
			"animal",
			"annual",
			"anode",
			"anthony",
			"antipodes",
			"anus",
			"any",
			"anything",
			"aorta",
			"apart",
			"apology",
			"apostle",
			"appetite",
			"applicant",
			"arabic",
			"arable",
			"archimedes",
			"architect",
			"area",
			"argument",
			"around",
			"aseptic",
			"asexual",
			"asian",
			"ask",
			"assistance",
			"assume",
			"asterisk",
			"audacious",
			"august",
			"aura",
			"available",
			"awareness",
			"awry",
			"axial",
			"axiom",
			"ayes",
			"babel",
			"bald head",
			"ballet",
			"banal",
			"bang",
			"banger",
			"barbed wire",
			"barefoot",
			"bargain",
			"based",
			"basic",
			"bass",
			"bath",
			"bathe",
			"bathroom",
			"bear",
			"beige",
			"beneficence",
			"beneficent",
			"benefit",
			"benevolent",
			"bethany",
			"bethel",
			"biased",
			"bigot",
			"billion",
			"bird",
			"biscuit",
			"bishop",
			"blatant",
			"blurred",
			"bomb",
			"book",
			"borrow",
			"bosom",
			"boss",
			"bother",
			"bottom",
			"boudoir",
			"bough",
			"bouillon",
			"bound copy",
			"bouquet",
			"bourgeoisie",
			"bowel",
			"bravado",
			"breakfast",
			"bridget",
			"bristle",
			"brochure",
			"brooch",
			"brother",
			"bucket",
			"buffalo",
			"buffer",
			"buffet",
			"bugle",
			"build",
			"building",
			"bull",
			"buoy",
			"buoyant",
			"burden",
			"bureau",
			"burger",
			"burn",
			"burrow",
			"business",
			"buttocks",
			"busy",
			"cabbage",
			"cache",
			"cajun",
			"calcium",
			"calculate",
			"calendar",
			"callous",
			"campus",
			"canary",
			"capable",
			"capital",
			"carafe",
			"careful",
			"caribou",
			"caring",
			"carnivorous",
			"carrot",
			"cassoulet",
			"castle",
			"category",
			"causative",
			"cavalry",
			"caveat",
			"ceased",
			"ceremony",
			"certainly",
			"chafe",
			"chair",
			"chaise",
			"chalet",
			"chamber",
			"chameleon",
			"champion",
			"chaos",
			"chapel",
			"character",
			"charade",
			"charlatan",
			"charm",
			"chartreuse",
			"chasm",
			"chassis",
			"chasten",
			"chastise",
			"chastity",
			"chauffer",
			"chauvinism",
			"chevrolet",
			"chewing gum",
			"cheyenne",
			"chez",
			"chi",
			"chiasmus",
			"chic",
			"chicanery",
			"chime",
			"chipotle",
			"chitin",
			"chocolate",
			"christendom",
			"christian",
			"christianity",
			"christmas",
			"christopher",
			"church",
			"chute",
			"circuit",
			"circulate",
			"citrus",
			"cleanliness",
			"cleanse",
			"clifford",
			"close (n or adj)",
			"close (v)",
			"closet",
			"coax",
			"cobra",
			"coerce",
			"cohesion",
			"coiffure",
			"collection",
			"colonel",
			"colour",
			"columbus",
			"comb",
			"combine",
			"come",
			"comedian",
			"comfortable",
			"command",
			"commitment",
			"commodity",
			"common",
			"communication",
			"community",
			"comparative",
			"competitive",
			"completely",
			"complexion",
			"component",
			"compose",
			"computer",
			"concern",
			"concubine",
			"condition",
			"confidence",
			"confirm",
			"confusion",
			"connection",
			"connoisseur",
			"conservation",
			"consomme",
			"consult",
			"consultant",
			"consume",
			"consumer",
			"consumption",
			"contagious",
			"continue",
			"convert",
			"convicted",
			"cook",
			"co-operate",
			"co-operation",
			"co-operative",
			"co-ordinate",
			"corp",
			"corporate",
			"correct",
			"corrosion",
			"cosset",
			"cottage",
			"could",
			"country",
			"coupe",
			"courage",
			"courier",
			"courtesy",
			"cousin",
			"cranny",
			"creature",
			"creche",
			"crescent",
			"cricket",
			"crochet",
			"crocodile",
			"crooked",
			"cruel",
			"crumb",
			"crustacean",
			"cucumber",
			"cuisine",
			"cupboard",
			"curator",
			"currently",
			"curriculum",
			"curse",
			"curtains",
			"curve",
			"cutaneous",
			"cutesy",
			"cuticle",
			"cutlass",
			"cutlery",
			"czar",
			"dairy",
			"dare",
			"david",
			"de trop",
			"dearest",
			"deborah",
			"debris",
			"debt",
			"debtor",
			"debut",
			"decipher",
			"decision",
			"declaration",
			"defiance",
			"delicacy",
			"demolition",
			"demon",
			"depot",
			"desmond",
			"determine",
			"devastation",
			"development",
			"devour",
			"devout",
			"dictionary",
			"difficult",
			"diffusion",
			"diplomat",
			"dire",
			"divorce",
			"doctor",
			"dominion",
			"don’t",
			"donald",
			"door mouth",
			"dosage",
			"douglas",
			"dove",
			"dragon",
			"draught",
			"drawer",
			"duncan",
			"dyslexia",
			"earning",
			"eclectic",
			"eclipse",
			"economics",
			"edith",
			"education",
			"edward",
			"efficacy",
			"effort",
			"einstein",
			"elegant",
			"elite",
			"elizabeth",
			"embarrass",
			"emission",
			"emit",
			"employ",
			"enable",
			"enchant",
			"endanger",
			"endure",
			"enemy",
			"engine",
			"engineer",
			"england",
			"enmity",
			"ensemble",
			"entente",
			"entertainment",
			"entree",
			"entrepreneur",
			"environment",
			"envisage",
			"envision",
			"epaulette",
			"epitome",
			"ernest",
			"escape",
			"especially",
			"essential",
			"estate",
			"ethel",
			"eunuch",
			"euphemism",
			"eureka",
			"evanescent",
			"evelyn",
			"evolution",
			"ewe",
			"exacerbate",
			"exactly",
			"example",
			"execution",
			"exile (noun)",
			"exist",
			"exodus",
			"expatiate",
			"explain",
			"external",
			"extraordinary",
			"exuberance",
			"fabulous",
			"facade",
			"facetious",
			"factor",
			"famine",
			"famous",
			"fasten",
			"fatal",
			"father",
			"father-in-law",
			"faux pas",
			"favourable",
			"favourite",
			"feign",
			"feminine",
			"fete",
			"finally",
			"first",
			"fish",
			"flour",
			"focus",
			"foetus",
			"foliage",
			"follow",
			"foot",
			"forbid",
			"forget",
			"fork",
			"francis",
			"freedom",
			"freight",
			"frivolous",
			"fuel",
			"full",
			"funeral",
			"furniture",
			"further",
			"fuselage"};
	
    public static List<String> listDataHeaderNG = new ArrayList<String>();
    public static HashMap<String, List<String>> listDataChildNG = new HashMap<String, List<String>>();

    public static void initCollection(){
        // START OF LEVEL 1 LISTING
        listDataHeaderNG.add(" WORDS STARTING WITH A");
        listDataHeaderNG.add(" WORDS STARTING WITH B");
        listDataHeaderNG.add(" WORDS STARTING WITH C");
        listDataHeaderNG.add(" WORDS STARTING WITH D");
        listDataHeaderNG.add(" WORDS STARTING WITH E");
        listDataHeaderNG.add(" WORDS STARTING WITH F");
        listDataHeaderNG.add(" WORDS STARTING WITH G");
        listDataHeaderNG.add(" WORDS STARTING WITH H");
        listDataHeaderNG.add(" WORDS STARTING WITH I");
        listDataHeaderNG.add(" WORDS STARTING WITH J");
        listDataHeaderNG.add(" WORDS STARTING WITH K");
        listDataHeaderNG.add(" WORDS STARTING WITH L");
        listDataHeaderNG.add(" WORDS STARTING WITH M");
        listDataHeaderNG.add(" WORDS STARTING WITH N");
        listDataHeaderNG.add(" WORDS STARTING WITH O");
        listDataHeaderNG.add(" WORDS STARTING WITH P");
        listDataHeaderNG.add(" WORDS STARTING WITH Q");
        listDataHeaderNG.add(" WORDS STARTING WITH R");
        listDataHeaderNG.add(" WORDS STARTING WITH S");
        listDataHeaderNG.add(" WORDS STARTING WITH T");
        listDataHeaderNG.add(" WORDS STARTING WITH U");
        listDataHeaderNG.add(" WORDS STARTING WITH V");
        listDataHeaderNG.add(" WORDS STARTING WITH W");
        listDataHeaderNG.add(" WORDS STARTING WITH X");
        listDataHeaderNG.add(" WORDS STARTING WITH Y");
        listDataHeaderNG.add(" WORDS STARTING WITH Z");
        // END OF LEVEL 1 LISTING

        List<String> awords = new ArrayList<String>();
        awords.add("Abalone");
        awords.add("Abandon");
        awords.add("Abel");
        awords.add("Abiotic");
        awords.add("About");
        awords.add("Above");
        awords.add("Abraham");
        awords.add("Abstemious");
        awords.add("Accessory");
        awords.add("Academia");
        awords.add("Accept");
        awords.add("Acceptable");
        awords.add("Accolade");
        awords.add("Accountant");
        awords.add("Accurate");
        awords.add("Accusative");
        awords.add("Acoustic");
        awords.add("Acquiesce");
        awords.add("Action");
        awords.add("Actually");
        awords.add("Adage");
        awords.add("Addiction");
        awords.add("Adhesion");
        awords.add("Adhesive");
        awords.add("Adjacent");
        awords.add("Adolescence");
        awords.add("Adopt");
        awords.add("Advisable");
        awords.add("Advisor");
        awords.add("Advocacy");
        awords.add("Adze");
        awords.add("Aegis");
        awords.add("Aeronautics");
        awords.add("Affidavit");
        awords.add("Afraid");
        awords.add("African");
        awords.add("Agate");
        awords.add("Ago");
        awords.add("Agony");
        awords.add("Agree");
        awords.add("Agreement");
        awords.add("Ague");
        awords.add("Ahoy");
        awords.add("Aisle");
        awords.add("Aitch");
        awords.add("Alert");
        awords.add("Alex");
        awords.add("Alexandra");
        awords.add("Alfred");
        awords.add("Algae");
        awords.add("Alimony");
        awords.add("Allocate");
        awords.add("Along");
        awords.add("Alternative");
        awords.add("Although");
        awords.add("Alto");
        awords.add("Alumni");
        awords.add("Amateur");
        awords.add("Amazed");
        awords.add("Amazing");
        awords.add("Amazon");
        awords.add("Ambiguity");
        awords.add("Amoeba");
        awords.add("Anaemia");
        awords.add("Anal");
        awords.add("Anemone");
        awords.add("Animal");
        awords.add("Annual");
        awords.add("Anode");
        awords.add("Anthony");
        awords.add("Antipodes");
        awords.add("Anus");
        awords.add("Any");
        awords.add("Anything");
        awords.add("Aorta");
        awords.add("Apart");
        awords.add("Apology");
        awords.add("Apostle");
        awords.add("Appetite");
        awords.add("Applicant");
        awords.add("Arabic");
        awords.add("Arable farming");
        awords.add("Archimedes");
        awords.add("Architect");
        awords.add("Area");
        awords.add("Argument");
        awords.add("Around");
        awords.add("Aseptic");
        awords.add("Asexual");
        awords.add("Asian");
        awords.add("Ask");
        awords.add("Assistance");
        awords.add("Assume");
        awords.add("Asterisk");
        awords.add("Audacious");
        awords.add("August");
        awords.add("Aura");
        awords.add("Available");
        awords.add("Awareness");
        awords.add("Awry");
        awords.add("Axial");
        awords.add("Axiom");
        awords.add("Ayes");
        
        
        List<String> bwords = new ArrayList<String>();
        bwords.add("Babel");
        bwords.add("Bald head");
        bwords.add("Ballet");
        bwords.add("Banal");
        bwords.add("Bang");
        bwords.add("Banger");
        bwords.add("Barbed wire");
        bwords.add("Barefoot");
        bwords.add("Bargain");
        bwords.add("Based");
        bwords.add("Basic");
        bwords.add("Bass");
        bwords.add("Bath");
        bwords.add("Bathe");
        bwords.add("Bathroom");
        bwords.add("Bear");
        bwords.add("Beige");
        bwords.add("Beneficence");
        bwords.add("Beneficent");
        bwords.add("Benefit");
        bwords.add("Benevolent");
        bwords.add("Bethany");
        bwords.add("Bethel");
        bwords.add("Biased");
        bwords.add("Bigot");
        bwords.add("Billion");
        bwords.add("Bird");
        bwords.add("Biscuit");
        bwords.add("Bishop");
        bwords.add("Blatant");
        bwords.add("Blurred");
        bwords.add("Bomb");
        bwords.add("Book");
        bwords.add("Borrow");
        bwords.add("Bosom");
        bwords.add("Boss");
        bwords.add("Bother");
        bwords.add("Bottom");
        bwords.add("Boudoir");
        bwords.add("Bough");
        bwords.add("Bouillon");
        bwords.add("Bound copy");
        bwords.add("Bouquet");
        bwords.add("Bourgeoisie");
        bwords.add("Bowel");
        bwords.add("Bravado");
        bwords.add("Breakfast");
        bwords.add("Bridget");
        bwords.add("Bristle");
        bwords.add("Brochure");
        bwords.add("Brooch");
        bwords.add("Brother");
        bwords.add("Bucket");
        bwords.add("Buffalo");
        bwords.add("Buffer");
        bwords.add("Buffet");
        bwords.add("Bugle");
        bwords.add("Build");
        bwords.add("Building");
        bwords.add("Bull");
        bwords.add("Buoy");
        bwords.add("Buoyant");
        bwords.add("Burden");
        bwords.add("Bureau");
        bwords.add("Burger");
        bwords.add("Burn");
        bwords.add("Burrow");
        bwords.add("Business");
        bwords.add("Buttocks");
        bwords.add("Busy");
        
        
        List<String> cwords = new ArrayList<String>();
        cwords.add("Cabbage");
        cwords.add("Cache");
        cwords.add("Cajun");
        cwords.add("Calcium");
        cwords.add("Calculate");
        cwords.add("Calendar");
        cwords.add("Callous");
        cwords.add("Campus");
        cwords.add("Canary");
        cwords.add("Capable");
        cwords.add("Capital");
        cwords.add("Carafe");
        cwords.add("Careful");
        cwords.add("Caribou");
        cwords.add("Caring");
        cwords.add("Carnivorous");
        cwords.add("Carrot");
        cwords.add("Cassoulet");
        cwords.add("Castle");
        cwords.add("Category");
        cwords.add("Causative");
        cwords.add("Cavalry");
        cwords.add("Caveat");
        cwords.add("Ceased");
        cwords.add("Ceremony");
        cwords.add("Certainly");
        cwords.add("Chafe");
        cwords.add("Chair");
        cwords.add("Chaise");
        cwords.add("Chalet");
        cwords.add("Chamber");
        cwords.add("Chameleon");
        cwords.add("Champion");
        cwords.add("Chaos");
        cwords.add("Chapel");
        cwords.add("Character");
        cwords.add("Charade");
        cwords.add("Charlatan");
        cwords.add("Charm");
        cwords.add("Chartreuse");
        cwords.add("Chasm");
        cwords.add("Chassis");
        cwords.add("Chasten");
        cwords.add("Chastise");
        cwords.add("Chastity");
        cwords.add("Chauffer");
        cwords.add("Chauvinism");
        cwords.add("Chevrolet");
        cwords.add("Chewing gum");
        cwords.add("Cheyenne");
        cwords.add("Chez");
        cwords.add("Chi");
        cwords.add("Chiasmus");
        cwords.add("Chic");
        cwords.add("Chicanery");
        cwords.add("Chime");
        cwords.add("Chipotle");
        cwords.add("Chitin");
        cwords.add("Chocolate");
        cwords.add("Christendom");
        cwords.add("Christian");
        cwords.add("Christianity");
        cwords.add("Christmas");
        cwords.add("Christopher");
        cwords.add("Church");
        cwords.add("Chute");
        cwords.add("Circuit");
        cwords.add("Circulate");
        cwords.add("Citrus");
        cwords.add("Cleanliness");
        cwords.add("Cleanse");
        cwords.add("Clifford");
        cwords.add("Close (n or adj)");
        cwords.add("Close (v)");
        cwords.add("Closet");
        cwords.add("Coax");
        cwords.add("Cobra");
        cwords.add("Coerce");
        cwords.add("Cohesion");
        cwords.add("Coiffure");
        cwords.add("Collection");
        cwords.add("Colonel");
        cwords.add("Colour");
        cwords.add("Columbus");
        cwords.add("Comb");
        cwords.add("Combine");
        cwords.add("Come");
        cwords.add("Comedian");
        cwords.add("Comfortable");
        cwords.add("Command");
        cwords.add("Commitment");
        cwords.add("Commodity");
        cwords.add("Common");
        cwords.add("Communication");
        cwords.add("Community");
        cwords.add("Comparative");
        cwords.add("Competitive");
        cwords.add("Completely");
        cwords.add("Complexion");
        cwords.add("Component");
        cwords.add("Compose");
        cwords.add("Computer");
        cwords.add("Concern");
        cwords.add("Concubine");
        cwords.add("Condition");
        cwords.add("Confidence");
        cwords.add("Confirm");
        cwords.add("Confusion");
        cwords.add("Connection");
        cwords.add("Connoisseur");
        cwords.add("Conservation");
        cwords.add("Consommé");
        cwords.add("Consult");
        cwords.add("Consultant");
        cwords.add("Consume");
        cwords.add("Consumer");
        cwords.add("Consumption");
        cwords.add("Contagious");
        cwords.add("Continue");
        cwords.add("Convert");
        cwords.add("Convicted");
        cwords.add("Cook");
        cwords.add("Co-operate");
        cwords.add("Co-operation");
        cwords.add("Co-operative");
        cwords.add("Co-ordinate");
        cwords.add("Corp");
        cwords.add("Corporate");
        cwords.add("Correct");
        cwords.add("Corrosion");
        cwords.add("Cosset");
        cwords.add("Cottage");
        cwords.add("Could");
        cwords.add("Country");
        cwords.add("Coupe");
        cwords.add("Courage");
        cwords.add("Courier");
        cwords.add("Courtesy");
        cwords.add("Cousin");
        cwords.add("Cranny");
        cwords.add("Creature");
        cwords.add("Creche");
        cwords.add("Crescent");
        cwords.add("Cricket");
        cwords.add("Crochet");
        cwords.add("Crocodile");
        cwords.add("Crooked");
        cwords.add("Cruel");
        cwords.add("Crumb");
        cwords.add("Crustacean");
        cwords.add("Cucumber");
        cwords.add("Cuisine");
        cwords.add("Cupboard");
        cwords.add("Curator");
        cwords.add("Currently");
        cwords.add("Curriculum");
        cwords.add("Curse");
        cwords.add("Curtains");
        cwords.add("Curve");
        cwords.add("Cutaneous");
        cwords.add("Cutesy");
        cwords.add("Cuticle");
        cwords.add("Cutlass");
        cwords.add("Cutlery");
        cwords.add("Czar");
        
        List<String> dwords = new ArrayList<String>();
        dwords.add("Dairy");
        dwords.add("Dare");
        dwords.add("David");
        dwords.add("De trop");
        dwords.add("Dearest");
        dwords.add("Deborah");
        dwords.add("Debris");
        dwords.add("Debt");
        dwords.add("Debtor");
        dwords.add("Debut");
        dwords.add("Decipher");
        dwords.add("Decision");
        dwords.add("Declaration");
        dwords.add("Defiance");
        dwords.add("Delicacy");
        dwords.add("Demolition");
        dwords.add("Demon");
        dwords.add("Depot");
        dwords.add("Desmond");
        dwords.add("Determine");
        dwords.add("Devastation");
        dwords.add("Development");
        dwords.add("Devour");
        dwords.add("Devout");
        dwords.add("Dictionary");
        dwords.add("Difficult");
        dwords.add("Diffusion");
        dwords.add("Diplomat");
        dwords.add("Dire");
        dwords.add("Divorce");
        dwords.add("Doctor");
        dwords.add("Dominion");
        dwords.add("Don't");
        dwords.add("Donald");
        dwords.add("Door mouth");
        dwords.add("Dosage");
        dwords.add("Douglas");
        dwords.add("Dove");
        dwords.add("Dragon");
        dwords.add("Draught");
        dwords.add("Drawer");
        dwords.add("Duncan");
        dwords.add("Dyslexia");
        
        List<String> ewords = new ArrayList<String>();
        ewords.add("Earning");
        ewords.add("Eclectic");
        ewords.add("Eclipse");
        ewords.add("Economics");
        ewords.add("Edith");
        ewords.add("Education");
        ewords.add("Edward");
        ewords.add("Efficacy");
        ewords.add("Effort");
        ewords.add("Einstein");
        ewords.add("Elegant");
        ewords.add("Elite");
        ewords.add("Elizabeth");
        ewords.add("Embarrass");
        ewords.add("Emission");
        ewords.add("Emit");
        ewords.add("Employ");
        ewords.add("Enable");
        ewords.add("Enchant");
        ewords.add("Endanger");
        ewords.add("Endure");
        ewords.add("Enemy");
        ewords.add("Engine");
        ewords.add("Engineer");
        ewords.add("England");
        ewords.add("Enmity");
        ewords.add("Ensemble");
        ewords.add("Entente");
        ewords.add("Entertainment");
        ewords.add("Entr�e");
        ewords.add("Entrepreneur");
        ewords.add("Environment");
        ewords.add("Envisage");
        ewords.add("Envision");
        ewords.add("Epaulette");
        ewords.add("Epitome");
        ewords.add("Ernest");
        ewords.add("Escape");
        ewords.add("Especially");
        ewords.add("Essential");
        ewords.add("Estate");
        ewords.add("Ethel");
        ewords.add("Eunuch");
        ewords.add("Euphemism");
        ewords.add("Eureka");
        ewords.add("Evanescent");
        ewords.add("Evelyn");
        ewords.add("Evolution");
        ewords.add("Ewe");
        ewords.add("Exacerbate");
        ewords.add("Exactly");
        ewords.add("Example");
        ewords.add("Execution");
        ewords.add("Exile (noun)");
        ewords.add("Exist");
        ewords.add("Exodus");
        ewords.add("Expatiate");
        ewords.add("Explain");
        ewords.add("External");
        ewords.add("Extraordinary");
        ewords.add("Exuberance");

        List<String> fwords = new ArrayList<String>();
        fwords.add("Fabulous");
        fwords.add("Facade");
        fwords.add("Facetious");
        fwords.add("Factor");
        fwords.add("Famine");
        fwords.add("Famous");
        fwords.add("Fasten");
        fwords.add("Fatal");
        fwords.add("Father");
        fwords.add("Father-in-law");
        fwords.add("Faux pas");
        fwords.add("Favourable");
        fwords.add("Favourite");
        fwords.add("Feign");
        fwords.add("Feminine");
        fwords.add("Fete");
        fwords.add("Finally");
        fwords.add("First");
        fwords.add("Fish");
        fwords.add("Flour");
        fwords.add("Focus");
        fwords.add("Foetus");
        fwords.add("Foliage");
        fwords.add("Follow");
        fwords.add("Foot");
        fwords.add("Forbid");
        fwords.add("Forget");
        fwords.add("Fork");
        fwords.add("Francis");
        fwords.add("Freedom");
        fwords.add("Freight");
        fwords.add("Frivolous");
        fwords.add("Fuel");
        fwords.add("Full");
        fwords.add("Funeral");
        fwords.add("Furniture");
        fwords.add("Further");
        fwords.add("Fuselage");
        
        List<String> comingSoonWord = new ArrayList<String>();
        comingSoonWord.add("Words coming soon");
        
        listDataChildNG.put(listDataHeaderNG.get(0), awords); 
        listDataChildNG.put(listDataHeaderNG.get(1), bwords);
        listDataChildNG.put(listDataHeaderNG.get(2), cwords);
        listDataChildNG.put(listDataHeaderNG.get(3), dwords);
        listDataChildNG.put(listDataHeaderNG.get(4), ewords);
        listDataChildNG.put(listDataHeaderNG.get(5), fwords);
        listDataChildNG.put(listDataHeaderNG.get(6), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(7), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(8), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(9), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(10), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(11), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(12), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(13), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(14), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(15), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(16), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(17), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(18), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(19), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(20), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(21), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(22), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(23), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(24), comingSoonWord);
        listDataChildNG.put(listDataHeaderNG.get(25), comingSoonWord);

    }
    	
}
