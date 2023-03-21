package com.bharath.springweb;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.bharath.springweb.entities.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductrestapiApplicationTests {

	@Value("${productrestapi.services.url}")
	private String baseURL;

	@Test
	public void testGetProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseURL + "1", Product.class);
		assertNotNull(product);
		assertEquals("iphone", product.getName());
	}

	@Test
	public void testCreatProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product newProduct = new Product();
		newProduct.setId(2);
		newProduct.setName("SAMSUNG");
		newProduct.setDescription("AWSOME");
		newProduct.setPrice(1500);

		Product product = restTemplate.postForObject("baseURL", newProduct, Product.class);
		assertNotNull(product);
		assertNotNull(product.getId());
		assertEquals("SAMSUNG", product.getName());
	}

	@Test
	public void testUpdateProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject("baseURL+1", Product.class);
		product.setPrice(2000);
		restTemplate.put("http://localhost:8080/productapi/products/", product);
		assertEquals(2000, product.getPrice());
	}

}
