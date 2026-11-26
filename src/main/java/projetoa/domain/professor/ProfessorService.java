package projetoa.domain.professor;

import projetoa.domain.aluno.Aluno;
import projetoa.domain.usuario.RoleEnum;
import projetoa.infra.security.exception.FuncionarioRoleWrongException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfessorService {

        @Autowired
        private ProfessorRepository professorRepository;

        public Professor salvarProfessor(Professor professor) {

            if(professor.getUsuario().getRole() == RoleEnum.PROFESSOR) {
                    return professorRepository.save(professor);
            } else {
                throw new FuncionarioRoleWrongException("O usuário não tem o papel de PROFESSOR");
            }

        }


        @Transactional
        public List<Professor> listarTodosProfessores() {
            return professorRepository.findAll();
        }

        @Transactional
        public Professor listarProfessor(long id) {
            Professor professorExiste = professorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

            return professorExiste;
        }
}
