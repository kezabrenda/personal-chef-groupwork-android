/*
 * Copyright 2016 the original author or authors.
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

package org.gradle.logging;

/**
 * This type was accidentally leaked into the public API, please do not refer to it.
 */
@Deprecated
public interface StyledTextOutput extends Appendable {
    enum Style {
        Normal,
        Header,
        UserInput,
        Identifier,
        Description,
        ProgressStatus,
        Success,
        Failure,
        Info,
        Error
    }

    StyledTextOutput style(Style var1);

    StyledTextOutput append(char c);

    StyledTextOutput println(Object text);

    StyledTextOutput exception(Throwable throwable);

    StyledTextOutput append(CharSequence csq, int start, int end);

    StyledTextOutput withStyle(Style var1);

    StyledTextOutput println();

    StyledTextOutput append(CharSequence csq);

    StyledTextOutput text(Object text);

    StyledTextOutput formatln(String pattern, Object... args);

    StyledTextOutput format(String pattern, Object... args);
}
