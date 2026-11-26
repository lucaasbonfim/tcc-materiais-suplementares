package projetoa.domain.professor;

import projetoa.domain.funcionario.Funcionario;
import projetoa.domain.funcionario.RoleTipoFuncionarioEnum;
import projetoa.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("PROFESSOR")
public class Professor extends Funcionario{

    public Professor(Usuario usuario, int matricula_funcionario) {
        super(usuario, matricula_funcionario);
    }

}
