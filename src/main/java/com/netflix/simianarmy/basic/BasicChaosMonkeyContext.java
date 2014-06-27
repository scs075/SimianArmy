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
package com.netflix.simianarmy.basic;

import com.netflix.simianarmy.MonkeyConfiguration;
import com.netflix.simianarmy.basic.chaos.BasicChaosInstanceSelector;
import com.netflix.simianarmy.chaos.ChaosMonkey;
import com.netflix.simianarmy.chaos.InstanceGroup;

/**
 * The Class BasicContext. This provide the basic context needed for the Chaos Monkey to run. It will configure
 * the Chaos Monkey based on a simianarmy.properties file and chaos.properties. The properties file can be
 * overridden with -Dsimianarmy.properties=/path/to/my.properties
 */
public class BasicChaosMonkeyContext extends BasicSimianArmyContext implements ChaosMonkey.Context {

    /** The crawler. */
    //private ChaosCrawler crawler;
	
	private InstanceGroup instanceGroup;

    /** The selector. */
    private BasicChaosInstanceSelector selector;

    /** The chaos email notifier. */
    //private ChaosEmailNotifier chaosEmailNotifier;

    /**
     * Instantiates a new basic context.
     */
    public BasicChaosMonkeyContext() {
        super("simianarmy.properties", "client.properties", "chaos.properties");
        //setChaosCrawler(new ASGChaosCrawler(awsClient()));
        setChaosInstanceSelector(new BasicChaosInstanceSelector());
        MonkeyConfiguration cfg = configuration();
        //setChaosEmailNotifier(new BasicChaosEmailNotifier(cfg, new AmazonSimpleEmailServiceClient(), null));
    }

    /** {@inheritDoc} */
//    @Override
//    public ChaosCrawler chaosCrawler() {
//        return crawler;
//    }

    /**
     * Sets the chaos crawler.
     *
     * @param chaosCrawler
     *            the new chaos crawler
     */
//    protected void setChaosCrawler(ChaosCrawler chaosCrawler) {
//        this.crawler = chaosCrawler;
//    }

    /** {@inheritDoc} */
    @Override
    public BasicChaosInstanceSelector chaosInstanceSelector() {
        return selector;
    }

    /**
     * Sets the chaos instance selector.
     *
     * @param chaosInstanceSelector
     *            the new chaos instance selector
     */
    protected void setChaosInstanceSelector(BasicChaosInstanceSelector chaosInstanceSelector) {
        this.selector = chaosInstanceSelector;
    }

	public InstanceGroup getInstanceGroup() {
		return instanceGroup;
	}

	public void setInstanceGroup(InstanceGroup instanceGroup) {
		this.instanceGroup = instanceGroup;
	}

	@Override
	public InstanceGroup getInstanceGRoup() {
		// TODO Auto-generated method stub
		return null;
	}

//    @Override
//    public ChaosEmailNotifier chaosEmailNotifier() {
//        return chaosEmailNotifier;
//    }

    /**
     * Sets the chaos email notifier.
     *
     * @param notifier
     *            the chaos email notifier
     */
//    protected void setChaosEmailNotifier(ChaosEmailNotifier notifier) {
//        this.chaosEmailNotifier = notifier;
//    }
}
