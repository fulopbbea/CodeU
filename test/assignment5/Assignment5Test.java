package assignment5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class Assignment5Test {
    
    @Test
    public void testFindAlphabetCountries() throws Exception {
        System.out.println("Countries");
        ArrayList<String> dict = new ArrayList<>();
        dict.addAll(Arrays.asList("afghanistan", "albania", "algeria", "andorra",
        "angola", "argentina", "armenia", "australia", "austria", "azerbaijan",
        "bahamas", "bahrain", "bangladesh", "barbados", "belarus", "belgium", "belize",
        "benin", "bhutan", "bolivia", "botswana", "brazil", "brunei", "bulgaria",
        "burundi", "cambodia", "cameroon", "canada", "chad", "chile", "china", "colombia",
        "comoros", "croatia", "cuba", "cyprus", "czech", "denmark", "djibouti", "dominica",
        "ecuador", "egypt", "equatorial", "eritrea", "estonia", "ethiopia", "fiji",
        "finland", "france", "gabon", "gambia", "georgia", "germany", "ghana", "greece",
        "grenada", "guatemala", "guinea", "guyana", "haiti", "honduras", "hungary",
        "iceland", "india", "indonesia", "iran", "iraq", "ireland", "israel", "italy",
        "jamaica", "japan", "jordan", "kazakhstan", "kenya", "kiribati", "kosovo",
        "kuwait", "kyrgyzstan", "laos", "latvia", "lebanon", "lesotho", "liberia",
        "libya", "liechtenstein", "lithuania", "luwembourg", "luxembourg", "macedonia",
        "madagascar", "malawi", "malaysia", "maldives", "mali", "malta", "mauritania",
        "mauritius", "mexico", "micronesia", "moldova", "monaco", "mongolia", "montenegro",
        "morocco", "mozambique", "myanmar", "namibia", "nauru", "nepal", "netherlands",
        "newzealand", "nicaragua", "niger", "nigeria", "northkorea", "norway", "oman",
        "pakistan", "palau", "palestine", "panama", "paraguay", "peru", "philippines",
        "poland", "portugal", "qatar", "romania", "russia", "rwanda", "samoa", "senegal",
        "serbia", "seychelles", "singapore", "slovakia", "slovenia", "solomon", "somalia",
        "spain", "srilanka", "sudan", "suriname", "swaziland", "sweden", "switzerland",
        "syria", "taiwan", "tajikistan", "tanzania", "thailand", "togo", "tonga",
        "trinidad", "tunisia", "turkey", "turkmenistan", "tuvalu", "uganda", "ukraine",
        "uruguay", "uzbekistan", "vanuatu", "vatican", "venezuela", "vietnam", "yemen",
        "zambia", "zimbabwe"));
        List<Character> expResult = new ArrayList<>();
        expResult.addAll(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
        assertEquals(expResult, Assignment5.findAlphabet(dict));
    }
    
    @Test
    public void testFindAlphabetMini() throws Exception {
        System.out.println("Mini");
        ArrayList<String> dict = new ArrayList<>();
        dict.addAll(Arrays.asList("art", "rat", "cat", "car"));
        List<Character> expResult = new ArrayList<>();
        expResult.addAll(Arrays.asList('t', 'a', 'r', 'c'));
        assertEquals(expResult, Assignment5.findAlphabet(dict));
    }
    
    @Test (expected = Exception.class)
    public void testFindAlphabetContradict() throws Exception {
        System.out.println("Contradictory dict");
        ArrayList<String> dict = new ArrayList<>();
        dict.addAll(Arrays.asList("art", "rat", "cat", "crt", "car"));
        Assignment5.findAlphabet(dict);
        fail();
    }
 
    @Test
    public void testFindAlphabetEmpty() throws Exception {
        System.out.println("Empty dict");
        ArrayList<String> dict = new ArrayList<>();
        List<Character> expResult = new ArrayList<>();
        assertEquals(expResult, Assignment5.findAlphabet(dict));
    }
    
    @Test
    public void testFindAlphabetNull() throws Exception {
        System.out.println("Null dict");
        ArrayList<String> dict = null;
        List<Character> expResult = new ArrayList<>();
        assertEquals(expResult, Assignment5.findAlphabet(dict));
    }
}
