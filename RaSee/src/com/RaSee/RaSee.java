package com.RaSee;

import java.io.IOException;
import java.util.Locale;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

/**
 * This class contains the methods of RaSee application
 * 
 * @author Sri Harsha Hosur
 *
 */
public class RaSee {

  /**
   * Below method is to make RaSee speak
   * 
   * @param speech text to speak
   */
  public static void RaSeeSpeech(String speech) {
    System.setProperty("freetts.voices",
        "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
    try {
      Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
      Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
      synthesizer.allocate();
      synthesizer.resume();
      System.out.println(speech);
      synthesizer.speakPlainText(speech, null);
      synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
      // synthesizer.deallocate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException, EngineException, EngineStateError {
    Configuration configuration = new Configuration();
    configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
    configuration.setDictionaryPath("file:///E:\\R4U\\RaSee\\Files\\8754.dic");
    configuration.setLanguageModelPath("file:///E:\\R4U\\RaSee\\Files\\8754.lm");
    LiveSpeechRecognizer recognize = new LiveSpeechRecognizer(configuration);
    recognize.startRecognition(true);
    SpeechResult result;
    while ((result = recognize.getResult()) != null) {
      String command = result.getHypothesis();
      if (command.equalsIgnoreCase("Hello")) {
        RaSeeSpeech("Hello. RaSee here");
      } else if (command.equalsIgnoreCase("Open notepad")) {
        RaSeeSpeech("Can you please open ? I am busy");
      } else if (command.equalsIgnoreCase("Howdy")) {
        RaSeeSpeech("I am fine thank you. How are you ?");
      } else if (command.equalsIgnoreCase("I am fine thank you")) {
        RaSeeSpeech("What can I do for you");
      } else if (command.equalsIgnoreCase("Bye")) {
        RaSeeSpeech("Tata");
        break;
      }
    }
  }
}
