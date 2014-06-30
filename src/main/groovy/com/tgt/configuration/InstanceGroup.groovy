package com.tgt.configuration;


/**
 * The Class BasicInstanceGroup.
 */
class InstanceGroup {

    /** The api name. */
    String apiName;

     /** The region. */
    String environment;


    /** The list of instances in this group. */
    List<Instance> instanceList = []
	
	public InstanceGroup(String apiName, String environment) {
        this.apiName = apiName;
        this.environment = environment;
		//TODO discover the list of instances in this cluster and initialize list
    }

    public String name() {
        return apiName;
    }

     public String environment() {
        return environment;
    }


    public List<Instance> instances() {
        return Collections.unmodifiableList(instanceList);
    }
	
	public refreshInstanceList() {
		//TODO discover the list of instances in this cluster and initialize list
		
	}

}
