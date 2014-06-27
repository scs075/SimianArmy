package com.netflix.simianarmy.basic.chaos

import com.netflix.simianarmy.MonkeyRunner
import com.netflix.simianarmy.basic.BasicChaosMonkeyContext

class BasicRunner {

	static main(args) {
		if (args.length != 2)
		{
			println "Usage ..."
			return
		}
		
		BasicChaosMonkeyContext ctx = new BasicChaosMonkeyContext()
		
		BasicChaosMonkey bChaosMonkey = new BasicChaosMonkey(ctx)
		
		MonkeyRunner runner = MonkeyRunner.getInstance()
		
		runner.start()
		
		sleep(10*1000) // sleep 10 sec
		
		runner.stop()
		

	}

}
