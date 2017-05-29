/**
 * <h1>ValidationResult.java</h1> <p> This program is free software: you can redistribute it
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
 *
 * @author Víctor Muñoz Sola
 * @version 1.0
 * @since 23/7/2015
 */

package com.easyinnova.implementation_checker;

import com.easyinnova.implementation_checker.rules.RuleResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by easy on 29/03/2016.
 */
public class ValidationResult {
  private List<RuleResult> result;

  /**
   * Instantiates a new Validation result.
   */
  public ValidationResult() {
    result = new ArrayList<>();
  }

  /**
   * Add rule.
   *
   * @param ruleResult the rule result
   */
  public void add(RuleResult ruleResult) {
    result.add(ruleResult);
  }

  /**
   * Gets result.
   *
   * @return the result
   */
  public List<RuleResult> getResult() {
    return result;
  }

  /**
   * Gets passed.
   *
   * @return the passed rules
   */
  public List<RuleResult> getPassed() {
    List<RuleResult> passed = new ArrayList<>();
    for (RuleResult res : result) {
      if (res.ok()) {
        passed.add(res);
      }
    }
    return passed;
  }

  /**
   * Gets errors.
   *
   * @return the errors
   */
  public List<RuleResult> getErrors() {
    List<RuleResult> errors = new ArrayList<>();
    for (RuleResult res : result) {
      if (!res.ok() && !res.getWarning() && !res.getInfo()) {
        errors.add(res);
      }
    }
    return errors;
  }

  /**
   * Gets warnings.
   *
   * @return the warnings
   */
  public List<RuleResult> getWarnings(boolean infos) {
    List<RuleResult> errors = new ArrayList<>();
    for (RuleResult res : result) {
      if (!res.ok()) {
        if (res.getWarning()) errors.add(res);
        else if (infos && res.getInfo()) errors.add(res);
      }
    }
    return errors;
  }

  /**
   * Gets infos.
   *
   * @return the infos
   */
  public List<RuleResult> getInfos() {
    List<RuleResult> errors = new ArrayList<>();
    for (RuleResult res : result) {
      if (!res.ok() && res.getInfo()) {
        errors.add(res);
      }
    }
    return errors;
  }
}
