package com.easyinnova.implementation_checker;

import com.easyinnova.implementation_checker.model.TiffValidationObject;
import com.easyinnova.tiff.model.TiffDocument;
import com.easyinnova.tiff.reader.TiffReader;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Adria Llorens on 22/12/2016.
 */
public class ImplementationCheckerValidator {

  public ImplementationCheckerValidator() {

  }

  public Map<String, ValidationResult> check(TiffReader tr, List<String> isos) throws ParserConfigurationException, IOException, SAXException, JAXBException {
    Map<String, ValidationResult> validations = new HashMap<>();
    String content = getValidationXmlString(tr);
    for (String iso : isos) {
      Validator validation = new Validator();
      ValidationResult result = validation.validate(content, iso, false);
      validations.put(ImplementationCheckerLoader.getFileName(iso), result);
    }
    return validations;
  }

  public String getValidationXmlString(TiffReader tr) throws ParserConfigurationException, IOException, SAXException, JAXBException {
    TiffDocument td = tr.getModel();
    return getValidationXmlString(td);
  }

  public String getValidationXmlString(TiffDocument td) throws ParserConfigurationException, IOException, SAXException, JAXBException {
    TiffImplementationChecker tic = new TiffImplementationChecker();
    tic.setITFields(true);
    TiffValidationObject tiffValidation = tic.CreateValidationObject(td);
    return tiffValidation.getXml();
  }
}
