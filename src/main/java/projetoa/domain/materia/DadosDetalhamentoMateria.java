package projetoa.domain.materia;

import projetoa.domain.curso.Curso;

import java.util.List;

public record DadosDetalhamentoMateria(
        String nome
) {
    public DadosDetalhamentoMateria(Materia materia){
        this(materia.getNome());
    }
}
