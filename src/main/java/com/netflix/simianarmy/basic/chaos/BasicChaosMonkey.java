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
package com.netflix.simianarmy.basic.chaos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.simianarmy.FeatureNotEnabledException;
import com.netflix.simianarmy.InstanceGroupNotFoundException;
import com.netflix.simianarmy.MonkeyConfiguration;
import com.netflix.simianarmy.chaos.ChaosMonkey;
import com.netflix.simianarmy.chaos.ChaosType;

/**
 * The Class BasicChaosMonkey.
 */public class BasicChaosMonkey extends ChaosMonkey {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BasicChaosMonkey.class);

    /** The Constant NS. */
    private static final String NS = "simianarmy.chaos.";

    /** The cfg. */
    private final MonkeyConfiguration cfg;

    /**
     * Instantiates a new basic chaos monkey.
     * @param ctx
     *            the ctx
     */
    public BasicChaosMonkey(ChaosMonkey.Context ctx) {
        super(ctx);
        this.cfg = ctx.configuration();
   }

    /** {@inheritDoc} */
    @Override
    public void doMonkeyBusiness() {
        cfg.reload();
        if (!isChaosMonkeyEnabled()) {
            return;
        }
//        InstanceGroup group = context().getInstanceGRoup()
//        		
//        for (InstanceGroup group : context().chaosCrawler().groups()) {
//            if (isGroupEnabled(group)) {
//                if (isMaxTerminationCountExceeded(group)) {
//                    continue;
//                }
//                double prob = getEffectiveProbability(group);
//                Collection<String> instances = context().chaosInstanceSelector().select(group, prob / runsPerDay);
//                for (String inst : instances) {
//                    ChaosType chaosType = pickChaosType(context().cloudClient(), inst);
//                    if (chaosType == null) {
//                        // This is surprising ... normally we can always just terminate it
//                        LOGGER.warn("No chaos type was applicable to the instance: {}", inst);
//                        continue;
//                    }
//                    terminateInstance(group, inst, chaosType);
//                }
//            }
//        }
    }

//    private ChaosType pickChaosType(CloudClient cloudClient, String instanceId) {
//        Random random = new Random();
//
//        SshConfig sshConfig = new SshConfig(cfg);
//        ChaosInstance instance = new ChaosInstance(cloudClient, instanceId, sshConfig);
//
//        List<ChaosType> applicable = Lists.newArrayList();
//        for (ChaosType chaosType : allChaosTypes) {
//            if (chaosType.isEnabled() && chaosType.canApply(instance)) {
//                applicable.add(chaosType);
//            }
//        }
//
//        if (applicable.isEmpty()) {
//            return null;
//        }
//
//        int index = random.nextInt(applicable.size());
//        return applicable.get(index);
//    }

 
    /**
     * Handle termination error. This has been abstracted so subclasses can decide to continue causing chaos if desired.
     *
     * @param instance
     *            the instance
     * @param e
     *            the exception
     */
    protected void handleTerminationError(String instance, Throwable e) {
        LOGGER.error("failed to terminate instance " + instance, e);
        throw new RuntimeException("failed to terminate instance " + instance, e);
    }


    private boolean isChaosMonkeyEnabled() {
        String prop = NS + "enabled";
        if (cfg.getBoolOrElse(prop, true)) {
            return true;
        }
        LOGGER.info("ChaosMonkey disabled, set {}=true", prop);
        return false;
    }

	@Override
	public void terminateNow(String type, String name, ChaosType chaosType)
			throws FeatureNotEnabledException, InstanceGroupNotFoundException {
		// TODO Auto-generated method stub
		
	}

//    private InstanceGroup findInstanceGroup(String type, String name) {
//        // Calling context().chaosCrawler().groups(name) causes a new crawl to get
//        // the up to date information for the group name.
//        for (InstanceGroup group : context().chaosCrawler().groups(name)) {
//            if (group.type().toString().equals(type) && group.name().equals(name)) {
//                return group;
//            }
//        }
//        LOGGER.warn("Failed to find instance group for type {} and name {}", type, name);
//        return null;
//    }

//    private Event terminateInstance(InstanceGroup group, String inst, ChaosType chaosType) {
////        Validate.notNull(group);
////        Validate.notEmpty(inst);
////        String prop = NS + "leashed";
////        if (cfg.getBoolOrElse(prop, true)) {
////            LOGGER.info("leashed ChaosMonkey prevented from killing {} from group {} [{}], set {}=false",
////                    new Object[]{inst, group.name(), "", prop});
////            reportEventForSummary(EventTypes.CHAOS_TERMINATION_SKIPPED, group, inst);
////            return null;
////        } else {
////            try {
////                Event evt = recordTermination(group, inst, chaosType);
////                sendTerminationNotification(group, inst, chaosType);
////                SshConfig sshConfig = new SshConfig(cfg);
////                ChaosInstance chaosInstance = new ChaosInstance(context().cloudClient(), inst, sshConfig);
////                chaosType.apply(chaosInstance);
////                LOGGER.info("Terminated {} from group {} [{}] with {}",
////                        new Object[]{inst, group.name(), group.type(), chaosType.getKey() });
////                reportEventForSummary(EventTypes.CHAOS_TERMINATION, group, inst);
////                return evt;
////            } catch (NotFoundException e) {
////                LOGGER.warn("Failed to terminate " + inst + ", it does not exist. Perhaps it was already terminated");
////                reportEventForSummary(EventTypes.CHAOS_TERMINATION_SKIPPED, group, inst);
////                return null;
////            } catch (Exception e) {
////                handleTerminationError(inst, e);
////                reportEventForSummary(EventTypes.CHAOS_TERMINATION_SKIPPED, group, inst);
////                return null;
////            }
////        }
//    }



}