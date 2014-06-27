/*
 *
 *  Copyright 2012 Netflix, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */
package com.netflix.simianarmy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The abstract Monkey class, it provides a minimal interface from which all monkeys must be derived.
 */
public abstract class Monkey {
	
	private String name;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Monkey.class);

    /**
     * The Interface Context.
     */
    public interface Context {

    	// Scheduler and all are future feature
        /**
         * Scheduler.
         *
         * @return the monkey scheduler
         */
//        MonkeyScheduler scheduler();

        /**
         * Calendar.
         *
         * @return the monkey calendar
         */
//        MonkeyCalendar calendar();

        /**
         * Recorder.
         *
         * @return the monkey recorder
         */
//        MonkeyRecorder recorder();

        /**
         * Add a event to the summary report. The ChaosMonkey uses this to print a summary after the chaos run is
         * complete.
         *
         * @param evt
         *            The Event to be reported
         */
//        void reportEvent(Event evt);

        /**
         * Used to clear the event summary on the start of a chaos run.
         */
//        void resetEventReport();

        /**
         * Configuration.
         *
         * @return the monkey configuration
         */
        MonkeyConfiguration configuration();
    }

    /** The context. */
    private final Context ctx;

    /**
     * Instantiates a new monkey.
     *
     * @param ctx
     *            the context
     */
    public Monkey(Context ctx, String name) {
        this.ctx = ctx;
        this.name = name;
    }

    /**
     * Do monkey business.
     */
    public abstract void doMonkeyBusiness();

    /**
     * Context.
     *
     * @return the context
     */
    public Context context() {
        return ctx;
    }

 	public String getName() {
		return name;
	}
}
