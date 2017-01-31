package com.easyinnova;

import com.easyinnova.implementation_checker.ImplementationCheckerValidator;
import com.easyinnova.implementation_checker.ValidationResult;
import com.easyinnova.implementation_checker.rules.RuleResult;
import com.easyinnova.tiff.reader.TiffReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Adria Llorens on 22/12/2016.
 */
public class Main {
  public static void main(String[] args) {
    // Arguments
    String path;
    String iso = "TIFF_Baseline_Core_6_0";
    if (args.length == 1) {
      path = args[0];
    } else if (args.length == 2) {
      path = args[0];
      iso = args[1];
    } else {
      help();
      return;
    }

    if (!new File(path).exists() || !validISO(iso)) {
      help();
      return;
    }

    // All OK
    try {
      TiffReader tr = new TiffReader();
      int readerRes = tr.readFile(path, false);
      switch (readerRes) {
        case -1:
          System.out.println("File '" + path + "' does not exist");
          break;
        case -2:
          System.out.println("IO Exception in file '" + path + "'");
          break;
        case 0:
          ImplementationCheckerValidator implementationCheckerValidator = new ImplementationCheckerValidator();
          List<String> isos = new ArrayList<>();
          isos.add(iso);
          Map<String, ValidationResult> result = implementationCheckerValidator.check(tr, isos);
          for (String key : result.keySet()) {
            prettyPrint(result.get(key));
          }
          break;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static boolean validISO(String iso) {
    if (iso.equals("TIFF_Baseline_Core_6_0")) return true;
    if (iso.equals("TIFF_Baseline_Extended_6_0")) return true;
    if (iso.equals("TIFF_EP")) return true;
    if (iso.equals("TiffITProfileChecker")) return true;
    if (iso.equals("TiffITP1ProfileChecker")) return true;
    if (iso.equals("TiffITP2ProfileChecker")) return true;
    if (iso.equals("TIAProfileChecker")) return true;
    return false;
  }

  public static void prettyPrint(ValidationResult validation) {
    printResults("ERROR", validation.getErrors());
    printResults("Warning", validation.getWarnings(false));
    printResults("Info", validation.getInfos());
  }

  private static void printResults(String type, List<RuleResult> results) {
    for (RuleResult result : results) {
      System.out.println(type);
      System.out.println("  Rule ID:     " + result.getRule().getId());
      System.out.println("  Location:    " + result.getLocation());
      System.out.println("  Description: " + result.getDescription());
      System.out.println("  Rule Desc:   " + result.getRule().getDescription().getValue());
      System.out.println("  Reference:   " + result.getReference());
      System.out.println();
    }
  }

  private static void help() {
    System.out.println("Input params error.");
    System.out.println("  First argument must be the file.");
    System.out.println("  Second argument can be the ISO to check. If empty, it checks Baseline TIFF 6.0.");
    System.out.println("  Available ISOs: 'TIFF_Baseline_Core_6_0', 'TIFF_Baseline_Extended_6_0', 'TIFF_EP', 'TiffITProfileChecker', 'TiffITP1ProfileChecker', 'TiffITP2ProfileChecker', 'TIAProfileChecker'");
  }

}
