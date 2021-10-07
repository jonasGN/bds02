package com.devsuperior.bds02.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;

	@Transactional(readOnly = true)
	public List<EventDTO> findAll() {
		final List<Event> result = repository.findAll();
		final Stream<EventDTO> stream = result.stream().map(e -> new EventDTO(e));

		return stream.collect(Collectors.toList());
	}

	@Transactional
	public EventDTO update(Long id, EventDTO event) {
		try {
			Event entity = repository.getOne(id);
			dtoToEntity(event, entity);
			entity = repository.save(entity);

			return new EventDTO(entity);
		} catch (Exception e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void dtoToEntity(EventDTO dto, Event entity) {
		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setUrl(dto.getUrl());

		final City city = new City();
		city.setId(dto.getCityId());
		entity.setCity(city);
	}
}
