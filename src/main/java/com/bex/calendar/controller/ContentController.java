package com.bex.calendar.controller;

import com.bex.calendar.model.Content;
import com.bex.calendar.model.Status;
import com.bex.calendar.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {
	private final ContentRepository repository;

	@Autowired
	public ContentController(final ContentRepository repository) {
		this.repository = repository;
	}

	/**
	 * Make a request and find all the pieces of content in the system.
	 * @return The pieces of content.
	 */
	@GetMapping("")
	public List<Content> findAll() {
		List<Content> contents = new ArrayList<>();
		repository.findAll().forEach(contents::add);
		return contents;
	}

	//CRUD Create Read Update Delete - filter | paging | sorting

	@GetMapping("/{id}")
	public Content findById(@PathVariable Integer id) {
		return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	public void create(@RequestBody Content content) {
		repository.save(content);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{id}")
	public void update(@PathVariable Integer id, @RequestBody Content content) {
		if(!repository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
		}
		repository.save(content);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if(!repository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
		}
		repository.deleteById(id);
	}

	@GetMapping("/filter/{keyword}")
	public List<Content> findByTitle(@PathVariable String keyword) {
		return repository.findAllByTitleContains(keyword);
	}

	@GetMapping("/filter/status/{status}")
	public List<Content> findByStatus(@PathVariable Status status) {
		return repository.listByStatus(status);
	}
}
