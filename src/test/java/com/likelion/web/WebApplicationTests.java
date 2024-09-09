package com.likelion.web;

import java.lang.reflect.Array;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.likelion.web.model.Department;
import com.likelion.web.model.Physician;
import com.likelion.web.model.Procedure;
import com.likelion.web.repository.DepartmentRepository;
import com.likelion.web.repository.ProcedureRepository;
import com.likelion.web.service.HospitalService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Slf4j
class WebApplicationTests {
	@Autowired
	HospitalService hospitalService;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	ProcedureRepository procedureRepository;

	private static final int MAX_NUMBER = 20;
	private static Object lock = new Object();
	private static boolean isEvenTurn = true;

	@Test
	void contextLoads() {
		// ListNode listNode0 = new ListNode(0, new ListNode(1,
		// new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null))))));
		// System.err.println(middleNode(listNode0, listNode0).val);
	}

	@Test
	void testJPA() {
		System.err.println("\n" + "Start" + "\n");

		// log.info(departmentRepository.findAllById(List.of(1, 2, 3)).toString());
		// System.err.println(departmentRepository.findOne(Example.of(new
		// Department())).toString());
		procedureRepository.findAll().stream().forEach(System.err::println);

		System.err.println("\n" + "End" + "\n");
	}

	@Autowired
    private WebClient.Builder webClientBuilder;

	@Test
	void testWebclient() {
        WebClient webClient = webClientBuilder
                .baseUrl("https://venus.aih.com.vn")
                .build();

        String requestBody = "{"
                + "\"search\": \"\","
                + "\"publish\": 1,"
                + "\"hot\": 0,"
                + "\"rowperpage\": 2,"
                + "\"pageselected\": 1,"
                + "\"post_type\": [\"news\"],"
                + "\"lang\": \"vi-VN\","
                + "\"sort\": [\"post_datepublish DESC\"]"
                + "}";

        Mono<String> responseMono = webClient.post()
                .uri("/post/list")
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(e -> log.error("Error: ", e));

        StepVerifier.create(responseMono)
                .consumeNextWith(response -> {
                    log.info("Response: " + response);
                    // Add assertions here to verify the response
                })
                .verifyComplete();
	}

	public ListNode middleNode(ListNode head, ListNode node) {
		int count = 0;
		while (head != null) {
			++count;
			head = head.next;
		}

		for (int i = 0; i < count / 2; i++) {
			node = node.next;
		}
		return node;
	}

	class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	void thread() {
		Thread evenThread = new Thread(() -> {
			for (int i = 2; i <= MAX_NUMBER; i += 2) {
				synchronized (lock) {
					while (!isEvenTurn) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.err.println("Even Number from evenThread: " + i);
					isEvenTurn = false;
					lock.notify();
				}
			}
		});

		Thread oddThread = new Thread(() -> {
			for (int i = 1; i <= MAX_NUMBER; i += 2) {
				synchronized (lock) {
					while (isEvenTurn) {
						// try {
						// // lock.wait();
						// } catch (InterruptedException e) {
						// e.printStackTrace();
						// }
					}
					System.err.println("Odd Number from oddThread: " + i);
					isEvenTurn = true;
					lock.notify();
				}
			}
		});

		evenThread.start();
		oddThread.start();
	}
}
