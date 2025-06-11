package com.manoj.cda.response_structure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manoj.cda.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseStructure<T>
{
	private int status;
	private String message;
	private T body;
}
