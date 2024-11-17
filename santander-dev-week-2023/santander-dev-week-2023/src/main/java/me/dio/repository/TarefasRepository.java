package me.dio.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import me.dio.model.Tarefa;


@Repository

public interface TarefasRepository extends JpaRepository<Tarefa, Long> {
    
}
