/*
 * Copyright 2019 the original author or authors.
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
 */

package org.gradle.api.reporting.internal

import org.gradle.api.Task
import org.gradle.test.fixtures.file.TestNameTestDirectoryProvider
import org.gradle.util.TestUtil
import org.junit.Rule
import spock.lang.Specification

class TaskGeneratedSingleFileReportTest extends Specification {
    @Rule
    TestNameTestDirectoryProvider tmpDir = new TestNameTestDirectoryProvider(getClass())

    def "can attach a convention mapping for output file location"() {
        def report = TestUtil.objectFactory(tmpDir.testDirectory).newInstance(TaskGeneratedSingleFileReport, "report", Stub(Task))
        def output = new File("report.txt")
        def resolvedOutput = tmpDir.file("report.txt")

        expect:
        report.destination == null
        !report.outputLocation.isPresent()

        report.conventionMapping.map("destination") { output }

        report.destination == output
        report.outputLocation.asFile.get() == resolvedOutput
    }
}
