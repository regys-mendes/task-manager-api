package com.regysmendes.taskmanger;

import com.regysmendes.taskmanger.entities.Task;
import com.regysmendes.taskmanger.entities.TaskPriority;
import com.regysmendes.taskmanger.entities.TaskStatus;
import com.regysmendes.taskmanger.services.ObjectNotFoundException;
import com.regysmendes.taskmanger.services.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskmanagerApplicationTests {

	@Autowired
	private TaskService service;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldThrowExceptionWhenIdNotFound() {
		assertThrows(ObjectNotFoundException.class, () -> {
			service.findById(999L);
		});
	}

	@Test
	void shouldInsertAndFindTask() {
		Task task = new Task(null, "Teste", "Descrição", TaskPriority.ALTA, TaskStatus.PENDENTE);
		Task saved = service.insert(task);

		assertNotNull(saved.getId());
		assertEquals("Teste", saved.getTitle());
		assertEquals(TaskStatus.PENDENTE, saved.getStatus());
	}
}