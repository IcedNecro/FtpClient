package org.lyuda.ftp.config;

import static org.lyuda.ftp.config.Config.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses bookmark info from config/prop.prop
 * 
 */
public class ConfigFileParser {
    private static final String PATH_TO_CONFIG_FILE = "config/prop.prop";
    private static final String BOOKMARK_NODES = "\\{([^,]+)\\}\\s*";
    private static final String BOOK_MARKS_PATERN = "\\s*(.+):((.+)|(\\s*\\{([.\\s\\S]*)\\}))";

    private final File file;

    public ConfigFileParser() {
        this.file = new File(PATH_TO_CONFIG_FILE);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            new Thread(new OpenThreadRunnable(reader)).start();
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
    }

    /**
     * Thread that parses data from config
     */
    private class OpenThreadRunnable implements Runnable {
        private BufferedReader reader;

        public OpenThreadRunnable(BufferedReader reader) {
                this.reader = reader;
        }

        @Override
        public void run() {
            StringBuilder strBuffer = new StringBuilder();
            String str;
            try {
                while((str = this.reader.readLine())!=null) {
                        strBuffer.append(str+"\n");
                }
            } catch (IOException e) {
                    e.printStackTrace();
            }

            String resultConfig = strBuffer.toString();

            patternSeeker(resultConfig);

        }

        /**
         * Seeks given pattern in given str
         * @param str
         * @return 
         */
        public HashMap<String,String[]>  patternSeeker(String str) {
            Pattern pattern = Pattern.compile(BOOK_MARKS_PATERN);
            Matcher matcher = pattern.matcher(str);

            ArrayList<String> list = new ArrayList<String>();
            HashMap<String, String[]> map = new HashMap<String, String[]>();

            while(matcher.find()){
                    String s =matcher.group(); 

                    list.add(s);
            }

            for(String s : list) { 
                    map.put(s,parseInfo(s));
            }

            return map;
        }

        /**
         * 
         * @param str - input string
         * @return - parsed data
         */
        private String[] parseInfo(String str){

            Pattern pattern = Pattern.compile(ConfigFileParser.BOOK_MARKS_PATERN);
            Matcher matcher = pattern.matcher(str);
            ArrayList<String> strs = new ArrayList<String>();

            String answer[] = new String[2];

            String key="";

            if(matcher.matches()) {
                key = matcher.group(1);
                answer[0] = key;
                switch(key) {
                case HOME_DIR: {
                    Config config = Config.getInstance();

                    String value = matcher.group(3);
                    answer[1] = value;

                    config.setHomeDirectory(value);
                }
                case HOST:
                case LOGIN:
                case PASSWORD: {

                        String value = matcher.group(3);
                        answer[1] = value;
                        break;
                }
                case BOOK_MARKS:
                        parseBookmarks(matcher.group(5));
                        break;
                }
            }
            return answer;
        }
        
        /**
         * Parses bookmark data
         * @param str 
         */
        public void parseBookmarks(String str) {
            Config config = Config.getInstance();

            Pattern pattern = Pattern.compile(ConfigFileParser.BOOKMARK_NODES);
            Matcher matcher = pattern.matcher(str);
            try {
                while(matcher.find()){
                    String values = matcher.group(1);
                    
                    HashMap<String, String[]> map = this.patternSeeker(values);
                    Config.BookMark bookMark = new Config.BookMark();

                    for(Iterator<String> iter = map.keySet().iterator();iter.hasNext();){
                        String key = iter.next();
                    
                        Field f = bookMark.getClass().getDeclaredField(map.get(key)[0]);
                        f.setAccessible(true);
                        f.set(bookMark, map.get(key)[1]);
                    }
                    
                    config.addBookMark(bookMark);
                }
            } catch(NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
