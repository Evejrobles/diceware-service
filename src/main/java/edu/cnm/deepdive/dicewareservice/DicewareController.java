package edu.cnm.deepdive.dicewareservice;

import edu.cnm.deepdive.cryptography.ArtifactGenerator;
import edu.cnm.deepdive.cryptography.PassphraseGenerator;
import edu.cnm.deepdive.cryptography.WordSource;
import java.util.Random;
import javax.activation.MimeType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DicewareController {

  private ArtifactGenerator generater;
  public DicewareController(WordSource source, Random rng) {
    generater = new PassphraseGenerator(source, rng);

  }

  @GetMapping(path ="/diceware", produces = "text/plain")
  public String get(@RequestParam(name = "length", defaultValue = "6") int length) {
    return generater.generate(length);
  }
  @GetMapping(path = "/diceware", produces = "application/json")
  public String[] getJson(@RequestParam(name = "length", defaultValue = "6") int length) {
    return get(length).split("\\s+");
  }

}
