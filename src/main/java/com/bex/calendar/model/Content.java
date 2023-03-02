package com.bex.calendar.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import java.time.LocalDateTime;

public record Content(
	@Id Integer id,
	@NotBlank String title,
	@Column(value = "description")
	@NotBlank String desc,
	@Enumerated(EnumType.STRING) Status status,
	@Enumerated(EnumType.STRING) Type contentType,
	LocalDateTime dateCreated,
	LocalDateTime dateUpdated,
	String url) {

}
