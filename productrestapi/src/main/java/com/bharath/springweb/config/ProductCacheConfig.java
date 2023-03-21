package com.bharath.springweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;

@Configuration
public class ProductCacheConfig {

	@Bean
	public Config cacheConfig() {
		return new Config().setInstanceName("hazel-instance")
				.addMapConfig(new MapConfig().setName("Produce-cache").setTimeToLiveSeconds(3000));
	}
}
