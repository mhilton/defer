// Copyright 2012 Martin Hilton
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.github.mhilton.defer;

import java.util.Stack;

public class DeferredRunner implements Runnable {
    private final Stack<Runnable> stack = new Stack<Runnable>();

    /** Runs any objects that have previously been deferred.
     * Objects are run in the reverse order in which they were deferred.
     *
     */
    @Override
    public void run() {
        while (!stack.empty()) {
            Runnable r = stack.pop();
            r.run();
        }
    }

    /** Defers a Runnable object to be run at a later time when this
     * DeferredRunner is run.
     *
     * @param r Task to be deferred and performed later
     */
    public void defer(Runnable r) {
        stack.push(r);
    }
}
