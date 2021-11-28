package com.warehouse.inventory.api.counterparty;

import com.warehouse.inventory.extensions.mappers.CounterpartyMapper;
import com.warehouse.inventory.infrastructure.database.repositories.CounterpartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/counterparty/")
@RestController
@RequiredArgsConstructor
public class CounterpartyEndpoint {
    private final CounterpartyRepository repository;
    private final CounterpartyMapper mapper;

    @GetMapping("/all")
    @ResponseBody
    @Transactional
    public List<CounterpartyResponseItem> getAllCounterparties() {
        return repository.findAll()
            .stream()
            .map(mapper::toResponseItem)
            .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    @ResponseBody
    @Transactional
    public CounterpartyResponseItem getAllCounterparties(@PathVariable Long id) {
        var entity = repository.getCounterpartyEntityById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return mapper.toResponseItem(entity);
    }

    @PostMapping()
    @Transactional
    public void createCounterparty(@RequestBody CounterpartyRequest request) {
        var entity = mapper.toEntity(request);
        repository.save(entity);
    }

    @PutMapping("{id}")
    @Transactional
    public void editCounterparty(@PathVariable Long id, @RequestBody CounterpartyRequest request) {
        var entity = repository.getCounterpartyEntityById(id);
        entity.setName(request.getName());
        repository.save(entity);
    }

    @DeleteMapping("{id}")
    @Transactional
    public void deleteCounterparty(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
