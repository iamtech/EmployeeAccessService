package com.client.app.service.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.client.app.service.bean.EmployeeBean;

public class EmployeeRepositoryAccessImpl implements EmployeeRepositoryAccess{

	
	protected RestTemplate restTemplate = new RestTemplate();
	
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
