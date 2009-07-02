/*
 * Copyright 2002-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Also licensed under CPL http://junit.sourceforge.net/cpl-v10.html
 */



package org.junit.experimental;

import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.Failure;
import org.junit.runner.Result;
import org.junit.runner.Description;

/*
 * @author Kristian Rosenvold, kristianAzeniorD0Tno
 */
public class ClassReport extends RunListener {
    private final RunListener realtarget;
    private final Result resultForThisClass = new Result();
    private final RunListener classRunListener = resultForThisClass.createListener();

    public ClassReport(RunListener realtarget) {
        this.realtarget = realtarget;
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        realtarget.testRunFinished( result);
        classRunListener.testRunFinished( result);
    }

    @Override
    public void testStarted(Description description) throws Exception {
        realtarget.testStarted(description);
        classRunListener.testStarted( description);
    }

    @Override
    public void testFinished(Description description) throws Exception {
        realtarget.testFinished(description);
        classRunListener.testFinished( description);
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        realtarget.testFailure(failure);
        classRunListener.testFailure( failure);
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        realtarget.testAssumptionFailure(failure);
        classRunListener.testAssumptionFailure( failure);
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        realtarget.testIgnored(description);
        classRunListener.testIgnored( description);
    }

    public void testRunFinished() throws Exception {
        realtarget.testRunFinished(resultForThisClass);
    }

    Result getResultForThisClass() {
        return resultForThisClass;
    }
}
