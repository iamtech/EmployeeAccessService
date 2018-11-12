package com.client.app.service.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.client.app.service.bean.EmployeeBean;
import com.client.app.service.config.UserRibbonConfig;

@RibbonClient(name = "employee-microservice-dataset", configuration = UserRibbonConfig.class)
public class EmployeeRepositoryAccessImpl implements EmployeeRepositoryAccess{

	@LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
    @Autowired
    RestTemplate restTemplate;
	
	protected String serviceUrl;
	
	public EmployeeRepositoryAccessImpl(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}
	
	@Override
	public List<EmployeeBean> getAllProfiles() {
		EmployeeBean[] profiles = restTemplate.getForObject(serviceUrl+"/profiles", EmployeeBean[].class);
		return Arrays.asList(profiles);
	}

	@Override
	public EmployeeBean getProfile(String userId) {
		return restTemplate.getForObject(serviceUrl + "/profiles/{id}",
				EmployeeBean.class, userId);
	}
}
