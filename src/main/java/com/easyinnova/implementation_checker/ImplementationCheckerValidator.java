/**
 * <h1>ImplementationCheckerValidator.java</h1> <p> This program is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version; or,
 * at your choice, under the terms of the Mozilla Public License, v. 2.0. SPDX GPL-3.0+ or MPL-2.0+.
 * </p> <p> This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License and the Mozilla Public License for more details. </p>
 * <p> You should have received a copy of the GNU General Public License and the Mozilla Public
 * License along with this program. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>
 * and at <a href="http://mozilla.org/MPL/2.0">http://mozilla.org/MPL/2.0</a> . </p> <p> NB: for the
 * © statement, include Easy Innova SL or other company/Person contributing the code. </p> <p> ©
 * 2015 Easy Innova, SL </p>
 */

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
