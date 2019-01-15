package com.share1024;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.share1024.api.IOrderService;
import com.share1024.config.EnablePkActivityWebApi;
import com.share1024.model.Order;
import com.share1024.request.OrderRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootApplication
@RestController
@EnablePkActivityWebApi
public class DubboConsumer3Application {


	@Reference(version = "v1")
	private IOrderService orderService;

	@RequestMapping("/test")
	public Object test(){
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setOrderId("211212");
		orderRequest.setPrice(0F);
		return orderService.getOrderBy(orderRequest);
	}

	@RequestMapping("/echo")
	public Object echo(){
		EchoService echoService = (EchoService) orderService;
		return 	echoService.$echo("ok");
	}

	@RequestMapping("/rpc")
	public Object echoRpc(){
		RpcContext.getContext().setAttachment("remarks","this is a remarks");
		return orderService.getOrderById("211212");
	}


	@Reference(version = "v1",async = true)
	private IOrderService orderService2;

	@RequestMapping("/async")
	public Object async() throws ExecutionException, InterruptedException {
		Order order = orderService2.getOrderById("121121212");
		System.out.println(order);
		Future<Order> orderFuture = RpcContext.getContext().getFuture();
		return orderFuture.get();
	}

	public static void main(String[] args) {
		SpringApplication.run(DubboConsumer3Application.class, args);
	}
}
