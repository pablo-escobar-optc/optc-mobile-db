package it.instruman.treasurecruisedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Paolo on 31/10/2016.
 */

class DBHelper extends SQLiteOpenHelper {

    // Name of database
    static final String DB_NAME = "optc_db_mobile";
    // Version of database
    private static final int DB_VERSION = 1;

    // Table units
    final static String UNITS_TABLE = "units";
    // Columns
    final static String UNITS_ID = "_id";
    final static String UNITS_CHARID = "charid";
    final static String UNITS_NAME = "name";
    final static String UNITS_TYPE = "type";
    final static String UNITS_CLASS1 = "class1";
    final static String UNITS_CLASS2 = "class2";
    final static String UNITS_STARS = "stars";
    final static String UNITS_COST = "cost";
    final static String UNITS_COMBO = "combo";
    final static String UNITS_SOCKETS = "sockets";
    final static String UNITS_MAXLEVEL = "maxlevel";
    final static String UNITS_EXPTOMAX = "exptomax";
    final static String UNITS_LVL1HP = "lvl1hp";
    final static String UNITS_LVL1ATK = "lvl1atk";
    final static String UNITS_LVL1RCV = "lvl1rcv";
    final static String UNITS_MAXHP = "maxhp";
    final static String UNITS_MAXATK = "maxatk";
    final static String UNITS_MAXRCV = "maxrcv";

    // Table specials
    final static String SPECIALS_TABLE = "specials";
    // Columns
    final static String SPECIALS_ID = "_id";
    final static String SPECIALS_CHARID = "char_id";
    final static String SPECIALS_NAME = "name";
    final static String SPECIALS_DESCRIPTION = "description";
    final static String SPECIALS_MAXCD = "max_cd";
    final static String SPECIALS_MINCD = "min_cd";
    final static String SPECIALS_NOTES = "notes";

    // Table captains
    final static String CAPTAINS_TABLE = "captains";
    // Columns
    final static String CAPTAINS_ID = "_id";
    final static String CAPTAINS_CHARID = "char_id";
    final static String CAPTAINS_DESCRIPTION = "description";
    final static String CAPTAINS_NOTES = "notes";

    // Table evolutions
    private final static String EVOLUTIONS_TABLE = "evolutions";
    // Columns
    private final static String EVOLUTIONS_ID = "_id";
    private final static String EVOLUTIONS_CHARID = "char_id";
    private final static String EVOLUTIONS_EVOID = "evolution_id";
    private final static String EVOLUTIONS_EV1 = "evolver_1";
    private final static String EVOLUTIONS_EV2 = "evolver_2";
    private final static String EVOLUTIONS_EV3 = "evolver_3";
    private final static String EVOLUTIONS_EV4 = "evolver_4";
    private final static String EVOLUTIONS_EV5 = "evolver_5";

    // Table drop locations
    private final static String DROPS_TABLE = "drop_locations";
    // Columns
    private final static String DROPS_ID = "_id";
    private final static String DROPS_CHARID = "char_id";
    private final static String DROPS_LOCATIONS = "drop_location";
    private final static String DROPS_CHAPDIFF = "chapter_or_difficulty";
    private final static String DROPS_GLOBAL = "global";
    private final static String DROPS_JAPAN = "japan";
    private final static String DROPS_THUMB = "thumbnail";

    // Table manual locations
    private final static String MANUALS_TABLE = "manuals_locations";
    // Columns
    private final static String MANUALS_ID = "_id";
    private final static String MANUALS_CHARID = "char_id";
    private final static String MANUALS_LOCATIONS = "drop_location";
    private final static String MANUALS_CHAPDIFF = "chapter_or_difficulty";
    private final static String MANUALS_GLOBAL = "global";
    private final static String MANUALS_JAPAN = "japan";
    private final static String MANUALS_THUMB = "thumbnail";

    // Table manual locations
    private final static String CREWMATE_TABLE = "crewmate_abilities";
    // Columns
    private final static String CREWMATE_ID = "_id";
    private final static String CREWMATE_CHARID = "char_id";
    private final static String CREWMATE_DESCRIPTION = "crewmate_description";
    private final static String CREWMATE_NOTES = "crewmate_notes";

    // Table limit
    private final static String LIMIT_TABLE = "char_limit";
    // Columns
    private final static String LIMIT_ID = "_id";
    private final static String LIMIT_CHARID = "char_id";
    private final static String LIMIT_DESCRIPTION = "description";
    private final static String LIMIT_NOTES = "notes";

    // Table potential
    private final static String POTENTIAL_TABLE = "char_potential";
    // Columns
    private final static String POTENTIAL_ID = "_id";
    private final static String POTENTIAL_CHARID = "char_id";
    private final static String POTENTIAL_NAME = "name";
    private final static String POTENTIAL_LVL1 = "lvl1";
    private final static String POTENTIAL_LVL2 = "lvl2";
    private final static String POTENTIAL_LVL3 = "lvl3";
    private final static String POTENTIAL_LVL4 = "lvl4";
    private final static String POTENTIAL_LVL5 = "lvl5";
    private final static String POTENTIAL_NOTES = "notes";

    // Table aliases
    final static String ALIASES_TABLE = "aliases";
    //Columns
    final static String ALIASES_ID = "_id";
    final static String ALIASES_CHARID = "char_id";
    final static String ALIASES_ALIAS = "char_alias";


    // Custom SQL
    //Drop tables
    private final static String DROP_UNITS = "DROP TABLE IF EXISTS " + UNITS_TABLE;
    private final static String DROP_SPECIALS = "DROP TABLE IF EXISTS " + SPECIALS_TABLE;
    private final static String DROP_CAPTAINS = "DROP TABLE IF EXISTS " + CAPTAINS_TABLE;
    private final static String DROP_EVOLUTIONS = "DROP TABLE IF EXISTS " + EVOLUTIONS_TABLE;
    private final static String DROP_DROPS = "DROP TABLE IF EXISTS " + DROPS_TABLE;
    private final static String DROP_MANUALS = "DROP TABLE IF EXISTS " + MANUALS_TABLE;
    private final static String DROP_CREWMATE = "DROP TABLE IF EXISTS " + CREWMATE_TABLE;
    private final static String DROP_LIMIT = "DROP TABLE IF EXISTS " + LIMIT_TABLE;
    private final static String DROP_POTENTIAL = "DROP TABLE IF EXISTS " + POTENTIAL_TABLE;
    private final static String DROP_ALIASES = "DROP TABLE IF EXISTS " + ALIASES_TABLE;

    // Create tables
    private final static String CREATE_TABLE_UNITS =
            "CREATE TABLE " + UNITS_TABLE + " (" +
                    UNITS_ID + " INTEGER, " +
                    UNITS_CHARID + " INT NOT NULL, " +
                    UNITS_NAME + " TEXT NOT NULL, " +
                    UNITS_TYPE + " TEXT, " +
                    UNITS_CLASS1 + " TEXT, " +
                    UNITS_CLASS2 + " TEXT, " +
                    UNITS_STARS + " REAL, " +
                    UNITS_COST + " INT, " +
                    UNITS_COMBO + " INT, " +
                    UNITS_SOCKETS + " INT, " +
                    UNITS_MAXLEVEL + " INT, " +
                    UNITS_EXPTOMAX + " INT, " +
                    UNITS_LVL1HP + " INT, " +
                    UNITS_LVL1ATK + " INT, " +
                    UNITS_LVL1RCV + " INT, " +
                    UNITS_MAXHP + " INT, " +
                    UNITS_MAXATK + " INT, " +
                    UNITS_MAXRCV + " INT, " +
                    "PRIMARY KEY ( " + UNITS_ID + " ))";
    private final static String CREATE_TABLE_SPECIALS =
            "CREATE TABLE " + SPECIALS_TABLE + " (" +
                    SPECIALS_ID + " INTEGER, " +
                    SPECIALS_CHARID + " INT NOT NULL, " +
                    SPECIALS_NAME + " TEXT NOT NULL, " +
                    SPECIALS_DESCRIPTION + " TEXT NOT NULL, " +
                    SPECIALS_MAXCD + " INT, " +
                    SPECIALS_MINCD + " INT, " +
                    SPECIALS_NOTES + " TEXT, " +
                    "PRIMARY KEY ( " + SPECIALS_ID + " ))";
    private final static String CREATE_TABLE_CAPTAINS =
            "CREATE TABLE " + CAPTAINS_TABLE + " (" +
                    CAPTAINS_ID + " INTEGER, " +
                    CAPTAINS_CHARID + " INT NOT NULL, " +
                    CAPTAINS_DESCRIPTION + " TEXT NOT NULL, " +
                    CAPTAINS_NOTES + " TEXT, " +
                    "PRIMARY KEY ( " + CAPTAINS_ID + " ))";
    private final static String CREATE_TABLE_EVOLUTIONS =
            "CREATE TABLE " + EVOLUTIONS_TABLE + " (" +
                    EVOLUTIONS_ID + " INTEGER, " +
                    EVOLUTIONS_CHARID + " INT NOT NULL, " +
                    EVOLUTIONS_EVOID + " INT NOT NULL, " +
                    EVOLUTIONS_EV1 + " INT, " +
                    EVOLUTIONS_EV2 + " INT, " +
                    EVOLUTIONS_EV3 + " INT, " +
                    EVOLUTIONS_EV4 + " INT, " +
                    EVOLUTIONS_EV5 + " INT, " +
                    "PRIMARY KEY ( " + EVOLUTIONS_ID + " ))";
    private final static String CREATE_TABLE_DROPS =
            "CREATE TABLE " + DROPS_TABLE + " (" +
                    DROPS_ID + " INTEGER, " +
                    DROPS_CHARID + " INT, " +
                    DROPS_LOCATIONS + " TEXT, " +
                    DROPS_CHAPDIFF + " TEXT, " +
                    DROPS_GLOBAL + " INT, " +
                    DROPS_JAPAN + " INT, " +
                    DROPS_THUMB + " INT, " +
                    "PRIMARY KEY ( " + DROPS_ID + " ))";
    private final static String CREATE_TABLE_MANUALS =
            "CREATE TABLE " + MANUALS_TABLE + " (" +
                    MANUALS_ID + " INTEGER, " +
                    MANUALS_CHARID + " INT, " +
                    MANUALS_LOCATIONS + " TEXT, " +
                    MANUALS_CHAPDIFF + " TEXT, " +
                    MANUALS_GLOBAL + " INT, " +
                    MANUALS_JAPAN + " INT, " +
                    MANUALS_THUMB + " INT, " +
                    "PRIMARY KEY ( " + MANUALS_ID + " ))";
    private final static String CREATE_TABLE_CREWMATE =
            "CREATE TABLE " + CREWMATE_TABLE + " (" +
                    CREWMATE_ID + " INTEGER, " +
                    CREWMATE_CHARID + " INT, " +
                    CREWMATE_DESCRIPTION + " TEXT, " +
                    CREWMATE_NOTES + " TEXT, " +
                    "PRIMARY KEY ( " + CREWMATE_ID + " ))";
    private final static String CREATE_TABLE_LIMIT =
            "CREATE TABLE " + LIMIT_TABLE + " (" +
                    LIMIT_ID + " INTEGER, " +
                    LIMIT_CHARID + " INT NOT NULL, " +
                    LIMIT_DESCRIPTION + " TEXT NOT NULL, " +
                    LIMIT_NOTES + " TEXT, " +
                    "PRIMARY KEY ( " + SPECIALS_ID + " ))";
    private final static String CREATE_TABLE_POTENTIAL =
            "CREATE TABLE " + POTENTIAL_TABLE + " (" +
                    POTENTIAL_ID + " INTEGER, " +
                    POTENTIAL_CHARID + " INT NOT NULL, " +
                    POTENTIAL_NAME + " TEXT NOT NULL, " +
                    POTENTIAL_LVL1 + " TEXT NOT NULL, " +
                    POTENTIAL_LVL2 + " TEXT NOT NULL, " +
                    POTENTIAL_LVL3 + " TEXT NOT NULL, " +
                    POTENTIAL_LVL4 + " TEXT NOT NULL, " +
                    POTENTIAL_LVL5 + " TEXT NOT NULL, " +
                    POTENTIAL_NOTES + " TEXT, " +
                    "PRIMARY KEY ( " + SPECIALS_ID + " ))";
    private final static String CREATE_TABLE_ALIASES =
            "CREATE TABLE " + ALIASES_TABLE + " (" +
                    ALIASES_ID + " INTEGER, " +
                    ALIASES_CHARID + " INT NOT NULL, " +
                    ALIASES_ALIAS + " TEXT NOT NULL, " +
                    "PRIMARY KEY ( " + ALIASES_ID + " ))";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void dropAndCreateTables() {
        SQLiteDatabase db = getWritableDatabase();
        //Drop tables
        db.execSQL(DROP_UNITS);
        db.execSQL(DROP_SPECIALS);
        db.execSQL(DROP_CAPTAINS);
        db.execSQL(DROP_EVOLUTIONS);
        db.execSQL(DROP_DROPS);
        db.execSQL(DROP_MANUALS);
        db.execSQL(DROP_CREWMATE);
        db.execSQL(DROP_LIMIT);
        db.execSQL(DROP_POTENTIAL);
        db.execSQL(DROP_ALIASES);

        //Create tables
        db.execSQL(CREATE_TABLE_UNITS);
        db.execSQL(CREATE_TABLE_SPECIALS);
        db.execSQL(CREATE_TABLE_CAPTAINS);
        db.execSQL(CREATE_TABLE_EVOLUTIONS);
        db.execSQL(CREATE_TABLE_DROPS);
        db.execSQL(CREATE_TABLE_MANUALS);
        db.execSQL(CREATE_TABLE_CREWMATE);
        db.execSQL(CREATE_TABLE_LIMIT);
        db.execSQL(CREATE_TABLE_POTENTIAL);
        db.execSQL(CREATE_TABLE_ALIASES);
        db.close();
    }

    public static ArrayList<HashMap> getAllCharacters(SQLiteDatabase db) {
        ArrayList<HashMap> result = new ArrayList<>();
        Cursor entry = db.query(UNITS_TABLE, new String[]{UNITS_CHARID, UNITS_NAME, UNITS_TYPE, UNITS_STARS, UNITS_MAXATK, UNITS_MAXHP, UNITS_MAXRCV}, null, null, null, null, null, null);
        entry.moveToFirst();
        while (!entry.isAfterLast()) {
            HashMap<String, Object> tmp = new HashMap();
            tmp.put(Constants.ID, entry.getInt(0));
            tmp.put(Constants.NAME, entry.getString(1));
            tmp.put(Constants.TYPE, entry.getString(2));
            tmp.put(Constants.STARS, entry.getDouble(3));
            tmp.put(Constants.MAXATK, entry.getInt(4));
            tmp.put(Constants.MAXHP, entry.getInt(5));
            tmp.put(Constants.MAXRCV, entry.getInt(6));
            result.add(tmp);
            entry.moveToNext();
        }
        entry.close();
        return result;
    }

    public static void dropAndCreateTables(SQLiteDatabase db) {
        //Drop tables
        db.execSQL(DROP_UNITS);
        db.execSQL(DROP_SPECIALS);
        db.execSQL(DROP_CAPTAINS);
        db.execSQL(DROP_EVOLUTIONS);
        db.execSQL(DROP_DROPS);
        db.execSQL(DROP_MANUALS);
        db.execSQL(DROP_CREWMATE);
        db.execSQL(DROP_LIMIT);
        db.execSQL(DROP_POTENTIAL);
        db.execSQL(DROP_ALIASES);

        //Create tables
        db.execSQL(CREATE_TABLE_UNITS);
        db.execSQL(CREATE_TABLE_SPECIALS);
        db.execSQL(CREATE_TABLE_CAPTAINS);
        db.execSQL(CREATE_TABLE_EVOLUTIONS);
        db.execSQL(CREATE_TABLE_DROPS);
        db.execSQL(CREATE_TABLE_MANUALS);
        db.execSQL(CREATE_TABLE_CREWMATE);
        db.execSQL(CREATE_TABLE_LIMIT);
        db.execSQL(CREATE_TABLE_POTENTIAL);
        db.execSQL(CREATE_TABLE_ALIASES);
    }

    public static void insertIntoDrops(SQLiteDatabase db, Integer char_id, String drop_location, String chap_or_difficulty, boolean is_global, boolean is_japan, Integer thumbnail)
    {
        Cursor drops_cursor = db.query(DROPS_TABLE, new String[] {DROPS_ID,DROPS_CHAPDIFF}, DROPS_CHARID + " = ? AND " + DROPS_LOCATIONS
                + " = ? AND " + DROPS_GLOBAL + " = ? AND " + DROPS_JAPAN + " = ? AND " + DROPS_THUMB + " = ?",
                new String[] {String.valueOf(char_id), drop_location, String.valueOf(is_global?1:0), String.valueOf(is_japan?1:0), String.valueOf(thumbnail)}, null, null, null, null);

        if (drops_cursor.getCount()>0)
        {
            drops_cursor.moveToFirst();
            ContentValues values = new ContentValues();
            values.put(DROPS_CHAPDIFF, drops_cursor.getString(1)+", "+chap_or_difficulty);
            db.update(DROPS_TABLE, values, DROPS_ID + " = ?", new String[] {String.valueOf(drops_cursor.getInt(0))});
        } else {
            ContentValues values = new ContentValues();
            values.put(DROPS_CHARID, char_id);
            values.put(DROPS_LOCATIONS, drop_location);
            values.put(DROPS_CHAPDIFF, chap_or_difficulty);
            values.put(DROPS_GLOBAL, is_global ? 1 : 0);
            values.put(DROPS_JAPAN, is_japan ? 1 : 0);
            values.put(DROPS_THUMB, thumbnail);
            db.insert(DROPS_TABLE, null, values);
        }
        drops_cursor.close();
    }

    public static void insertIntoManuals(SQLiteDatabase db, Integer char_id, String drop_location, String chap_or_difficulty, boolean is_global, boolean is_japan, Integer thumbnail)
    {
        Cursor manuals_cursor = db.query(MANUALS_TABLE, new String[] {MANUALS_ID,MANUALS_CHAPDIFF}, MANUALS_CHARID + " = ? AND " + MANUALS_LOCATIONS
                        + " = ? AND " + MANUALS_GLOBAL + " = ? AND " + MANUALS_JAPAN + " = ? AND " + MANUALS_THUMB + " = ?",
                new String[] {String.valueOf(char_id), drop_location, String.valueOf(is_global?1:0), String.valueOf(is_japan?1:0), String.valueOf(thumbnail)}, null, null, null, null);

        if (manuals_cursor.getCount()>0)
        {
            manuals_cursor.moveToFirst();
            ContentValues values = new ContentValues();
            values.put(MANUALS_CHAPDIFF, manuals_cursor.getString(1)+", "+chap_or_difficulty);
            db.update(MANUALS_TABLE, values, MANUALS_ID + " = ?", new String[] {String.valueOf(manuals_cursor.getInt(0))});
        } else {
            ContentValues values = new ContentValues();
            values.put(MANUALS_CHARID, char_id);
            values.put(MANUALS_LOCATIONS, drop_location);
            values.put(MANUALS_CHAPDIFF, chap_or_difficulty);
            values.put(MANUALS_GLOBAL, is_global ? 1 : 0);
            values.put(MANUALS_JAPAN, is_japan ? 1 : 0);
            values.put(MANUALS_THUMB, thumbnail);
            db.insert(MANUALS_TABLE, null, values);
        }
        manuals_cursor.close();
    }

    public static void insertIntoUnits(SQLiteDatabase db, Integer id, String name, String type, String class1, String class2,
                                       Double stars, Integer cost, Integer combo, Integer sockets, Integer maxlevel,
                                       Integer exptomax, Integer lvl1hp, Integer lvl1atk, Integer lvl1rcv, Integer maxhp,
                                       Integer maxatk, Integer maxrcv) {

        ContentValues values = new ContentValues();
        values.put(UNITS_CHARID, id);
        values.put(UNITS_NAME, name);
        values.put(UNITS_TYPE, type);
        values.put(UNITS_CLASS1, class1);
        values.put(UNITS_CLASS2, class2);
        values.put(UNITS_STARS, stars);
        values.put(UNITS_COST, cost);
        values.put(UNITS_COMBO, combo);
        values.put(UNITS_SOCKETS, sockets);
        values.put(UNITS_MAXLEVEL, maxlevel);
        values.put(UNITS_EXPTOMAX, exptomax);
        values.put(UNITS_LVL1HP, lvl1hp);
        values.put(UNITS_LVL1ATK, lvl1atk);
        values.put(UNITS_LVL1RCV, lvl1rcv);
        values.put(UNITS_MAXHP, maxhp);
        values.put(UNITS_MAXATK, maxatk);
        values.put(UNITS_MAXRCV, maxrcv);

        db.insert(UNITS_TABLE, null, values);
    }

    public static void insertIntoSpecials(SQLiteDatabase db, Integer charid, String name, String description, Integer maxcd,
                                          Integer mincd, String notes) {
        ContentValues values = new ContentValues();

        values.put(SPECIALS_CHARID, charid);
        values.put(SPECIALS_NAME, name);
        values.put(SPECIALS_DESCRIPTION, description);
        values.put(SPECIALS_MAXCD, maxcd);
        values.put(SPECIALS_MINCD, mincd);
        values.put(SPECIALS_NOTES, notes);

        db.insert(SPECIALS_TABLE, null, values);
    }

    public static void insertIntoCaptains(SQLiteDatabase db, Integer charid, String description, String notes) {
        ContentValues values = new ContentValues();

        values.put(CAPTAINS_CHARID, charid);
        values.put(CAPTAINS_DESCRIPTION, description);
        values.put(CAPTAINS_NOTES, notes);

        db.insert(CAPTAINS_TABLE, null, values);
    }

    public static void insertIntoCrewmate(SQLiteDatabase db, Integer charid, String description, String notes) {
        ContentValues values = new ContentValues();

        values.put(CREWMATE_CHARID, charid);
        values.put(CREWMATE_DESCRIPTION, description);
        values.put(CREWMATE_NOTES, notes);

        db.insert(CREWMATE_TABLE, null, values);
    }

    public static void insertIntoEvolutions(SQLiteDatabase db, Integer charid, Integer evoid, Integer ev1, Integer ev2, Integer ev3, Integer ev4, Integer ev5) {
        ContentValues values = new ContentValues();

        values.put(EVOLUTIONS_CHARID, charid);
        values.put(EVOLUTIONS_EVOID, evoid);
        values.put(EVOLUTIONS_EV1, ev1);
        values.put(EVOLUTIONS_EV2, ev2);
        values.put(EVOLUTIONS_EV3, ev3);
        values.put(EVOLUTIONS_EV4, ev4);
        values.put(EVOLUTIONS_EV5, ev5);

        db.insert(EVOLUTIONS_TABLE, null, values);
    }

    public static void insertIntoLimit(SQLiteDatabase db, Integer charid, String description, String notes) {
        ContentValues values = new ContentValues();

        values.put(LIMIT_CHARID, charid);
        values.put(LIMIT_DESCRIPTION, description);
        values.put(LIMIT_NOTES, notes);

        db.insert(LIMIT_TABLE, null, values);
    }

    public static void insertIntoPotential(SQLiteDatabase db, Integer charid, String name, String lvl1,
                                           String lvl2, String lvl3, String lvl4, String lvl5, String notes) {
        ContentValues values = new ContentValues();

        values.put(POTENTIAL_CHARID, charid);
        values.put(POTENTIAL_NAME, name);
        values.put(POTENTIAL_LVL1, lvl1);
        values.put(POTENTIAL_LVL2, lvl2);
        values.put(POTENTIAL_LVL3, lvl3);
        values.put(POTENTIAL_LVL4, lvl4);
        values.put(POTENTIAL_LVL5, lvl5);
        values.put(POTENTIAL_NOTES, notes);

        db.insert(POTENTIAL_TABLE, null, values);
    }

    public static void insertIntoAliases(SQLiteDatabase db, Integer charid, String alias) {
        ContentValues values = new ContentValues();

        values.put(ALIASES_CHARID, charid);
        values.put(ALIASES_ALIAS, alias);

        db.insert(ALIASES_TABLE, null, values);
    }

    public static CharacterInfo getCharacterInfo(SQLiteDatabase db, Integer CharId) {
        Cursor units_cursor = db.query(UNITS_TABLE, new String[]{UNITS_ID, UNITS_NAME, UNITS_TYPE, UNITS_CLASS1, UNITS_CLASS2,
                        UNITS_STARS, UNITS_COST, UNITS_COMBO, UNITS_SOCKETS, UNITS_MAXLEVEL, UNITS_EXPTOMAX,
                        UNITS_LVL1HP, UNITS_LVL1ATK, UNITS_LVL1RCV, UNITS_MAXHP, UNITS_MAXATK, UNITS_MAXRCV}, UNITS_CHARID + " = ?",
                new String[]{String.valueOf(CharId)}, null, null, null, null);
        units_cursor.moveToFirst();
        if (units_cursor.isAfterLast()) return null;

        Integer id = units_cursor.getInt(0);
        String char_name = units_cursor.getString(1);
        String char_type = units_cursor.getString(2);
        String char_class1 = units_cursor.getString(3);
        String char_class2 = units_cursor.getString(4);
        Double char_stars = units_cursor.getDouble(5);
        Integer char_cost = units_cursor.getInt(6);
        Integer char_combo = units_cursor.getInt(7);
        Integer char_sockets = units_cursor.getInt(8);
        Integer char_maxlvl = units_cursor.getInt(9);
        Integer char_exptomax = units_cursor.getInt(10);
        Integer char_lvl1hp = units_cursor.getInt(11);
        Integer char_lvl1atk = units_cursor.getInt(12);
        Integer char_lvl1rcv = units_cursor.getInt(13);
        Integer char_maxhp = units_cursor.getInt(14);
        Integer char_maxatk = units_cursor.getInt(15);
        Integer char_maxrcv = units_cursor.getInt(16);

        Cursor specials_cursor = db.query(SPECIALS_TABLE, new String[]{SPECIALS_NAME, SPECIALS_DESCRIPTION, SPECIALS_MAXCD,
                SPECIALS_MINCD, SPECIALS_NOTES}, SPECIALS_CHARID + " = ?", new String[]{String.valueOf(CharId)}, null, null, null, null);
        specials_cursor.moveToFirst();

        Cursor captain_cursor = db.query(CAPTAINS_TABLE, new String[]{CAPTAINS_DESCRIPTION, CAPTAINS_NOTES},
                CAPTAINS_CHARID + " = ?", new String[]{String.valueOf(CharId)}, null, null, null, null);
        Boolean has_captain = captain_cursor.moveToFirst();

        Cursor evolutions_cursor = db.query(EVOLUTIONS_TABLE, new String[]{EVOLUTIONS_EVOID, EVOLUTIONS_EV1,
                        EVOLUTIONS_EV2, EVOLUTIONS_EV3, EVOLUTIONS_EV4, EVOLUTIONS_EV5},
                EVOLUTIONS_CHARID + " = ?", new String[]{String.valueOf(CharId)}, null, null, null, null);
        evolutions_cursor.moveToFirst();

        Cursor drops_cursor = db.query(DROPS_TABLE, new String[]{DROPS_LOCATIONS, DROPS_CHAPDIFF,
                        DROPS_GLOBAL, DROPS_JAPAN, DROPS_THUMB},
                DROPS_CHARID + " = ?", new String[]{String.valueOf(CharId)}, null, null, null, null);
        drops_cursor.moveToFirst();

        Cursor manuals_cursor = db.query(MANUALS_TABLE, new String[]{MANUALS_LOCATIONS, MANUALS_CHAPDIFF,
                        MANUALS_GLOBAL, MANUALS_JAPAN, MANUALS_THUMB},
                MANUALS_CHARID + " = ?", new String[]{String.valueOf(CharId)}, null, null, null, null);
        manuals_cursor.moveToFirst();

        Cursor crewmate_cursor = db.query(CREWMATE_TABLE, new String[]{CREWMATE_DESCRIPTION, CREWMATE_NOTES},
                CREWMATE_CHARID + " = ?", new String[]{String.valueOf(CharId)}, null, null, null, null);
        crewmate_cursor.moveToFirst();

        Cursor limit_cursor = db.query(LIMIT_TABLE, new String[]{LIMIT_DESCRIPTION, LIMIT_NOTES}, LIMIT_CHARID + " = ?",
                new String[]{String.valueOf(CharId)}, null, null, null, null);
        limit_cursor.moveToFirst();

        Cursor potential_cursor = db.query(POTENTIAL_TABLE, new String[]{POTENTIAL_NAME, POTENTIAL_LVL1, POTENTIAL_LVL2,
                        POTENTIAL_LVL3, POTENTIAL_LVL4, POTENTIAL_LVL5, POTENTIAL_NOTES}, POTENTIAL_CHARID + " = ?",
                new String[]{String.valueOf(CharId)}, null, null, null, null);
        potential_cursor.moveToFirst();
        
        ArrayList<CharacterSpecials> specials_data = new ArrayList<>();
        String special_name = "";
        String special_notes = "";
        int i = 1;
        while (!specials_cursor.isAfterLast()) {
            CharacterSpecials char_spec = new CharacterSpecials(specials_cursor.getInt(2), specials_cursor.getInt(3), specials_cursor.getString(1), i, specials_cursor.getString(4));
            specials_data.add(char_spec);
            special_name = specials_cursor.getString(0);
            special_notes = specials_cursor.getString(4);
            specials_cursor.moveToNext();
            i++;
        }

        String captain_description = "";
        String captain_notes = "";
        if (has_captain) {
            captain_description = captain_cursor.getString(0);
            captain_notes = captain_cursor.getString(1);
        }

        ArrayList<CharacterEvolutions> char_evos = new ArrayList<>();
        while (!evolutions_cursor.isAfterLast()) {
            ArrayList<Integer> evolvers = new ArrayList<>();
            for (int n = 1; n <= 5; n++) {
                evolvers.add(evolutions_cursor.getInt(n));
            }
            CharacterEvolutions evos = new CharacterEvolutions(evolutions_cursor.getInt(0), evolvers);
            char_evos.add(evos);
            evolutions_cursor.moveToNext();
        }

        ArrayList<DropInfo> dropInfos = new ArrayList<>();
        while(!drops_cursor.isAfterLast()) {
            DropInfo dropInfo = new DropInfo(CharId, drops_cursor.getString(1), drops_cursor.getString(0), drops_cursor.getInt(4), (drops_cursor.getInt(2)==1), (drops_cursor.getInt(3)==1));
            dropInfos.add(dropInfo);
            drops_cursor.moveToNext();
        }

        ArrayList<DropInfo> manualsInfos = new ArrayList<>();
        while(!manuals_cursor.isAfterLast()) {
            DropInfo manualsInfo = new DropInfo(CharId, manuals_cursor.getString(1), manuals_cursor.getString(0), manuals_cursor.getInt(4), (manuals_cursor.getInt(2)==1), (manuals_cursor.getInt(3)==1));
            manualsInfos.add(manualsInfo);
            manuals_cursor.moveToNext();
        }

        String cwDesc = null;
        String cwNotes = null;
        if(!crewmate_cursor.isAfterLast()) {
            cwDesc = crewmate_cursor.getString(0);
            cwNotes = crewmate_cursor.getString(1);
        }

        Limits charLimits = null;
        if(limit_cursor.getCount()>0) {
            charLimits = new Limits();
            ArrayList<String> limitEntries = new ArrayList<>();
            boolean isLimitFirst = true;
            while (!limit_cursor.isAfterLast()) {
                if (isLimitFirst) {
                    charLimits.setLimitNotes(limit_cursor.getString(1));
                    isLimitFirst = false;
                }
                limitEntries.add(limit_cursor.getString(0));
                limit_cursor.moveToNext();
            }
            charLimits.setLimitEntries(limitEntries);
        }

        Potentials charPotentials = null;
        if(potential_cursor.getCount()>0) {
            charPotentials = new Potentials();
            LinkedHashMap<String, ArrayList<String>> potentialsEntries = new LinkedHashMap<>();
            boolean isPotentialFirst = true;
            while (!potential_cursor.isAfterLast()) {
                if (isPotentialFirst) {
                    charPotentials.setPotentialNotes(potential_cursor.getString(6));
                    isPotentialFirst = false;
                }
                String potName = potential_cursor.getString(0);
                ArrayList<String> potLevels = new ArrayList<>();
                potLevels.add(potential_cursor.getString(1));
                potLevels.add(potential_cursor.getString(2));
                potLevels.add(potential_cursor.getString(3));
                potLevels.add(potential_cursor.getString(4));
                potLevels.add(potential_cursor.getString(5));
                potentialsEntries.put(potName, potLevels);
                potential_cursor.moveToNext();
            }
            charPotentials.setPotentialEntries(potentialsEntries);
        }

        units_cursor.close();
        specials_cursor.close();
        captain_cursor.close();
        evolutions_cursor.close();
        drops_cursor.close();
        manuals_cursor.close();
        crewmate_cursor.close();
        limit_cursor.close();
        potential_cursor.close();

        return new CharacterInfo(captain_description, captain_notes, char_class1, char_class2, char_combo,
                char_cost, id, char_evos, char_exptomax, CharId, char_lvl1atk, char_lvl1hp, char_lvl1rcv,
                char_maxatk, char_maxhp, char_maxlvl, char_maxrcv, char_name, char_sockets, special_name, special_notes,
                specials_data, char_stars, char_type, dropInfos, manualsInfos, cwDesc, cwNotes, charLimits, charPotentials);
    }

    public static ArrayList<CharacterEvolutions> getEvolutions(SQLiteDatabase db, Integer charID)
    {
        Cursor evolutions_cursor = db.query(EVOLUTIONS_TABLE, new String[]{EVOLUTIONS_EVOID, EVOLUTIONS_EV1,
                        EVOLUTIONS_EV2, EVOLUTIONS_EV3, EVOLUTIONS_EV4, EVOLUTIONS_EV5},
                EVOLUTIONS_CHARID + " = ?", new String[]{String.valueOf(charID)}, null, null, null, null);
        evolutions_cursor.moveToFirst();

        ArrayList<CharacterEvolutions> char_evos = new ArrayList<>();
        while (!evolutions_cursor.isAfterLast()) {
            ArrayList<Integer> evolvers = new ArrayList<>();
            for (int n = 1; n <= 5; n++) {
                evolvers.add(evolutions_cursor.getInt(n));
            }
            CharacterEvolutions evos = new CharacterEvolutions(evolutions_cursor.getInt(0), evolvers);
            char_evos.add(evos);
            evolutions_cursor.moveToNext();
        }
        return char_evos;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        dropAndCreateTables(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        dropAndCreateTables(sqLiteDatabase);
    }
}
