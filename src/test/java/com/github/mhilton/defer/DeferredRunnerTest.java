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

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class DeferredRunnerTest {
    private Queue<Integer> queue;

    @Before
    public void setUp() {
        queue = new LinkedList<Integer>();
    }

    @Test
    public void testDefer() {
        DeferredRunner defer = new DeferredRunner();
        defer.defer(runnable(1));
        defer.defer(runnable(2));
        defer.defer(runnable(3));
        defer.run();
        assertEquals(3, (long) queue.poll());
        assertEquals(2, (long) queue.poll());
        assertEquals(1, (long) queue.poll());
    }

    private Runnable runnable(final int i) {
        return new Runnable() {
            @Override
            public void run() {
                queue.add(i);
            }
        };
    }
}
