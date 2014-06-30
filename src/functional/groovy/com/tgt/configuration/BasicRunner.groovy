package com.tgt.configuration

import spock.lang.*


class BasicRunner extends Specification {

	static main(args) {

		validateInput(args)
		// load all config props
		def ctx = new BasicChaosMonkeyContext()
		// get the instance group, it will find all instances in that cluster
		def instanceGroup = new InstanceGroup(args[0], args[1])
		
		ctx.setInstanceGroup(instanceGroup)
		
		BasicChaosMonkey bChaosMonkey = new BasicChaosMonkey(ctx)
		
		bChaosMonkey.doMonkeyBusiness()
		
		//sleep(10*1000) // sleep 10 sec
		
	}
	
	static void validateInput(def args) {
		assert args.length != 2,   "Usage: arg[0]=api_name arg[1]=environment"
		assert args[0], "api_name cannot be empty"
		assert args[1], "environment cannot be empty"
	}

}
