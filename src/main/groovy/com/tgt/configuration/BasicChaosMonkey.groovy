
package com.tgt.configuration;

import groovy.util.logging.Log

/**
 * The Class BasicChaosMonkey.
 */
@Log
class BasicChaosMonkey {

	/** The Constant NS. */
	static final String NS = "simianarmy.chaos.";

	static final long WAIT_TIME = 5*1000;

	/** The cfg. */
	final BasicChaosMonkeyContext ctx;


	BasicChaosMonkey(BasicChaosMonkeyContext ctx) {
		this.ctx = ctx
	}

	void doMonkeyBusiness() {
		ctx.config.reload();
		if (!isChaosMonkeyEnabled()) {
			return;
		}
		InstanceGroup group = ctx.getInstanceGroup()
		for(Instance instance: group.instances()) {
			getHealthOfGroupSnapshot(group); //email
			getLoadBalanceSnapshot(group); //email
			instance.down(); // error handling
			waitAWhile();
			instance.up();
			waitAWhile();
		}
		// after the last iteration
		getHealthOfGroupSnapshot(group); //email
		getLoadBalanceSnapshot(group); //email
	}

	private void waitAWhile() {
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getLoadBalanceSnapshot(InstanceGroup group) {
		// TODO Auto-generated method stub

	}

	private void getHealthOfGroupSnapshot(InstanceGroup group) {
		// TODO Auto-generated method stub

	}

	/**
	 * Handle termination error. This has been abstracted so subclasses can decide to continue causing chaos if desired.
	 *
	 * @param instance
	 *            the instance
	 * @param e
	 *            the exception
	 */
	protected void handleTerminationError(String instance, Throwable e) {
		log.error("failed to terminate instance " + instance, e);
		throw new RuntimeException("failed to terminate instance " + instance, e);
	}


	private boolean isChaosMonkeyEnabled() {
		String prop = NS + "enabled";
		if (cfg.getBoolOrElse(prop, true)) {
			return true;
		}
		log.info("ChaosMonkey disabled, set {}=true", prop);
		return false;
	}


	//    private InstanceGroup findInstanceGroup(String type, String name) {
	//        // Calling context().chaosCrawler().groups(name) causes a new crawl to get
	//        // the up to date information for the group name.
	//        for (InstanceGroup group : context().chaosCrawler().groups(name)) {
	//            if (group.type().toString().equals(type) && group.name().equals(name)) {
	//                return group;
	//            }
	//        }
	//        log.warn("Failed to find instance group for type {} and name {}", type, name);
	//        return null;
	//    }

	//    private Event terminateInstance(InstanceGroup group, String inst, ChaosType chaosType) {
	////        Validate.notNull(group);
	////        Validate.notEmpty(inst);
	////        String prop = NS + "leashed";
	////        if (cfg.getBoolOrElse(prop, true)) {
	////            log.info("leashed ChaosMonkey prevented from killing {} from group {} [{}], set {}=false",
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
	////                log.info("Terminated {} from group {} [{}] with {}",
	////                        new Object[]{inst, group.name(), group.type(), chaosType.getKey() });
	////                reportEventForSummary(EventTypes.CHAOS_TERMINATION, group, inst);
	////                return evt;
	////            } catch (NotFoundException e) {
	////                log.warn("Failed to terminate " + inst + ", it does not exist. Perhaps it was already terminated");
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