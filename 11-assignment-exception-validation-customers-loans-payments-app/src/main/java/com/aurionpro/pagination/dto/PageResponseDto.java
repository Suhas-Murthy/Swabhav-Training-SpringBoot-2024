package com.aurionpro.pagination.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PageResponseDto<T> {
	private long totalElements;
	private int totalPages;
	private int size;
	private List<T> content;
	private boolean isLastPage;

}