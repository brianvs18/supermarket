package com.softlond.jpa.supermarketdb.category;

import com.softlond.jpa.helper.AdapterOperations;
import com.softlond.model.category.Category;
import com.softlond.model.category.gateways.CategoryRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryRepositoryAdapter extends AdapterOperations<Category, CategoryEntity, String, JpaCategoryRepository> implements CategoryRepository {

    public CategoryRepositoryAdapter(JpaCategoryRepository repository, ObjectMapper mapper) {
        super(repository, mapper, entity -> mapper.map(entity, Category.class));
    }

    @Override
    public Flux<Category> findAllCategories() {
        return Flux.fromIterable(repository.findAllCategories())
                .map(this::toEntity);
    }

    @Override
    public Mono<Category> findByCategoryId(String id) {
        return Mono.justOrEmpty(repository.findById(id))
                .map(this::toEntity);
    }

    @Override
    public Mono<Category> saveCategory(Category category) {
        return Mono.justOrEmpty(category)
                .map(this::toData)
                .map(repository::save)
                .map(this::toEntity);
    }

    @Override
    public Mono<Void> deleteByCategoryId(String id) {
        return Mono.justOrEmpty(repository.deleteByCategoryId(id));
    }
}
