package ru.geekbrains.cashFlowManager.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Базовый интерфейс для репозитория.
 *
 * @param <T> элемент репозитория.
 */
public interface BaseRepository<T, I> {
    String add(T item);

    Boolean edit(T newItem);

    Optional<T> get(I id);

    List<T> findAll();

    String delete(I id);
}
