package com.example.tarefa_api.domain;

import org.springframework.stereotype.Service;

import java.util.List;

import jakarta.transaction.Transactional;

@Service
public class TarefaService {

	private final TarefaRepository repo;
	public TarefaService(TarefaRepository repo) {
		this.repo = repo;
	}
	public List<Tarefa> listarTodas(){
		return repo.findAll();
		
	}
	public Tarefa buscarPorId(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
	}
	@Transactional
	public Tarefa criar(Tarefa tarefa) {
		return repo.save(tarefa);
	}
	@Transactional
	public Tarefa atualizar(Long id, Tarefa tarefaAtualizada) {

        Tarefa tarefa = buscarPorId(id);

        tarefa.setNome(tarefaAtualizada.getNome());

        tarefa.setDataEntrega(tarefaAtualizada.getDataEntrega());

        tarefa.setResponsavel(tarefaAtualizada.getResponsavel());

        return repo.save(tarefa);

    }
    @Transactional

    public void remover(Long id) {

        Tarefa tarefa = buscarPorId(id);
        repo.delete(tarefa);
    }
}

