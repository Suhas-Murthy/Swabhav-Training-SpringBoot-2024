package com.aurionpro.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CourseDto {
	private int courseId;
	private String name;
	private int duration;
	private double fees;
}
