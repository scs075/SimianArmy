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
package com.netflix.simianarmy.chaos;

import com.netflix.simianarmy.FeatureNotEnabledException;
import com.netflix.simianarmy.InstanceGroupNotFoundException;
import com.netflix.simianarmy.Monkey;
import com.netflix.simianarmy.MonkeyConfiguration;
import com.netflix.simianarmy.basic.chaos.BasicChaosInstanceSelector;

/**
 * The Class ChaosMonkey.
 */
public abstract class ChaosMonkey extends Monkey {
	
	private static final String MONKEY_NAME = "Chaos_Monkey";

    /**
     * The Interface Context.
     */
    public interface Context extends Monkey.Context {

        /**
         * Configuration.
         *
         * @return the monkey configuration
         */
        MonkeyConfiguration configuration();

//        /**
//         * Chaos crawler.
//         *
//         * @return the chaos crawler
//         */
        //We don't need to crawl
//        ChaosCrawler chaosCrawler();

        /**
         * Chaos instance selector.
         *
         * @return the chaos instance selector
         */
        BasicChaosInstanceSelector chaosInstanceSelector();

		InstanceGroup getInstanceGRoup();

        /**
         * Chaos email notifier.
         *
         * @return the chaos email notifier
         */
       //TODO Add notification back later 
        //ChaosEmailNotifier chaosEmailNotifier();
    }

    /** The context. */
    private final Context ctx;

    /**
     * Instantiates a new chaos monkey.
     *
     * @param ctx
     *            the context.
     */
    public ChaosMonkey(Context ctx) {
        super(ctx, MONKEY_NAME);
        this.ctx = ctx;
    }



    /** {@inheritDoc} */
    @Override
    public Context context() {
        return ctx;
    }

    /** {@inheritDoc} */
    @Override
    public abstract void doMonkeyBusiness();

     /**
     * Terminates one instance right away from an instance group when there are available instances.
     * @param type
     *            the type of the instance group
     * @param name
     *            the name of the instance group
     * @return the termination event
     * @throws FeatureNotEnabledException
     * @throws InstanceGroupNotFoundException
     */
    public abstract void terminateNow(String type, String name, ChaosType chaosType)
            throws FeatureNotEnabledException, InstanceGroupNotFoundException;

}
