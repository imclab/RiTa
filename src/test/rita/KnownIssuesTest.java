package test.rita;

import static test.QUnitStubs.equal;
import static test.QUnitStubs.ok;

import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

import rita.*;
import rita.support.*;

public class KnownIssuesTest implements Constants
{
  @Test
  public void testAnalyzeNums() // Cardinal numbers
  {
    // FROM RiStringTest
    Map features = new RiString("123.").analyze().features();
    ok(features);
    equal(features.get(PHONEMES), "w-ah-n-t-uw-th-r-iy");
    equal(features.get(SYLLABLES), "w-ah-n/t-uw/th-r-iy");
    equal(features.get(STRESSES), "0/0/0");
    
    features = new RiString("1 2 7").analyze().features();
    ok(features);
    System.out.println("Analyze: "+features);
    equal(features.get(PHONEMES), "w-ah-n t-uw s-eh-v-ax-n");
    equal(features.get(SYLLABLES), "w-ah-n t-uw s-eh-v/ax-n");
    equal(features.get(STRESSES), "1 1 1/0");

    /* in RITAJS
     * equal(features.get(SYLLABLES), "w-ah-n t-uw s-eh/v-ax-n");
     * equal(features.get(STRESSES), "0 0 1/0");*/    

    features = new RiString("123").analyze().features();
    ok(features);
    equal(features.get(PHONEMES), "w-ah-n-t-uw-th-r-iy");
    equal(features.get(SYLLABLES), "w-ah-n/t-uw/th-r-iy");
    equal(features.get(STRESSES), "0/0/0");
    
/*  In RITAJS  
 *  equal(features.get(PHONEMES), "w-ah-n-t-uw-th-r-iy");
    equal(features.get(SYLLABLES), "w-ah-n/t-uw/th-r-iy");
    equal(features.get(STRESSES), "0/0/0");
*/
  }
  
  @Test
  public void testRandomIterator() {

    JSONLexicon lexicon = JSONLexicon.getInstance();
    long ts = System.currentTimeMillis();
    lexicon.randomPosIterator("nns"); 
    System.out.println("randomPosIterator in "+(System.currentTimeMillis()-ts)+"ms");
    equal("TODO:","fix performance!");
  }
  
  @Test
  public void testSyllabify()   // ******
  {
    RiLexicon lex = new RiLexicon();
    String phones = RiString.syllabify(LetterToSound.getInstance().getPhones("dragging"));
    String phones2 = lex.lexImpl.getRawPhones("dragging");
    System.out.println(phones+" ?= "+phones2);
    equal(phones, phones2);
  }
  
}
